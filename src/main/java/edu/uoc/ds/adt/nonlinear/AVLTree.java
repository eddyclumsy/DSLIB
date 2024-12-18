package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.IllegalArgumentException;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements a balanced binary search tree AVL (Adelson-
 * Velskii & Landis), which is characterized by having the greatest root
 * that all the elements of your left subtree and smaller
 * that all the elements of its right subtree, in addition, the
 * its left and right subtrees are also search trees.
 * <p>
 * The tree is said to be height balanced when the value
 * absolute of the difference in heights of its subtrees is less than or equal to
 * that u and its subtrees are also balanced.
 * <p>
 * Element classes are expected to implement java.lang.Comparable
 * or that a java.util.Comparator is provided as a parameter of the
 * builder.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class AVLTree<E> extends BinarySearchTreeLinked<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. The detector is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Constructor without parameters.
     */
    public AVLTree() {
        super();
    }

    /**
     * Constructor with a parameter.
     *
     * @param comparator comparator that allows you to deduce the priority
     * @throws IllegalArgumentException if the comparator is null
     */
    public AVLTree(java.util.Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Method that restores, if necessary, the balance of the node tree
     * after each insertion or deletion. A non-null NodeAVL is expected.
     *
     * @param bst node tree or subtree to be balanced
     */
    @Override
    protected void balance(Position<E> bst) {
        if (bst != null) ((NodeAVL) bst).balance();
    }

    /**
     * Overrides the superclass method to incorporate the attribute
     * height at the node, which allows you to check the balance of the AVL tree.
     *
     * @param parent parent of the new node
     * @param elemComp comparable element to be saved to the node
     * @return node new binary tree that stores the item and incorporates it
     *         default height "1" (leaf)
     */
    @Override
    protected NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeAVL<>(elemComp);
    }

    /**
     * Class that are NodeTree to add a height attribute that
     * allows you to check and maintain the balance of an AVL node tree.
     */
    protected static class NodeAVL<E> extends NodeTree<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. The detector is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * Node height within the tree. By default it is "1" (leaf).
         */
        protected int height = 1;

        /**
         * Constructor with a parameter. Assigns the received value to the item
         * of the parent node and gives a null value to the child positions.
         * Delegate to the superclass. The default height is "1" (leaf).
         *
         * @param elem value of the element to go to the node
         */
        public NodeAVL(E elem) {
            super();
            element = elem;
        }

        /**
         * Setter for height
         *
         * @param height the new height
         */
        public void setHeight(int height) {
            this.height = height;
        }

        /**
         * Getter for height
         *
         * @return the height
         */
        public int getHeight() {
            return height;
        }

        /**
         * Method that updates the height of the node. Add 1 (parent node) to
         * the height value of your tallest child.
         */
        public void updateHeight() {
            int fe = (leftChild == null) ? 0 :
                    ((NodeAVL<E>) leftChild).height;
            int fd = (rightChild == null) ? 0 : ((NodeAVL<E>) rightChild).height;
            height = 1 + ((fe > fd) ? fe : fd);
        }

        /**
         * Method that checks the balance of the node tree.
         *
         * @return zero if your children are the same height;
         * negative if hanging to the right;
         * and positive if the left subtree is the highest
         */
        public int isBalanced() {
            int fe = (leftChild == null) ? 0 :
                    ((NodeAVL<E>) leftChild).height;
            int fd = (rightChild == null) ? 0 : ((NodeAVL<E>) rightChild).height;
            return (fe - fd);
        }

        /**
         * Method that restores, if necessary, the balance of the node tree
         * after each insertion or deletion. The equilibrium factor must
         * to be between -1 and 1. Four types of imbalance can occur
         * (EE, ED, DD and DE) depending on the swing (left or right)
         * of the corresponding tree and subtree.
         */
        public void balance() {
            updateHeight();
            if (isBalanced() > 1) {               // balanced to the left
                if (((NodeAVL<E>) leftChild).isBalanced() > -1)
                    rotateEE();
                else
                    rotateED();
            } else if (isBalanced() < -1) {         // balanced to the right
                if (((NodeAVL<E>) rightChild).isBalanced() < 1)
                    rotateDD();
                else
                    rotateDE();
            }
        }


        /**
         * Method to correct DD imbalance (right to right).
         * The node has been inserted into the right subtree of the right subtree, or b�
         * a node in the left subtree has been deleted
         * circumstances (in a tree balanced to the right).
         * That is, the tree has grown by (T2) or decreased by (T0).
         * Root B of the right subtree becomes the new root and preserves
         * his right son, who with this move is at a higher level
         * near the root of the tree.
         * <PRE>
         *           A                                  B
         *         /   \                              /   \
         *        /       \                         /       \
         *      T0        B       ---------->     A        T2
         *              /   \                   /   \
         *             /     \                /       \
         *            T1     T2              T0       T1
         * </PRE>
         * The old root A becomes the left son of B and retains his
         * left subtree. Finally, the previous left subtree of B
         * becomes a right subtree of the old root A to preserve the
         * Sort property of search trees.
         */
        protected void rotateDD() {
            NodeAVL<E> node = (NodeAVL<E>) leftChild;
            leftChild = rightChild;
            rightChild = leftChild.rightChild;
            leftChild.rightChild =
                    leftChild.leftChild;
            leftChild.leftChild = node;

            E elemComp = element;
            element = leftChild.element;
            leftChild.element = elemComp;

            // adjust height A (old root) and B (new root subtree)
            ((NodeAVL<E>) leftChild).updateHeight();
            updateHeight();
        }

        /**
         * Protected method that corrects the DE imbalance (right
         * left handed). The node has been inserted into the left subtree of the
         * right subtree, or left subtree node deleted
         * in the same circumstances (in a tree balanced towards the
         * right).
         * That is, the tree has grown by (C) or decreased by (T0).
         * <PRE>
         *      A       ----->        A
         *    /   \                 /   \
         *  /       \             /       \
         * T0        B           T0        C        ----->        C
         *         /   \                 /   \                  /   \
         *       /       \             /       \              /       \
         *      C        T3           T1        B            A         B
         *     / \                             / \          / \       / \
         *    /   \                           /   \        /   \     /   \
         *   T1   T2                         T2   T3      T0   T1   T2   T3
         * </PRE>
         * To restore balance you need two rotations: first is
         * corrects the EE imbalance of the right subtree, and then
         * the DD imbalance of the tree.
         *
         * @see NodeAVL#rotateEE()
         * @see NodeAVL#rotateDD()
         */
        protected void rotateDE() {
            ((NodeAVL<E>) rightChild).rotateEE();
            rotateDD();
        }

        /**
         * Method to correct the imbalance DE (left to left).
         * The procedure is the same in the case of DD imbalance.
         * <PRE>
         *            A                                  B
         *          /   \                              /   \
         *        /       \                          /       \
         *       B        T0       ---------->      T2        A
         *     /   \                                        /   \
         *   /       \                                    /       \
         *  T2       T1                                  T1       T0
         * </PRE>
         *
         * @see NodeAVL#rotateDD()
         */
        protected void rotateEE() {
            NodeAVL<E> node = (NodeAVL<E>) rightChild;
            rightChild = leftChild;
            leftChild = rightChild.leftChild;
            rightChild.leftChild = rightChild.rightChild;
            rightChild.rightChild = node;

            E elemComp = element;
            element = rightChild.element;
            rightChild.element = elemComp;

            ((NodeAVL<E>) rightChild).updateHeight();
            updateHeight();
        }

        /**
         * Protected method that corrects ED imbalance (left
         * right).
         * The procedure is symmetrical in the case of the DE imbalance.
         * <PRE>
         *            A        ----->        A
         *          /   \                  /   \
         *        /       \              /       \
         *       B        T0            C        T0    ----->     C
         *     /   \                  /   \                     /   \
         *   /       \              /       \                 /       \
         *  T3        C            B        T1               B         A
         *           / \          / \                       / \       / \
         *          /   \        /   \                     /   \     /   \
         *         T2   T1      T3   T2                   T3   T2   T1   T0
         * </PRE>
         *
         * @see NodeAVL#rotateDE()
         */
        protected void rotateED() {
            ((NodeAVL<E>) leftChild).rotateDD();
            rotateEE();
        }
    }
}

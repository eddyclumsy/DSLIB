
package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the operations of any binary tree, which
 * is characterized by organizing its elements (nodes) forming a
 * hierarchy: every node (except the root that is the head of the hierarchy) is
 * descending from a single node, and can ascend from a maximum of two nodes
 * (when you have no descendants it is called a leaf).
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class BinaryTreeLinkedImpl<E> extends BinaryTree<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Pointer to the root of the tree.
     */
    protected NodeTree<E> root;


    /**
     * Retrives the number of items in the container.
     *
     * @return number of items it currently contains
     */
    public int size() {
        return (root == null) ? 0 : root.numNodes();
    }


    /**
     * Method to check if the container is empty.
     *
     * @return true or false, if it is empty or not
     */
    @Override
    public boolean isEmpty() {
        return (root == null);
    }


    /**
     * Retrives the tree root, if any.
     *
     * @return root of the tree, null in case the tree
     * be empty.
     */
    public Position<E> root() throws EmptyContainerException {
        return root;
    }


    /**
     * Add an item as a new child from the received position, if possible.
     * If the parent is null, it is added to the root; if it is a leaf, it is added
     * as a left child; otherwise, it is added to the free position.
     *
     * @param parent reference position
     * @param elem   item to add to the tree
     * @return new child; or the root, if the parent was null
     * @throws InvalidPositionException if no more children
     * @pre parent == null || childName(parent) < 2, Invalid Position Exception
     */
    public Position<E> add(Position<E> parent, E elem) {
        NodeTree<E> newNode = newNode(parent, elem);
        if (parent == null)
            root = newNode;
        else {
            if (leftChild(parent) == null)
                setLeftChild(parent, newNode);
            else
                setRightChild(parent, newNode);
        }
        return newNode;
    }


    /**
     * Add an item as the left child of the received position, if any
     * can.
     *
     * @param parent reference position
     * @param elem   item to add to the tree
     * @return new son left
     * @throws InvalidPositionException if any position is null or not
     *                                  valid
     * @pre parent! = null && ((NodeTree) parent).getLeftChild() == null,
     * InvalidPosition Exception
     */
    public Position<E> addLeftChild(Position<E> parent, E elem) {
        NodeTree<E> newNode = newNode(parent, elem);
        setLeftChild(parent, newNode);
        return newNode;
    }


    /**
     * Add an item as the right child of the received position, if possible.
     *
     * @param parent to be the reference position
     * @param elem   item to add to the tree
     * @return new right son
     * @throws InvalidPositionException if any position is null or not
     *                                  valid
     * @prepared! = null && ((NodeTree) stopped).getRightChild () == null, InvalidPositionException
     */
    public Position<E> addRightChild(Position<E> parent, E elem) {
        NodeTree<E> newNode = newNode(parent, elem);
        setRightChild(parent, newNode);
        return newNode;
    }


    /**
     * Retrieves the left child from a position in the tree.
     *
     * @param node position node
     * @return left child of received position; or null if not in t�
     * @throws InvalidPositionException whether the position is null or not
     *                                  valid
     * @pre! isEmpty () && node! = null, InvalidPositionException
     */
    public Position<E> leftChild(Position<E> node) {
        return ((NodeTree<E>) node).getLeftChild();
    }


    /**
     * Retrives the right child from a tree position.
     *
     * @param node position node
     * @return right son of position received; or null if not in t�
     * @throws InvalidPositionException whether the position is null or not
     *                                  valid
     * @pre! estaBuit () && node! = null, InvalidPositionException
     */
    public Position<E> rightChild(Position<E> node) {
        return ((NodeTree<E>) node).getRightChild();
    }


    /**
     * Delete the subtree represented by the child position, if possible.
     * If the parent position is null, delete the entire tree.
     *
     * @param parent position of the parent; can be null
     * @param child  position of the child
     * @throws InvalidPositionException if any position is invalid
     * @pre child!=null, InvalidPositionException
     * @pre parent==null || leftChild(parent)==child || rightChild(pare)==child, InvalidPositionException
     */
    public void delete(Position<E> parent, Position<E> child) {
        if (parent == null)
            root = null;
        else {
            if (leftChild(parent) == child)
                setLeftChild(parent, null);
            else
                setRightChild(parent, null);
        }
    }


    /**
     * Replaces the item contained in the received position.
     *
     * @param elem new item
     * @param node position node
     * @return item in position
     * @throws InvalidPositionException whether the position is null or not
     *                                  valid
     * @pre node!=null, InvalidPositionException
     */
    public E update(Position<E> node, E elem) {
        E old = node.getElem();
        ((NodeTree<E>) node).setElem(elem);
        return old;
    }


    /**
     * Exchange items contained in the received positions.
     * Positions are not exchanged, but the
     * its content.
     *
     * @param node1 first of the two reference positions
     * @param node2 second of the two reference positions
     * @throws InvalidPositionException if any position is null or not
     *                                  valid
     * @pre node1!=null && node2!=null, InvalidPositionException
     */
    public void swap(Position<E> node1, Position<E> node2) {
        E tmp = node1.getElem();
        ((NodeTree<E>) node1).setElem(node2.getElem());
        ((NodeTree<E>) node2).setElem(tmp);
    }


    /**
     * Replace the subtree represented by the child position, if possible.
     * If the parent position is null, replace the entire tree. This
     * Auxiliary method is only accessible within the package and is used
     * to delete a node with less than two children.
     *
     * @param parent      position of the parent; can be null
     * @param child       position of the child
     * @param newPosition position with new tree or subtree; can be null
     * @throws InvalidPositionException if any position is invalid
     * @pre child!=null, InvalidPositionException
     * @pre parent==null && child==root, InvalidPositionException
     * @pre parent!=null && (child==leftChild(parent) || child==rightChild(pare)), InvalidPositionException
     */

    protected void replaceSubTree(Position<E> parent, Position<E> child, Position<E> newPosition) {
        if (parent == null) root = (NodeTree<E>) newPosition;              // substitueix tot l'arbre
        else {
            if (leftChild(parent) == child)
                setLeftChild(parent, (NodeTree<E>) newPosition);
            else
                setRightChild(parent, (NodeTree<E>) newPosition);
        }
    }


    /**
     * Create a new node, with two node chains, that stores one
     * element. The method must be overridden by the subclasses they need
     * other node types.
     *
     * @param elem element to be saved to the node
     * @return new binary tree node that stores the item
     */
    protected NodeTree<E> newNode(Position<E> parent, E elem) {
        return new NodeTree<>(elem);
    }


    private void setLeftChild(Position<E> node, NodeTree<E> child) {
        ((NodeTree<E>) node).setLeftChild(child);
    }


    private void setRightChild(Position<E> node, NodeTree<E> child) {
        ((NodeTree<E>) node).setRightChild(child);
    }


    /**
     * Class that implements a node with two node chains.
     * Only facilitates basic operations: builders and accessories
     * reading and writing. It can store any item (Object).
     */
    protected static class NodeTree<E> implements Position<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();

        /**
         * pointer to the nod.
         */
        protected E element;

        /**
         * pointer to the left child.
         */
        protected NodeTree<E> leftChild;

        /**
         * pointer to the rightChild.
         */
        protected NodeTree<E> rightChild;

        /**
         * Constructor without parameters.
         */
        public NodeTree() {
            super();
        }

        /**
         * Constructor with a parameter. Assigns the received value to the item
         * of the parent node and gives a null value to the child positions.
         *
         * @param elem value of the item to go to the parent node
         */
        public NodeTree(E elem) {
            element = elem;
        }

        /**
         * Constructor with three parameters.
         *
         * @param leftChild  left son
         * @param elem       value of the element contained in the parent node
         * @param rightChild son right
         */
        public NodeTree(NodeTree<E> leftChild, E elem, NodeTree<E> rightChild) {
            setElem(elem);
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        /**
         * Retrieve the number of nodes. Recursively counts nodes in a tree or subtree.
         *
         * @return zero if empty; otherwise it returns the number of nodes
         * of the left subtree plus the right subtree plus one (root)
         */
        public int numNodes() {
            int numNodes = 1;
            NodeTree<E> leftChild = getLeftChild();
            NodeTree<E> righChild = getRightChild();
            if (leftChild != null) numNodes += leftChild.numNodes();
            if (righChild != null) numNodes += righChild.numNodes();
            return numNodes;
        }

        /**
         * Setter for elem
         *
         * @param elem new value of the element contained in the node
         */
        public void setElem(E elem) {
            element = elem;
        }

        /**
         * Getter for elem
         *
         * @return element contained in the node
         */
        public E getElem() {
            return element;
        }

        /**
         * Setter for the left child
         *
         * @param leftChild value for left child
         */
        public void setLeftChild(NodeTree<E> leftChild) {
            this.leftChild = leftChild;
        }

        /**
         * Getter for the left child
         *
         * @return the left child
         */
        public NodeTree<E> getLeftChild() {
            return leftChild;
        }

        /**
         * Setter for the right child.
         *
         * @param rightChild value for the right child
         */
        public void setRightChild(NodeTree<E> rightChild) {
            this.rightChild = rightChild;
        }

        /**
         * Getter for the right child.
         *
         * @return the right left
         */
        public NodeTree<E> getRightChild() {
            return rightChild;
        }

        /**
         * Method that overrides the conversion of the object to String by
         * facilitate code debugging. Delegate toString ()
         * of the item stored in position.
         *
         * @return character string representative of the item
         */
        public String toString() {
            return (element == null) ? "null" : element.toString();
        }
    }

}

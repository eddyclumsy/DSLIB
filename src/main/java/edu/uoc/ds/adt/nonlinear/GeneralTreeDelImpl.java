package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.util.Utils;

/**
 * Trees are structures that relate their elements, called
 * nodes, forming hierarchies: every node (except the root) is a descendant
 * from a single node, and can be ascending
 * of other nodes (when it has no descendants it is named leaf). when a node
 * can have an indeterminate number of children we are talking about general trees
 * (general tree) and, if it has a fixed number N, of trees of order N (n-ary
 * tree); in the latter, the case of N = 2 stands out, they are called trees
 * binaries (binary tree).
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà.
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1
 */
public class GeneralTreeDelImpl<E> extends AbstractTree<E> {
    /**
     * Attribute that in Java determines the compatibility between serializable
     * objects of the same class. The identifier is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Binary tree that implements operations by delegation.
     */
    protected BinaryTreeLinkedImpl<E> binaryTree;


    public GeneralTreeDelImpl() {
        binaryTree = build();
    }


    protected BinaryTreeLinkedImpl<E> build() {
        return new BinaryTreeLinkedImpl<E>();
    }


    /**
     * method that returns the root of the tree, if any.
     */
    public int size() {
        return binaryTree.size();
    }


    /**
     * method that returns the root of the tree, if any.
     *
     * @return root of tree
     */
    public Position<E> root() {
        return binaryTree.root();
    }

    /**
     * Method that supports multiple traversals, of child positions
     * of the reference position, simultaneous and independent of each other.
     *
     * @param parent reference position
     * @return enumeration of child positions
     * @throws InvalidPositionException if position is null or not
     * valid
     */
    public Traversal<E> children(Position<E> parent) {
        if (isEmpty() || (parent == null))
            throw new InvalidPositionException();

        return new ChildrenTraversal<E>(this, parent);
    }

    /**
     * Add an element as a new child of the received position, if possible.
     * If the parent is null, it is added at the root; if it is a leaf, it is added
     * as first child (leftmost child); otherwise, it is added to
     * the first free position (from left to right).
     *
     * @param parent reference position
     * @param elem element that you want to add to the tree
     * @return new child; or the root, if the parent was null
     */
    public Position<E> add(Position<E> parent, E elem) {
        if ((parent == null) || isLeaf(parent))
            return binaryTree.add(parent, elem);

        parent = binaryTree.leftChild(parent);
        while (binaryTree.rightChild(parent) != null) parent = binaryTree.rightChild(parent);

        return binaryTree.addRightChild(parent, elem);
    }

    /**
     * Replaces the element contained in the received position.
     *
     * @param elem new element
     * @param pos reference position
     * @return element that was at position
     */
    public E update(Position<E> pos, E elem) {
        return binaryTree.update(pos, elem);
    }

    /**
     * Exchanges the elements contained in the received positions.
     * The positions are not interchanged.
     *
     * @param pos1 first of the two reference positions
     * @param pos2 second of the two reference positions
     */
    public void swap(Position<E> pos1, Position<E> pos2) {
        binaryTree.swap(pos1, pos2);
    }

    /**
     * Deletes the subtree represented by the child position, if possible.
     * If the position of the parent is null, delete the entire tree.
     *
     * @param parent position of parent; can be null
     * @param child child position
     * @throws InvalidPositionException if any position is invalid
     */
    public void delete(Position<E> parent, Position<E> child)
            throws InvalidPositionException {
        if (isEmpty() || (child == null))
            throw new InvalidPositionException();

        if (parent == null) binaryTree.delete(parent, child);
        else {
            if (child != binaryTree.leftChild(parent)) {
                for (parent = binaryTree.leftChild(parent);
                     binaryTree.rightChild(parent) != child;
                     parent = binaryTree.rightChild(parent))
                    ;
            }
            binaryTree.replaceSubTree(parent, child, binaryTree.rightChild(child));
        }
    }

    /**
     * Class that provides a traversal of the positions. based on
     * pattern Iterator, supports multiple simultaneous traversals and
     * independent of the container. It is sensitive to eventual changes in
     * the structure of positions.
     *
     * @see Traversal#hasNext() ()
     * @see Traversal#next()
     */
    protected static class ChildrenTraversal<E> implements Traversal<E> {

        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * Tree that is being traversed.
         */
        protected GeneralTreeDelImpl<E> tree;


        /**
         * auxiliar position
         */
        protected Position<E> child;


        /**
         * Constructor.
         *
         * @param parent reference position; expected to be a valid position
         */
        public ChildrenTraversal(GeneralTreeDelImpl<E> tree, Position<E> parent) {
            this.tree = tree;
            child = tree.binaryTree.leftChild(parent);
        }


        /**
         * Check if there is a first or next position. is sensitive to
         * eventual alterations of the structure of positions. returns false
         * if it is empty or the last position has already been visited.
         *
         * @return true or false, depending on whether you can move forward or not
         */
        public boolean hasNext() {
            return child != null;
        }


        /**
         * First advance, if possible, and then return the position.
         * If there is no next position throw an exception.
         *
         * @return next position
         */
        public Position<E> next() throws InvalidPositionException {
            if (!hasNext()) throw new InvalidPositionException(
                    "there is no next");

            Position<E> temp = child;
            child = tree.binaryTree.rightChild(child);
            return temp;
        }
    }
}

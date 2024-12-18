package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.helpers.Range;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.IllegalArgumentException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the operations of any binary tree, which
 * is characterized by organizing its elements (nodes) forming a
 * hierarchy: every node (except the root that is the head of the hierarchy) is
 * descending from a single node, and can ascend from a maximum of two nodes
 * (when you have no descendants it is called a leaf).
 * <p>
 * In the sequential representation of the tree the nodes inside are organized
 * of a vector so that the children and the parent are accessible by applying
 * just a formula. The first position of the vector will always contain
 * the root of the tree; if a node is in position [k], its child
 * left will be at [2 * k] and his son at [2 * k + 1].
 * <p>
 * This representation has two modifying operations (add at the end
 * and delete the last position) that allow to maintain a structure
 * of quasi-complete tree; That is, there will be no free position in one
 * route through tree levels. Since the first position [0] of the
 * vector is not used to facilitate the above calculations, if the number
 * of squares of the vector is power of two, the tree may become
 * complete.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class BinaryTreeArrayImpl<E> extends BinaryTree<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Maximum capacity of the container by default.
     */
    public static final int DEFAULT_CAPACITY = 256;

    /**
     * Number of items currently in the container.
     */
    protected int n;

    /**
     * the array. Positions start at zero.
     */
    protected Range<E>[] elems;

    /**
     * Constructor without parameters (maximum capacity of the container by default).
     */
    public BinaryTreeArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor with a parameter. Create a vector with the specified capacity.
     *
     * @param max maximum number of tree elements
     * @throws IllegalArgumentException if the maximum capacity of
     * new tree is negative
     * @pre max> = 0, new ExceptionParameterIncorrect ("maximum number of items cannot be negative")
     */
    public BinaryTreeArrayImpl(int max) {
        elems = new Range[max];
        n = 0;
    }

    /**
     * Retrieves the number of items in the container.
     *
     * @return number of items it currently contains
     * /
     */
    public int size() {
        return n;
    }

    /**
     * Method to check if the container is empty.
     *
     * @return true or false, depending on whether it is empty or not
     */
    @Override
    public boolean isEmpty() {
        return (n == 0);
    }

    /**
     * Method to check if the container is full.
     *
     * @return true or false, depending on whether it is full or not
     */
    public boolean isFull() {
        return (n == elems.length);
    }

    /**
     * Retrieves the root node, if any.
     *
     * @return tree root
     * @throws EmptyContainerException if the tree is empty
     * @pre !isEmpty (), new EmptyContainerExepction("Unable to access root of empty tree")
     */
    public Position<E> root() {
        return elems[0];
    }


    /**
     * Add an item as a new child from the received position.
     * If the parent is null, it is added to the root; if it is a leaf, it is added
     * as a left child; and if the father has only one child, it is added as
     * the other child.
     *
     * @param parent reference position
     * @param elem item to add to the tree
     * @return The position of the added node.
     * @throws InvalidPositionException If the parent position is invalid for power
     * add a child. This also includes cases where the father already has two children and
     * in case the children fall out of the maximum range allowed when creating the tree.
     * @pre positionValid (even) &&
     * (indexleftChild(parent) <max &&! hasLeftChild(parent)) ||
     * (indexrightChild(even) <max &&! hasRightChild(even)), InvalidPositionException
     */
    public Position<E> add(Position<E> parent, E elem) {
        Position<E> newChildPosition;
        if (leftChild(parent) != null)
            newChildPosition = addRightChild(parent, elem);
        else
            newChildPosition = addLeftChild(parent, elem);
        return newChildPosition;
    }


    /**
     * Add an item as the left child of the received position.
     *
     * @param parent reference position
     * @param elem item to add to the tree
     * @return The position of the added node.
     * @throws InvalidPositionException If the parent position is invalid for power
     * add a child. This also includes cases where the father already has a left child
     * and the case in which it falls outside the maximum range allowed when creating the tree.
     * @pre positionValid (even) &&
     * indexLeftChild(parent) <maxim &&! hasLeftChild(parent)
     */
    public Position<E> addLeftChild(Position<E> parent, E elem) {
        int index = getIndexLeftChild(parent);
        n++;
        elems[index] = new Range<>(index, elem);
        return elems[index];
    }


    /**
     * Add an item as the right child of the received position, if possible.
     *
     * @param parent to be the reference position
     * @param elem item to add to the tree
     * @return The position of the added node.
     * @throws InvalidPositionException If the parent position is invalid for power
     * add a child. This also includes cases where the father already has a child
     * and the case in which it falls outside the maximum range allowed when creating the tree.
     * @pre positionValid (even) &&
     * indexRightChild(ready) <maximum &&! hasRightChild(ready)
     */
    public Position<E> addRightChild(Position<E> parent, E elem) {
        int index = getIndexRightChild(parent);
        n++;
        elems[index] = new Range<>(index, elem);
        return elems[index];
    }


    /**
     * Retrieves the parent from a tree position, if any.
     *
     * @param pos reference position
     * @return father of received position; or null if it was the root.
     */
    public Position<E> parent(Position<E> pos) {
        if (getIndex(pos) == 0) return null;

        int pi = getParentIndex(pos);
        return elems[pi];
    }

    /**
     * Retrieves the left child from a position in the tree.
     *
     * @param pos reference position
     * @return left child of received position; or null.
     * @throws InvalidPositionException if the position is invalid.
     * @pre validPosition(pos)
     */
    public Position<E> leftChild(Position<E> pos) {
        int index = getIndexLeftChild(pos);
        if (index >= elems.length || elems[index] == null)
            return null;
        return elems[index];
    }

    /**
     * Retrieves the right child  from a tree position.
     *
     * @param pos reference position
     * @return right child of position received; or null
     * @throws InvalidPositionException whether the position is null or not
     * valid
     * @pre validPosition(pos)
     */
    public Position<E> rightChild(Position<E> pos) {
        int index = getIndexRightChild(pos);
        if (index >= elems.length || elems[index] == null)
            return null;
        return elems[index];
    }

    /**
     * Delete the subtree represented by the child position, if possible.
     * If the parent position is null, delete the entire tree.
     *
     * @param parent position of the parent; can be null
     * @param child position of the child
     * @throws InvalidPositionException if any position is invalid or
     * b� father is not the father of a child.
     * @pre isParentOf (parent, child)
     * @see BinaryTreeArrayImpl#delete(Position, Position)
     */
    public void delete(Position<E> parent, Position<E> child) {
        Position<E> netLeft = leftChild(child);
        Position<E> netRight = rightChild(child);
        if (netLeft != null)
            delete(child, netLeft);
        if (netRight != null)
            delete(child, netRight);
        int indexChild = getIndex(child);
        n--;
        elems[indexChild] = null;
    }


    /**
     * Replaces the item contained in the received position.
     *
     * @param elem new item
     * @param pos reference position
     * @return item in position before replacing.
     * @pre validPosition(pos)
     */
    public E update(Position<E> pos, E elem) {
        Range<E> rang = (Range<E>) pos;
        E old = rang.getElem();
        rang.setElem(elem);
        return old;
    }

    /**
     * Swap two positions within the tree. The objects
     * positions are exchanged for positions, with which their
     * content remains constant.
     *
     * @param pos1 first of the two reference positions.
     * @param pos2 second of the two reference positions.
     * @pre validPosition(pos1) && validPosition(pos2)
     */
    public void swap(Position pos1, Position pos2) {
        Range<E> r1 = (Range<E>) pos1;
        Range<E> r2 = (Range<E>) pos2;
        int index1 = r1.getIndex();
        int index2 = r2.getIndex();
        r1.setIndex(index2);
        r2.setIndex(index1);
        elems[index1] = r2;
        elems[index2] = r1;
    }


    private int getIndex(Position<E> pos) {
        if (pos == null) return -1;
        return ((Range) pos).getIndex();
    }


    private int getParentIndex(Position<E> pos) {
        int index = (pos!=null?((Range) pos).getIndex():0);
        if (index == 0) return -1;
        return (index - 1) / 2;
    }


    private int getIndexLeftChild(Position<E> pos) {
        if (pos == null) return 0;
        return ((Range) pos).getIndex() * 2 + 1;
    }


    private int getIndexRightChild(Position<E> pos) {
        if (pos == null) return 0;
        return ((Range) pos).getIndex() * 2 + 2;
    }


}

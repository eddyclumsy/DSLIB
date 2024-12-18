package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.Queue;
import edu.uoc.ds.adt.helpers.Range;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.NonComparableException;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.util.Utils;

import java.util.Comparator;

/**
 * Class that implements the operations of the priority queues (priority
 * queue) and is distinguished because the elements are not inserted at the end,
 * but they are ordered according to a priority they have associated.
 * Delete and query operations always affect the item you have
 * a smaller priority. Consequently, the elements will have to
 * allow a comparison operation from which priority can be deduced.
 * <p>
 * In the sequential representation of the tree by means of a pile
 * the nodes of the tree are organized inside a vector so that the
 * children and the father are accessible by applying only one formula. The first
 * position of the vector will always contain the root of the tree that is,
 * precisely, the smallest element; if a node is in position [k], the
 * his left son will be at [2 * k] and his right son at [2 * k + 1].
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class PriorityQueue<E> implements Queue<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Class that extend the behavior of a binary tree with two methods that we
     * provide the functionality of a almost-complete tree.
     *
     * @param <E>
     */

    protected static class AlmostCompleteBinaryTree<E> extends BinaryTreeArrayImpl<E> {

        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        public AlmostCompleteBinaryTree() {
            super();
        }

        public AlmostCompleteBinaryTree(int max) {
            super(max);
        }


        /**
         * Returns the last item in a almost-complete tree level traverse.
         *
         * @return The last item on a level journey.
         * @pre! isEmpty(), EmptyContainerException
         */
        public Position<E> last() {
            return elems[n - 1];
        }

        /**
         * Add an item below the last position of the tree in
         * a route by levels. It maintains its status as a quasi-complete tree.
         *
         * @param elem item to add to the tree
         * @return new timalast position�
         * @pre! isFull(), FullContainerException
         */
        public Position<E> addLast(E elem) {
            n++;
            elems[n - 1] = new Range<E>(n - 1, elem);
            return last();
        }

        /**
         * Deletes the last occupied position of the tree in a path by
         * levels. It maintains its status as a quasi-complete tree.
         *
         * @return The deleted item
         * @pre! isEmpty(), EmptyContainerException
         */
        public Position<E> deleteLast() {
            n--;
            Position<E> aux = elems[n];
            elems[n] = null;
            return aux;
        }

    }


    /**
     * Vector binary tree that supports the priority queue.
     */
    protected AlmostCompleteBinaryTree<E> heap;

    /**
     * Specific comparator that allows to deduce the priority between the
     * elements. It can have a null value and then the interface is used
     * java.lang.Comparable
     */
    protected java.util.Comparator<E> comparator;

    /**
     * Constructor without parameters (maximum capacity, default) i
     * elements of a class that implements java.lang.Comparable.
     */
    public PriorityQueue() {
        heap = new AlmostCompleteBinaryTree<>();
    }

    /**
     * Constructor with a parameter (given capacity) and elements of a
     * class that implements java.lang.Comparable.
     *
     * @param max maximum number of items the queue can contain
     */
    public PriorityQueue(int max) {
        heap = new AlmostCompleteBinaryTree<>(max);
    }

    /**
     * Constructor with a parameter (maximum capacity, default) i
     * items of a class comparable to the given comparator.
     *
     * @param comparator comparator that allows you to deduce the priority
     */
    public PriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
        heap = new AlmostCompleteBinaryTree<>();
    }

    /**
     * Constructor with two parameters (given capacity) and elements of one
     * class comparable to the given comparator.
     *
     * @param max maximum number of items the queue can contain
     * @param comparator comparator that allows you to deduce the priority
     */
    public PriorityQueue(int max, java.util.Comparator<E> comparator) {
        this.comparator = comparator;
        heap = new AlmostCompleteBinaryTree<>(max);
    }

    /**
     * Retrieves the number of items in the container.
     *
     * @return number of items it currently contains
     */
    public int size() {
        return heap.size();
    }

    /**
     * Method to check if the container is empty.
     *
     * @return true or false, depending on whether it is empty or not
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Method to check if the container is full.
     *
     * @return true or false, depending on whether it is full or not
     */
    public boolean isFull() {
        return heap.isFull();
    }

    /**
     * Add an item to the appropriate position, if any.
     *
     * @param elem comparable item to add to queue
     * @pre! heap.isFull(), FullContainerException
     */
    public void add(E elem) {
        if (isFull()) throw new FullContainerException();
        siftUp(nextLastPosition(elem));
    }


    protected Position<E> nextLastPosition(E elem) {
        return heap.addLast(elem);
    }


    protected void siftUp(Position<E> newPosition) {
        boolean found = false;
        while (!found) {
            Position<E> parent = heap.parent(newPosition);
            found = parent == null || compare(newPosition, parent) >= 0;
            if (!found)
                heap.swap(newPosition, parent);
        }
    }


    /**
     * Delete the lowest priority item, if any.
     *
     * @return first item, which is the lowest priority
     * @pre! heap.isEmpty(), EmptyContainerException
     * @see PriorityQueue#findChildren(Position)
     */
    public E poll() {
        if (isEmpty())
            throw new EmptyContainerException();
        E first = heap.root().getElem();
        heap.swap(heap.root(), heap.last());
        deleteLastPosition();
        siftDown(heap.root());
        return first;
    }


    protected Position<E> deleteLastPosition() {
        return heap.deleteLast();
    }


    protected void siftDown(Position<E> position2Sort) {
        boolean found = false;
        while (!found) {
            Position<E> child2Swap = findChildren(position2Sort);
            found = child2Swap == null || compare(position2Sort, child2Swap) <= 0;
            if (!found)
                heap.swap(position2Sort, child2Swap);
        }
    }


    /**
     * Retrieves the lowest priority item, if any.
     * If you want to unlock the object, you must delete it immediately.
     *
     * @return first item, which is the lowest priority
     * @throws EmptyContainerException if the queue is empty
     * @pre! isEmpty(), EmptyContainerException
     */
    public E peek() {
        if (isEmpty())
            throw new EmptyContainerException();
        return heap.root().getElem();
    }


    /**
     * Retrieves the items in the container.
     * Returns an enumeration. You can get a list with a couple of
     * lines of code: <PRE>
     *   for (Iterator it = adt.values(); it.hasNext();)
     *     System.out.println (it.next ()); </PRE>
     *
     * To enumerate is simply to enunciate one after the other (things
     * of a series, the parts of a whole). But if the container is defined
     * some sort of order or route, the enumeration must be
     * consequent and offer the items in order (FIFO, LIFO, order,
     * etc.), without altering the current state of the container.
     *
     * @return enumeration of items associated with keys
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    public Iterator<E> values() {
        final PriorityQueue<E> pq = clone();

        Iterator<E> it = new Iterator<>(){

            @Override
            public boolean hasNext() {
                return !pq.isEmpty();
            }

            @Override
            public E next() throws InvalidPositionException {
                return pq.poll();
            }
        };

        return  it;// heap.values();
    }

    /**
     * Method that supports multiple paths, from the positions of the
     * container, simultaneous and independent of each other.
     *
     * @return enumeration of container positions by level
     */
    public Traversal positions() {
        return heap.positions();
    }

    /**
     * Method overwriting Object.toString (). Remove separate items
     * for the platform line jump.
     *
     * @return list of items in a level tour
     */
    public String toString() {
        return heap.toString();
    }


    /**
     * Protected method that compares the items contained in the positions
     * received. If the constructor has not defined a comparator, it is used
     * java.lang.Comparable to deduce the priority between both elements.
     *
     * @param pos1 first of the two reference positions
     * @param pos2 second of the two reference positions
     * @return a negative, zero, or positive integer, depending on whether the element of the
     * first position has less, equal or more priority than
     * the element of the second position, according to the
     * implementation of the comparator
     * @throws InvalidPositionException if any position is null or not
     * valid
     * @throws NonComparableException if any of the items
     * contents in positions �s not comparable
     * @pre validPosition(pos1) && validPosition(pos2)
     */
    protected int compare(Position<E> pos1, Position<E> pos2) {
        E elem1 = pos1.getElem();
        E elem2 = pos2.getElem();
        int comp = 0;
        if (comparator == null)
            comp = ((Comparable<E>) elem1).compareTo(elem2);
        else
            comp = comparator.compare(elem1, elem2);
        return comp;
    }


    /**
     * Auxiliary method that given a position of the tree that is not arranged inside the tree i
     * that we want to order, determines which of your two children should be exchanged
     * (if this exchange occurs.
     *
     */
    private Position<E> findChildren(Position<E> pos) {
        Position<E> leftChild = heap.rightChild(pos);
        Position<E> rightChild = heap.leftChild(pos);
        if (leftChild == null) return rightChild;
        if (rightChild == null) return leftChild;
        return compare(rightChild, leftChild) < 0 ? rightChild : leftChild;
    }

    public PriorityQueue<E> clone() {
        PriorityQueue aux = new PriorityQueue(this.comparator);
        Traversal<E> traversal = this.positions();
        while (traversal.hasNext()) {
            aux.add(traversal.next().getElem());
        }

        return aux;
    }


}

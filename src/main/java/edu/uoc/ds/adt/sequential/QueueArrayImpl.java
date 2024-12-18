package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.exceptions.IllegalArgumentException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.IteratorArrayImpl;
import edu.uoc.ds.util.Utils;

/**
 * Sequence that is characterized by consulting and deleting the first one
 * element inserted: first-in-first-out (FIFO).
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 *
 * @inv n >= 0 && (n == last-first+1 || n == max+last - first + 1)
 * @since 1.5
 */
public class QueueArrayImpl<E> implements Queue<E>, FiniteContainer<E> {
     /**
     * Attribute that determines the compatibility between
     * serializable objects of the same class. It is calculated
     * using a method in the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Maximum capacity of the container by default.
     */
    public static final int DEFAULT_CAPACITY = 256;

    /**
     * Container item table.
     */
    protected E[] elems;

    /**
     * Number of items currently in the container.
     */
    protected int n;

    /**
     * First element of the queue.
     */
    private int first;

    /**
     * Constructor without parameters  (Maximum capacity of the container by default).
     *
     * @post n == 0 && this.max == DEFAULT_MAX
     */
    public QueueArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor with a parameter.
     *
     * @param max maximum number of  items
     * @throws IllegalArgumentException if the maximum capacity
     *                              of the new queue is negative
     * @pre max>=0
     * @post n == 0 && this.max == max
     */
    public QueueArrayImpl(int max) {
        elems = (E[]) new Object[max];
        n = 0;
        first = 0;
    }


    private int position(int position) {
        return position % elems.length;
    }

    private int next(int position) {
        return (position + 1) == elems.length ? 0 : position + 1;
    }


    /**
     * Retrieves the number of items in the container.
     *
     * @return number of items
     * @post $return == n && this == $old(this)
     */
    public int size() {
        return n;
    }

    /**
     * Method to check if the container is empty.
     *
     * @return true or false, depending on whether it is empty or not
     * @post $return == (n == 0) && this == $old(this)
     */
    public boolean isEmpty() {
        return (n == 0);
    }

    /**
     * Method to check if the container is full.
     *
     * @return true or false, depending on whether it is full or not
     * @post $return == (n == max) && this == $old(this)
     */
    public boolean isFull() {
        return (n == elems.length);
    }

    /**
     * Add an item to the queue
     *
     * @param elem item to add to the queue
     * @throws FullContainerException if the queue is full
     *
     * @pre !isFull(), FullContainerException
     * @post n == $old(n)+1
     * && first == $old(first)
     * && last == ($old(last) == max-1 ? 0 : $old(last)+1)
     * && vector[last] == elem
     * && $all(i:vector,i == last || $old(vector[i]) == vector(i))
     */
    public void add(E elem) {
        if (isFull()) throw new FullContainerException();
        int last = position(first + n);
        elems[last] = elem;
        n++;
    }

    /**
     * Delete the first item in the queue
     *
     * @return first item inserted in queue
     * @throws EmptyContainerException if the queue is empty
     * @pre !isEmptu(), EmptyContainerException
     * @post n == $old(n)-1
     * && last == $old(last)
     * && first == ($old(first) == max-1 ? 0 : $old(first)+1)
     * && $all(i:vector,i == $old(primer) || $old(vector[i]) == vector(i))
     */
    public E poll() {
        if (isEmpty()) throw new EmptyContainerException();
        E elem = elems[first];
        elems[first] = null;
        first = next(first);
        n--;
        return elem;
    }

    /**
     * Retrieves the first item added to the queue
     * @return first item in queue
     * @throws EmptyContainerException if the queue is empty
     * @pre !isEmpty()
     * @post $return == vector[first] && this == $old(this)
     */
    public E peek() {
        return elems[first];
    }

    /**
     * Retrieves the items in the container.
     * Returns an iterator. You can get a list with a couple of
     * lines of code:
     * <PRE>
     *   for (Iterator it = adt.values(); it.hasNext ();)
     *     System.out.println (it.next ());
     * </PRE>
     * To enumerate is simply to enunciate one after the other (things
     * of a series, the parts of a whole). But if the container is defined
     * some sort of order or route, the enumeration must be
     * consequent and offer the items in order (FIFO, LIFO, order,
     * etc.), without altering the current state of the container.
     *
     * @return enumeration of container items
     * @throws InvalidPositionException if you want to get the following
     * element of the enumeration and none or none more
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    public Iterator<E> values() {
        return new IteratorArrayImpl<>(elems, size(), first);
    }

    /**
     * Method overwriting Object.toString (). Remove separate items
     * for the platform line jump.
     *
     * @return list of items
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("{QUEUE:");
        for (Iterator<E> it = values(); it.hasNext(); ) {
            buffer.append(it.next());
            if (it.hasNext()) buffer.append(',');
        }
        buffer.append("}");
        return buffer.toString();
    }
}

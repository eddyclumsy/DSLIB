package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.exceptions.IllegalArgumentException;
import edu.uoc.ds.traversal.IteratorArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.util.Utils;

/**
 * Sequence that is characterized by consulting and deleting the last one
 * element inserted: last-in-first-out (LIFO).
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class StackArrayImpl<E> implements Stack<E>, FiniteContainer<E> {

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
     * Number of items currently in the container. Also
     * represents the position where a new item should be stacked.
     */
    protected int n;

    /**
     * The array of elems. Positions start at zero.
     */
    protected E[] elems;

    /**
     * Constructor without parameters (maximum capacity, default).
     *
     * @post n == 0 && this.max == DEFAULT_CAPACITY && elems.length==max
     */
    public StackArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor with a parameter.
     *
     * @param max maximum number of items it can contain
     * @throws IllegalArgumentException if the maximum capacity of the
     * new stack is negative
     *
     * @pre max>0
     * @post n == 0 && this.max == max && elems.length==max
     */
    public StackArrayImpl(int max) {
        elems = (E[]) new Object[max];
        n = 0;
    }

    /**
     * Getter for the number of items in the container.
     *
     * @return number of items it currently contains
     * @post $return==n && this == $old(this)
     */
    public int size() {
        return n;
    }

    /**
     * Method to check if the container is empty.
     *
     * @return true or false, depending on whether it is empty or not
     * @post $return==(n==0) && this == $old(this)
     */
    public boolean isEmpty() {
        return (n == 0);
    }

    /**
     * Method to check if the container is full.
     *
     * @return true or false, depending on whether it is full or not
     * @post $return==(n==max) && this == $old(this)
     */
    public boolean isFull() {
        return (n == elems.length);
    }

    /**
     * Add an item to the stack, if any.
     *
     * @param elem item you want to add to the stack
     * @throws FullContainerException if the stack is full
     * @pre !isFull(), FullContainerException
     * @post n == $old(n)+1
     * && vector[n-1] == elem
     * && $all(i:vector,i == n-1 || $old(vector[i]) == vector(i))
     */
    public void push(E elem) {
        if (isFull()) throw new FullContainerException();
        elems[n] = elem;
        n++;
    }

    /**
     * Delete the top of the stack item, if any.
     *
     * @return item at the top of the stack
     * @throws EmptyContainerException if the stack is empty
     * @pre !isEmpty(), EmptyContainerException
     * @post n == $old(n)-1
     * && $all(i:vector,i >= n || $old(vector[i]) == vector(i))
     */
    public E pop() {
        if (isEmpty()) throw new EmptyContainerException();
        E aux = elems[n - 1];
        elems[n - 1] = null;
        n--;
        return aux;
    }

    /**
     * Getter for the last item added to the stack, if any.
     *
     * @return element at the top of the stack
     * @throws EmptyContainerException if the stack is empty
     * @pre !isEmpty()
     * @post $return == vector[n-1] && this==$old(this)
     */
    public E peek() {
        return elems[n - 1];
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
        return new IteratorArrayImpl<>(elems, size(), size()-1, -1);
    }

    /**
     * Method overwriting Object.toString (). Remove separate items
     * for commas.
     *
     * @return A String with the list of items
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("{STACK:");
        for (Iterator it = values(); it.hasNext(); ) {
            buffer.append(it.next());
            if (it.hasNext()) buffer.append(',');
        }
        buffer.append("}");
        return buffer.toString();
    }
}

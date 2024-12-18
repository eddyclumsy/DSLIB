package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Iterator;

import java.io.Serializable;

/**
 * Interface that defines the operations of any ADT that is
 * characterize to contain a set of elements. The
 * create() ordestroy () methods: classes that implement it can
 * set them or let the compiler use the constructor for
 * default, and the "garbage collector" for unreferenced objects.
 * <p>
 * It also defines, for practical purposes, the values() method that it allows
 * display all items in the container using an iterator.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface Container<E> extends Serializable {
    /**
     * Check if the container is empty.
     *
     * @return true if the container is empty
     */
    boolean isEmpty();

    /**
     * return the number of items in the container.
     *
     * @return number of items in the container
     */
    int size();

    /**
     * Returns an iteration. You can get a list with a couple of
     * lines of code:
     * <PRE>
     *  for (Iterator it = adt.values (); it.hasNext ();)
     *    System.out.println (it.next ());
     *  </PRE>
     *  To iterate is simply to enunciate one after the other (things
     *  of a series, the parts of a whole). But if the container is defined
     *  some sort of order or route, the enumeration must be
     *  consequent and offer the items in order (FIFO, LIFO, order,
     *  etc.), without altering the current state of the container.
     *
     *  @return iteratin of container items
     *  @throws InvalidPositionException if you want to get the following
     *  element of the enumeration and none or none more
     *
     *  @see Iterator#hasNext()
     *  @see Iterator#next()
     */
    Iterator<E> values();
}

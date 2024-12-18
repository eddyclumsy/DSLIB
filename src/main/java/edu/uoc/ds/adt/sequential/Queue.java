package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.exceptions.EmptyContainerException;

/**
 * Sequence that is characterized by consulting and deleting the first one
 * element inserted: first-in-first-out (FIFO).
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface Queue<E> extends Container<E> {
    /**
     * Add an item to the queue, if any.
     * @param elem item to add to the queue
     */
    void add(E elem);

    /**
     * Delete the first item in the queue
     *
     *  @return first item inserted in queue
     *  @throws EmptyContainerException if the queue is empty
     */
    E poll();

    /**
     *  method for get the first item added to the queue.
     *  @return first item in queue
     *  @throws EmptyContainerException if the queue is empty
     */
    E peek();
}

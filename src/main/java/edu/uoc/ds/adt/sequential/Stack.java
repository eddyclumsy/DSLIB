package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.exceptions.EmptyContainerException;

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
public interface Stack<E> extends Container<E> {
    /**
     * Add an item to the stack, if any.
     *
     * @param elem item you want to add to the stack
     */
    void push(E elem);

    /**
     * Delete the top of the stack item, if any.
     *
     * @return item at the top of the stack
     * @throws EmptyContainerException if the stack is empty
     */
    E pop();

    /**
     * Getter for the last item added to the stack, if any.
     *
     * @return element at the top of the stack
     * @throws EmptyContainerException if the stack is empty
     */
    E peek();
}

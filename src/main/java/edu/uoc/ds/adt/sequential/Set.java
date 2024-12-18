package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.exceptions.IllegalArgumentException;

/**
 * Interface that defines the basic operations of a set.
 * <p>
 * Sets are structures that store non-repeating elements. The
 * class of objects must have an equality operation.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface Set<E> extends Container<E> {
    /**
     * Add an item, if possible.
     *
     * @param elem element to add to the set
     */
    void add(E elem);

    /**
     * Checks if an element exists.
     *
     * @param elem reference element
     * @return true or false, depending on whether the element is found or not
     */
    boolean contains(E elem);

    /**
     * Delete an item, if possible.
     *
     * @param elem reference element
     * @return element deleted; or null, if it wasn't there
     */
    E delete(E elem);

    /**
     * Adds the elements of a second set that do not exist in the
     * current set, if possible. If you find an equivalent element, according to the
     * comparison function, overwrites it.
     *
     * @param set set to join to current;
     * can be empty, but not null
     * @throws IllegalArgumentException if set is null
     */
    void union(Set<E> set);

    /**
     * Deletes from the current set the elements that do not exist in one
     * second set, if possible.
     *
     * @param conj set to intersect with current;
     * can be empty, but not null
     * @throws IllegalArgumentException if set is null
     */
    void intersection(Set<E> conj);

    /**
     * Clears from the current set the elements that exist in one second
     * set, if possible.
     *
     * @param conj set to be subtracted from the current one;
     * can be empty, but not null
     * @throws IllegalArgumentException if set is null
     */
    void difference(Set<E> conj);
}

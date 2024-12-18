package edu.uoc.ds.traversal;

import edu.uoc.ds.exceptions.InvalidPositionException;

/**
 * Interface that defines the bidirectional route operations of the
 * elements of a container.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface BidirectionalIterator<E> extends Iterator<E> {

    /**
     * Checks if there is a last or previous item.
     */
    boolean hasPrevious();

    /**
     * Accessor for reading the last or previous element of the enumeration.
     *
     * @return last or previous element to the current one
     * @throws InvalidPositionException if the above is to be obtained
     * element of the enumeration and none or none
     * more
     */
    E previous() throws InvalidPositionException;
}

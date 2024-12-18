package edu.uoc.ds.traversal;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.InvalidPositionException;

/**
 * Interface that defines the position traversal operations
 * of a container in both directions.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface BidirectionalTraversal<E> extends Traversal<E> {

    /**
     * Enumeration that helps us to indicate where the route begins:
     * if at the beginning or at the end of the collection.
     */
    enum traversalMode {BEGINNING, END}

    /**
     * Checks if there is a first or second element.
     */
    boolean hasPrevious();

    /**
     * Accessor for reading the previous element of the enumeration.
     *
     * @return first or second element in the current one
     * @throws InvalidPositionException if you want to get the following
     * element of the enumeration and none or none
     * more
     */
    Position<E> previous() throws InvalidPositionException;

}

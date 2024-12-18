package edu.uoc.ds.traversal;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.InvalidPositionException;

import java.io.Serializable;

/**
 * Interface that defines the position traversal operations
 * of a container.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface Traversal<E> extends Serializable {
    /**
     * Checks if there is a first or second element.
     */
    boolean hasNext();

    /**
     * Accessor for reading the first or next element of the enumeration.
     *
     * @return first or second element in the current one
     * @throws InvalidPositionException if you want to get the following
     * element of the enumeration and none or none
     * more
     */
    Position<E> next() throws InvalidPositionException;

}

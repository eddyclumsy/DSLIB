package edu.uoc.ds.adt.helpers;

import java.io.Serializable;

/**
 * Interface that represents a position of a container. A position is
 * the reference to an element of the container and is defined in
 * relation to its neighbors.
 * <p>
 * The classes that implement it must specify the mentioned position
 * associating a chained node, the index of a vector, etc.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà.
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface Position<E> extends Serializable {
    /**
     * Retrieves the item stored in position
     *
     * @return item stored in position
     */
    E getElem();

}

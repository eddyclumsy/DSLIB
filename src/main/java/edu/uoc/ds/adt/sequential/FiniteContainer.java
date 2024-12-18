package edu.uoc.ds.adt.sequential;

/**
 * Interface extending container by adding the necessary operations operations
 * for containers with a limited number of items.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface FiniteContainer<E> extends Container<E> {
    /**
     * Check that the container is full.
     *
     * @return true if the container is full
     */
    boolean isFull();

}

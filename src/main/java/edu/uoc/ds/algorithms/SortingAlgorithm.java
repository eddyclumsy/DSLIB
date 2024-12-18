
package edu.uoc.ds.algorithms;

/**
 * Provides a standard interface for all algorithms
 * of arrangement. A single sort method is offered, which sorts the
 * elements of a vector.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

public interface SortingAlgorithm<E> {

    void sort(E[] vector, int n);

}

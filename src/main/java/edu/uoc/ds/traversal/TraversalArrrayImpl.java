package edu.uoc.ds.traversal;


import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.InvalidPositionException;

/**
 * Implementation of route on a vector. *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class TraversalArrrayImpl<E> implements Traversal<E> {

    private IteratorArrayImpl<Position<E>> iterator;

    public TraversalArrrayImpl(Position<E>[] elems, int numElems, int first) {
        iterator = new IteratorArrayImpl<>(elems, numElems, first);
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Position<E> next() throws InvalidPositionException {
        return iterator.next();
    }

}

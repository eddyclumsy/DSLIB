
package edu.uoc.ds.traversal;

import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.adt.sequential.LinkedList;

/**
 * Iterator implementation that allows iterating using as a base
 * the elements of one of a sequence of iterators.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

public class MultipleIterator<E> implements Iterator<E> {

    LinkedList<Iterator<E>> iterators;
    Iterator<Iterator<E>> iteradorDIteradors;
    Iterator<E> currentIterator;


    public MultipleIterator() {
        iterators = new LinkedList<>();
        iteradorDIteradors = null;
    }


    public void addIterator(Iterator<E> iterador) {
        iterators.insertEnd(iterador);
    }


    protected void init() {
        iteradorDIteradors = iterators.values();
        nextIterator();
    }


    protected void nextIterator() {
        boolean found = false;
        while (!found && iteradorDIteradors.hasNext()) {
            currentIterator = iteradorDIteradors.next();
            found = currentIterator.hasNext();
        }
        if (!found)
            currentIterator = null;
    }


    public boolean hasNext() {
        if (iteradorDIteradors == null)
            init();
        return currentIterator != null;
    }


    public E next() throws InvalidPositionException {
        if (iteradorDIteradors == null)
            init();
        E element = currentIterator.next();
        if (!currentIterator.hasNext())
            nextIterator();
        return element;
    }

}

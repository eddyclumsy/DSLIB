
package edu.uoc.ds.traversal;

import edu.uoc.ds.util.Utils;

/**
 * Iterator implementation to go through the elements of a
 * collection that uses a vector as representation.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

public class IteratorArrayImpl<E> implements Iterator<E> {

    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    private E[] elems;
    private int numberOfElems;
    private int index;
    private int step;

    public IteratorArrayImpl(E[] elements, int numberOfElems, int first, int step) {
        this.elems = elements;
        this.numberOfElems = numberOfElems;
        this.index = first;
        this.step = step;
    }

    public IteratorArrayImpl(E[] elements, int numberOfElems, int first) {
        this(elements, numberOfElems, first, 1);
    }

    public boolean hasNext() {
        return numberOfElems > 0;
    }

    public E next() {
        E element = elems[index];
        index = index + step;
        if (index == elems.length) index = 0;
        numberOfElems--;
        return element;
    }

}

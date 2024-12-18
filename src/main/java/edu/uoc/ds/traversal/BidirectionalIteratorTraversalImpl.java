package edu.uoc.ds.traversal;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the two-way traversal operations of the
 * elements of a container, by delegation in a route of positions.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class BidirectionalIteratorTraversalImpl<I, T> extends IteratorTraversalImpl<I, T>
        implements BidirectionalIterator<I> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Constructor with a parameter.
     *
     * @param traversal recorregut que proporciona els elements
     */
    public BidirectionalIteratorTraversalImpl(Traversal<T> traversal) {
        super(traversal);
    }

    /**
     * Checks if there is a last or previous item. is sensitive to
     * possible alterations to the structure of positions. Returns false if
     * the container is empty or the first element has already been visited.
     *
     * @return true or false, depending on whether it can be rolled back or not
     */
    public boolean hasPrevious() {
        return ((BidirectionalTraversal<T>) traversal).hasPrevious();
    }

    /**
     * Accessor for reading the last or previous element of the enumeration.
     *
     * @return last or previous element to the current one
     * @throws InvalidPositionException if the above is to be obtained
     * element of the enumeration and none or none
     * more
     */
    public I previous() throws InvalidPositionException {
        Position<T> posicio = ((BidirectionalTraversal<T>) traversal).previous();
        return getElem(posicio);
    }
}

package edu.uoc.ds.traversal;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the traversal operations of the elements of a
 * container, by delegation in a route of positions.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class IteratorTraversalImpl<E, T> implements Iterator<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Route that implements operations by delegation.
     */
    protected Traversal<T> traversal;

    /**
     * Constructor with a parameter.
     *
     * @param traversal provides the elements
     */
    public IteratorTraversalImpl(Traversal<T> traversal) {
        this.traversal = traversal;
    }

    /**
     * Checks if there is a first or second element. is sensitive to
     * possible alterations to the structure of positions. Returns false if
     * the container is empty or the last item has already been visited.
     *
     * @return true or false, depending on whether it can be advanced or not
     */
    public boolean hasNext() {
        return traversal.hasNext();
    }

    /**
     * Accessor for reading the first or next element of the enumeration.
     *
     * @return first or second element in the current one
     * @throws InvalidPositionException if you want to get the following
     * element of the enumeration and none or none
     * more
     */
    public E next() throws InvalidPositionException {
        return getElem(traversal.next());
    }

    /**
     * Accessor for reading the item stored in the position.
     *
     * @param pos position containing the element
     * @return element contained in position
     * @pre pos!=null, InvalidPositionException
     * @see IteratorTraversalImpl#next()
     */
    protected E getElem(Position<T> pos) {
        return (E) pos.getElem();
    }

}

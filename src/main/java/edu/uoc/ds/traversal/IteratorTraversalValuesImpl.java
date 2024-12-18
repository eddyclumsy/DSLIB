package edu.uoc.ds.traversal;

import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the traversal operations of the values of the
 * elements of a container, by delegation in a route of positions.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class IteratorTraversalValuesImpl<C, E> extends IteratorTraversalImpl<E, KeyValue<C, E>> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Constructor with a parameter.
     *
     * @param traversal provides the elements
     */
    public IteratorTraversalValuesImpl(Traversal<KeyValue<C, E>> traversal) {
        super(traversal);
    }

    /**
     * Accessor for reading the item stored in the position.
     *
     * @param pos position containing the element
     * @return element contained in position
     * @see IteratorTraversalImpl#next()
     */
    @Override
    protected E getElem(Position<KeyValue<C, E>> pos) {
        return pos.getElem().getValue();
    }
}

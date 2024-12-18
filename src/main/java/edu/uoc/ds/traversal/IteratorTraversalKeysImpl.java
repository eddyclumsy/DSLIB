package edu.uoc.ds.traversal;

import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the traversal operations of the keys
 * elements of a container, by delegation in a route of positions.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class IteratorTraversalKeysImpl<K, V> extends IteratorTraversalImpl<K, KeyValue<K, V>> {
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
    public IteratorTraversalKeysImpl(Traversal<KeyValue<K, V>> traversal) {
        super(traversal);
    }

    /**
     * Accessor de lectura de l'element emmagatzemat a la posici�.
     *
     * @param pos posici� que cont� l'element
     * @return element contingut a la posici�
     * @see IteratorTraversalImpl#next()
     */
    @Override
    protected K getElem(Position<KeyValue<K, V>> pos) {
        return pos.getElem().getKey();
    }
}

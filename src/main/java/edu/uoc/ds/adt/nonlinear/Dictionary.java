package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.sequential.Container;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Iterator;

/**
 * Interface that defines the operations of a dictionary.
 * <p>
 * Dictionaries are structures that store items with a key
 * associated. The key must have an equality operation. In the case
 * of the ordered dictionaries must allow the total ordering between keys.
 * Some implementations allow repeated keys and some do not. The element
 * associated with the key can be any object.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface Dictionary<K, V> extends Container<V> {

    /**
     * Add an item with an associated key, if possible.
     *
     * @param key key associated with the item to be added
     * @param value element to add to the dictionary
     */
    void put(K key, V value);

    /**
     * Checks for an item with a certain key.
     *
     * @param key key associated with an item
     * @return true or false, depending on whether or not you find the key
     */
    boolean containsKey(K key);

    /**
     * Retrieves the item associated with a key.
     *
     * @param key reference key
     * @return element associated with the key
     */
    V get(K key);

    /**
     * Delete the first matching key and associated item, if possible.
     *
     * @param key reference key
     * @return item associated with deleted key
     */
    V delete(K key);

    /**
     * Retrieves the items in the container.
     * Returns an enumeration. You can get a list with a couple of
     * lines of code: <PRE>
     *   for (Iterator it = adt.values(); it.hasNext();)
     *     System.out.println (it.next ()); </PRE>
     * To enumerate is simply to enunciate one after the other (things
     * of a series, the parts of a whole). But if the container is defined
     * some sort of order or route, the enumeration must be
     * consequent and offer the items in order (FIFO, LIFO, order,
     * etc.), without altering the current state of the container.
     *
     * @return enumeration of container keys
     * @throws InvalidPositionException if you want to get the following
     * element of the enumeration and none or none
     * m�s
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    Iterator<K> keys();
}

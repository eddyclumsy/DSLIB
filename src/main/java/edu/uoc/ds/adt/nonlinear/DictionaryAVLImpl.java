package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.exceptions.IllegalArgumentException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.IteratorTraversalKeysImpl;
import edu.uoc.ds.traversal.IteratorTraversalValuesImpl;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the operations of a dictionary by delegation
 * an AVL binary tree.
 * <p>
 * Dictionaries are structures that store items with a key
 * associated. The key must have an equality operation. In the case
 * of the ordered dictionaries must allow the total ordering between keys.
 * Some implementations allow repeated keys and some do not. The element
 * associated with the key can be any object.
 * <p>
 * Elements of the KeyValue class are used in this implementation,
 * that matches the key and the associated value. If there is no comparator
 * specificComparableValue Key is used that implements the interface
 * java.lang.Comparable, by delegation in the key.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class DictionaryAVLImpl<K, V> implements Dictionary<K, V> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * AVL binary tree.
     */
    protected AVLTree<KeyValue<K, V>> avl;

    /**
     * Constructor without parameters. The classes of the elements must
     *  implement the java.lang.Comparable interface.
     */
    public DictionaryAVLImpl() {
        avl = new AVLTree<>();
    }

    /**
     * Constructor with a parameter and elements of a class comparable to
     * the given comparator.
     *
     * @param comparator comparator that allows you to deduce the priority
     * @throws IllegalArgumentException if the comparator is null
     */
    public DictionaryAVLImpl(java.util.Comparator<KeyValue<K, V>> comparator)
            throws IllegalArgumentException {
        avl = new AVLTree<>(comparator);
    }

    /**
     * Retrieves the number of items in the container.
     *
     * @return number of items it currently contains
     */
    public int size() {
        return avl.size();
    }

    /**
     * Method to check if the container is empty.
     *
     * @return true or false, depending on whether it is empty or not
     */
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    /**
     * Add an item with an associated key, if possible. If you find one
     * element with the same key overwrites it.
     *
     * @param key key associated with the item to be added
     * @param value element to add to the dictionary
     * @see KeyValue
     */
    public void put(K key, V value) {
        avl.add(new KeyValue<>(key, value));
    }

    /**
     * Checks for an item with a certain key.
     *
     * @param key key associated with an item
     * @return true or false, depending on whether or not you find the key
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Retrieves the item associated with a key.
     *
     * @param key reference key
     * @return element associated with the key; or null, if it was not there
     * @see KeyValue
     */
    public V get(K key) {
        V result = null;
        KeyValue<K, V> aux = new KeyValue<>(key, null);
        KeyValue<K, V> keyValue = avl.get(aux);
        if (keyValue != null)
            result = keyValue.getValue();
        return result;
    }

    /**
     * Delete the first matching key and associated item, if possible.
     *
     * @param key reference key
     * @return deleted item associated with key;
     * or null, if it was not there
     * @see KeyValue
     */
    public V delete(K key) {
        V result = null;
        KeyValue<K, V> aux = new KeyValue<>(key, null);
        KeyValue<K, V> keyValue = avl.delete(aux);
        if (keyValue != null)
            result = keyValue.getValue();
        return result;
    }

    /**
     * Accessory for reading the items in the container.
     * Returns an enumeration. You can get a list with a couple of
     * lines of code: <PRE>
     *   for (Iterator it = adt.values(); it.hasNext();)
     *     System.out.println (it.next ()); </PRE>
     *
     * To enumerate is simply to enunciate one after the other (things
     * of a series, the parts of a whole). But if the container is defined
     * some sort of order or route, the enumeration must be
     * consequent and offer the items in order (FIFO, LIFO, order,
     * etc.), without altering the current state of the container.
     *
     * @return enumeration of sorted container keys
     * @throws InvalidPositionException if you want to get the following
     * element of the enumeration and none
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    public Iterator<K> keys() {
        return new IteratorTraversalKeysImpl<>(avl.inOrderTraversal());
    }

    /**
     * Accessory for reading the items in the container.
     * Returns an enumeration. You can get a list with a couple of
     * lines of code:
     *
     * <PRE>
     *   for (Iterator it = adt.values(); it.hasNext();)
     *     System.out.println (it.next ());
     * </PRE>
     *
     * To enumerate is simply to enunciate one after the other (things
     * of a series, the parts of a whole). But if the container is defined
     * some sort of order or route, the enumeration must be
     * consequent and offer the items in order (FIFO, LIFO, order,
     * etc.), without altering the current state of the container.
     *
     * @return enumeration of items associated with keys
     * @throws InvalidPositionException if you want to get the following
     * element of the enumeration and there are none or none
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    public Iterator<V> values() {
        return new IteratorTraversalValuesImpl<>(avl.inOrderTraversal());
    }

    /**
     * Method overwriting Object.toString (). Take out pairs of items.
     * Separate one pair from the next with the line break from the
     * platform.
     *
     * @return list of keys (in brackets) followed by the item
     * associated with each key
     */
    public String toString() {
        return Utils.delegatedContainerToString("DictionariAVLImpl", avl);
    }

}

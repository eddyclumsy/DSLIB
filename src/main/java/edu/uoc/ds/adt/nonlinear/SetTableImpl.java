package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.sequential.AbstractSet;
import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.NonComparableException;
import edu.uoc.ds.exceptions.IllegalArgumentException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the basic operations of a set for
 * delegation in a scatter table.
 * <p>
 * Sets are structures that store non-repeating elements. The
 * class of objects must have an equality operation.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

public class SetTableImpl<E> extends AbstractSet<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * the hash table.
     */
    protected HashTable<E, E> hashTable;


    /**
     * Constructor without parameters (default table size).
     */
    public SetTableImpl() {
        hashTable = new HashTable<>();
    }


    /**
     * Constructor with a parameter (table of the given size).
     *
     * @param size size of the hash table
     * @throws IllegalArgumentException if the size is negative
     * @pre size> = 0, new ExceptionParameterIncorrect ("negative size")
     */
    public SetTableImpl(int size) {
        hashTable = new HashTable<>(size);
    }


    /**
     * Retrives the number of items in the container.
     *
     * @return number of items it currently contains
     */
    public int size() {
        return hashTable.size();
    }


    /**
     * Method to check if the container is empty.
     *
     * @return true or false, depending on whether it is empty or not
     */
    public boolean isEmpty() {
        return hashTable.isEmpty();
    }


    /**
     * Add an item, if possible. Use the same item as a key
     * and as a value. It is implemented for interface compatibility.
     * It is recommended to use add (key, value).
     *
     * @param elem item to add to the set
     */
    public void add(E elem) {
        hashTable.put(elem, elem);
    }


    /**
     * Checks for an item with a certain key.
     *
     * @param elem key associated with an item
     * @return true or false, depending on whether or not you find the key
     * @throws EmptyContainerException if the table is empty
     * @throws NonComparableException if item is not
     * comparable
     */
    public boolean contains(E elem) {
        return hashTable.get(elem) != null;
    }


    /**
     * Delete the key and associated item, if possible.
     *
     * @param key reference key
     * @return deleted item associated with key;
     * or null, if it was not there
     * @throws EmptyContainerException if the table is empty
     * @throws NonComparableException if item is not
     * comparable
     * @see KeyValue
     */
    public E delete(E key) {
        return hashTable.delete(key);
    }


    /**
     * Retrieves the items in the container.
     * Returns an enumeration. You can get a list with a couple of
     * lines of code: <PRE>
     *   for (Iterator it = adt.values(); it.hasNext();)
     *     System.out.println (it.next());
     * </PRE>
     * To enumerate is simply to enunciate one after the other (things
     * of a series, the parts of a whole). But if the container is defined
     * some sort of order or route, the enumeration must be
     * consequent and offer the items in order (FIFO, LIFO, order,
     * etc.), without altering the current state of the container.
     *
     * @return unlisted container key enumeration
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    public Iterator<E> values() {
        return hashTable.values();
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
        return Utils.delegatedContainerToString("ConjuntTaulaImpl", hashTable);
    }
}

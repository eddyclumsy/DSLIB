package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.*;
import edu.uoc.ds.exceptions.IllegalArgumentException;
import edu.uoc.ds.traversal.IteratorTraversalKeysImpl;
import edu.uoc.ds.traversal.IteratorTraversalValuesImpl;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the operations of a dictionary by means of a
 * indirect scatter table, known as table * Class that implements the operations of a dictionary using a
 * indirect scattering table, known as a chained table
 * open (separate chaining). This is probably the easiest way to
 * resolve collisions. The scatter function is accessed
 * the index of the vector and the synonyms are chained from it
 * position.
 * <p>
 * Dictionaries are structures that store items with a key
 * associated. The key must have an equality operation and the element
 * associated with the key can be any object.
 * <p>
 * Elements of the KeyValue class are used in this implementation
 * which overwrites, by delegation in the key, the hashCode() function.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class HashTable<K, V> implements Dictionary<K, V> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Default size of the table.
     */
    public static final int DEFAULT_CAPACITY = 257;


    /**
     * Number of items currently in the container.
     */
    protected int n = 0;


    /**
     * Array of linked nodes.
     */
    protected LinkedList<KeyValue<K, V>>[] table;


    /**
     * Constructor without parameters (default table size).
     */
    public HashTable() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * Constructor with a parameter. Create a vector with the given size.
     *
     * @param size size of the table
     * @throws IllegalArgumentException if the size is negative
     * @pre-size> = 0, new ExceptionParameterIncorrect ("size cannot be negative")
     */
    public HashTable(int size) throws IllegalArgumentException {
        table = new LinkedList[size];
    }


    /**
     * Retrieves the number of items in the container.
     *
     * @return number of items it currently contains
     */
    public int size() {
        return n;
    }


    /**
     * Method to check if the container is empty.
     *
     * @return true or false, depending on whether it is empty or not
     */
    public boolean isEmpty() {
        return (n == 0);
    }


    /**
     * Add an item with an associated key, if possible. If you find one
     * element with the same key overwrites it.
     *
     * @param key key associated with the item to be added
     * @param value element to add to the dictionary
     * @throws ADTException if the key is null
     * @see KeyValue
     */
    public void put(K key, V value) {
        KeyValue<K, V> kv = new KeyValue<>(key, value);
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        Position<KeyValue<K, V>> position = null;
        if (synonyms == null)
            synonyms = createSynonymsList(index, kv);
        else
            position = seekKeyInSynonyms(synonyms, key);
        if (position != null)
            synonyms.update(position, kv);
        else {
            synonyms.insertBeginning(kv);
            n++;
        }
    }


    /**
     * Checks for an item with a certain key.
     *
     * @param key key associated with an item
     * @return true or false, if you find the key
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }


    /**
     * Retrieves the value associated with a key.
     *
     * @param key reference key
     * @return element KeyValue associated with the key;
     * or null, if it was not there
     * @throws NonComparableException if item is not
     * comparable
     * @see KeyValue
     */
    public V get(K key) {
        V objectFound = null;
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        Position<KeyValue<K, V>> position = seekKeyInSynonyms(synonyms, key);
        if (position != null) {
            KeyValue<K, V> kv = position.getElem();
            objectFound = kv.getValue();
        }
        return objectFound;
    }


    /**
     * Delete the first matching key and associated item, if possible.
     *
     * @param key reference key
     * @return deleted item associated with key;
     * or null, if it was not there
     * @throws EmptyContainerException if the table is empty
     * @throws NonComparableException if item is not
     * comparable
     * @see KeyValue
     */
    public V delete(K key) {
        V oldValue = null;
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms != null) {
            Traversal<KeyValue<K, V>> synonymsTraversal = synonyms.positions();
            Position<KeyValue<K, V>> position = null;
            Position<KeyValue<K, V>> prevPosition = null;
            boolean found = false;
            while (synonymsTraversal.hasNext() && !found) {
                prevPosition = position;
                position = synonymsTraversal.next();
                KeyValue<K, V> kvPosition = position.getElem();
                found = key.equals(kvPosition.getKey());
            }
            if (found) {
                oldValue = position.getElem().getValue();
                if (synonyms.size() == 1)
                    deleteSynonymsList(index);
                else
                    synonyms.deleteNext(prevPosition);
                n--;
            }
        }
        return oldValue;
    }


    protected int calculateIndex(K key) {
        int hash = key.hashCode();
        // trim the sign bit and adjust the index to the range [0..max-1]:
        return (hash & 0x7FFFFFFF) % table.length;
    }


    protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
        table[index] = new LinkedList<>();
        return table[index];
    }


    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }


    protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
        Position<KeyValue<K, V>> position = null;
        boolean found = false;
        if (synonyms != null) {
            Traversal<KeyValue<K, V>> synonymsTraversal = synonyms.positions();
            while (synonymsTraversal.hasNext() && !found) {
                position = synonymsTraversal.next();
                KeyValue<K, V> kvPosicio = position.getElem();
                found = key.equals(kvPosicio.getKey());
            }
        }
        return found ? position : null;
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
    public Iterator<K> keys() {
        return new IteratorTraversalKeysImpl<>(new NodeTraversal<>(this));
    }

    /**
     * Retrieves the items in the container.
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
     * @return enumeration of items associated with keys
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    public Iterator<V> values() {
        return new IteratorTraversalValuesImpl<>(new NodeTraversal<>(this));
    }

    /**
     * Class that provides a traversal of the positions. Based on the
     * pattern Iterator, supports multiple simultaneous routes and
     * independent of the container. It is sensitive to possible alterations of
     * the structure of positions.
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */
    protected static class NodeTraversal<K, V> implements Traversal<KeyValue<K, V>> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * Hash table being traversed.
         */
        protected HashTable<K, V> hashTable;

        /**
         * Auxiliary traversal.
         */
        protected Traversal<KeyValue<K, V>> SynonymsTraversal = null;

        /**
         * Table index.
         */
        protected int i = 0;

        public NodeTraversal(HashTable<K, V> hashTable) {
            this.hashTable = hashTable;
            SynonymsTraversal = null;
            i = 0;
            seekNext();
        }


        /**
         * Check for a first or next position. It is sensitive to
         * possible alterations to the position structure. Return false
         * if the container is empty or the last position has already been visited.
         *
         * @return true or false, depending on whether you can advance or not
         */
        public boolean hasNext() {
            return SynonymsTraversal != null && SynonymsTraversal.hasNext();
        }


        /**
         * Forward first, if possible, and then return to position.
         * If there is no next position throw an exception.
         *
         * @return next position
         * @pre hasNext()
         */
        public Position<KeyValue<K, V>> next() {
            if (!hasNext())
                throw new InvalidPositionException();
            Position<KeyValue<K, V>> position = SynonymsTraversal.next();
            seekNext();
            return position;
        }


        private void seekNext() {
            while (i < hashTable.table.length && (SynonymsTraversal == null || !SynonymsTraversal.hasNext())) {
                if (hashTable.table[i] != null)
                    SynonymsTraversal = hashTable.table[i].positions();
                else
                    SynonymsTraversal = null;
                i++;
            }
        }

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
        return Utils.containerToString("HashTable", new NodeTraversal<K, V>(this));
    }

}

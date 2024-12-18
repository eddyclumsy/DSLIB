/*
 */
package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.nonlinear.Dictionary;
import edu.uoc.ds.traversal.IteratorTraversalKeysImpl;
import edu.uoc.ds.traversal.IteratorTraversalValuesImpl;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.util.Utils;

/**
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class DictionaryLinkedListImpl<K, V> implements Dictionary<K, V> {


    protected LinkedList<KeyValue<K, V>> dictionary;


    // Classes internes per a implementacio

    protected static class TraversalWithPrevious<E> implements Traversal<E> {

        // Atributs

        Traversal<E> traversal;
        Position<E> curr;
        Position<E> prev;


        // Constructor

        public TraversalWithPrevious(Traversal<E> r) {
            traversal = r;
            prev = null;
            curr = null;
        }



        public Position<E> next() {
            prev = curr;
            curr = traversal.hasNext() ? traversal.next() : null;
            return curr;
        }

        public boolean hasNext() {
            return traversal.hasNext();
        }


        public Position<E> previous() {
            return prev;
        }

        public Position<E> current() {
            return curr;
        }
    }


    public DictionaryLinkedListImpl() {
        dictionary = new LinkedList<>();
    }



    protected TraversalWithPrevious<KeyValue<K, V>> searchPosition(K clau) {
        TraversalWithPrevious<KeyValue<K, V>> r = new TraversalWithPrevious<>(dictionary.positions());
        boolean found = false;
        while (!found && r.hasNext()) {
            KeyValue<K, V> keyValue = r.next().getElem();
            found = clau.equals(keyValue.getKey());
        }
        if (!found)
            r.next();
        return r;
    }

    public void put(K key, V value) {
        if (!containsKey(key))
            dictionary.insertBeginning(new KeyValue<>(key, value));
    }


    public boolean containsKey(K key) {
        TraversalWithPrevious<KeyValue<K, V>> r = searchPosition(key);
        return r.current() != null && key.equals(r.current().getElem().getKey());
    }


    public V get(K key) {
        TraversalWithPrevious<KeyValue<K, V>> r = searchPosition(key);
        Position<KeyValue<K, V>> actual = r.current();
        return actual != null ? actual.getElem().getValue() : null;
    }


    public Iterator<K> keys() {
        return new IteratorTraversalKeysImpl<>(dictionary.positions());
    }


    public V delete(K key) {
        V elem = null;
        TraversalWithPrevious<KeyValue<K, V>> r = searchPosition(key);
        Position<KeyValue<K, V>> current = r.current();
        if (current != null && key.equals(current.getElem().getKey())) {
            elem = current.getElem().getValue();
            if (r.previous() == null)
                dictionary.deleteFirst();
            else
                dictionary.deleteNext(r.previous());
        }
        return elem;
    }


    public boolean isEmpty() {
        return dictionary.isEmpty();
    }


    public int size() {
        return dictionary.size();
    }


    public Iterator<V> values() {
        return new IteratorTraversalValuesImpl<>(dictionary.positions());
    }


    public String toString() {
        return Utils.delegatedContainerToString("DictionaryListImpl", dictionary);
    }

}

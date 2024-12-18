/*
 */
package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.adt.nonlinear.Dictionary;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.IteratorArrayImpl;
import edu.uoc.ds.traversal.Iterator;

/**
 * Implementation of a dictionary using an array.
 * This specific implementation is designed for very specific cases
 * in which on the one hand a dictionary is required where, once initialized,
 * we will always have the same number of elements (known as input); and for
 * on the other hand, we need access to the key value pair vector.
 * Specifically used in the algorithm for calculating the minimum paths of a graph
 * provided by the library.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class DictionaryArrayImpl<K, V> implements Dictionary<K, V>, FiniteContainer<V> {


    protected KeyValue<K, V>[] dictionary;
    protected int n;


    // Constructors

    public DictionaryArrayImpl(int n) {
        dictionary =  new KeyValue[n];
    }


    public KeyValue<K, V>[] getVector() {
        return dictionary;
    }


    public void put(K key, V value) {
        if (isFull()) throw new FullContainerException();
        int i;
        for (i = 0; i < n && !dictionary[i].getKey().equals(key); i++) ;

        dictionary[i] = new KeyValue<>(key, value);
        if (i == n)
            n++;
    }


    public V delete(K key) {
        V elem = null;
        int i;
        for (i = 0; i < n && !dictionary[i].getKey().equals(key); i++) ;

        if (i < n) {
            elem = dictionary[i].getValue();
            dictionary[i] = dictionary[n - 1];
            dictionary[n - 1] = null;
            n--;
        }
        return elem;
    }


    public boolean containsKey(K key) {
        int i;
        for (i = 0; i < n && !dictionary[i].getKey().equals(key); i++) ;
        return i < n;
    }


    public V get(K key) {
        int i;
        for (i = 0; i < n && !dictionary[i].getKey().equals(key); i++) ;
        return i < n ? dictionary[i].getValue() : null;
    }


    public boolean isEmpty() {
        return n == 0;
    }


    public int size() {
        return n;
    }


    public Iterator<K> keys() {
        return new KeysIterator<>(new IteratorArrayImpl<>(dictionary, n, 0));
    }


    public Iterator<V> values() {
        return new ValuesIterator<>(new IteratorArrayImpl<>(dictionary, n, 0));
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("{DiccionariVectorImpl: ");
        int i = 0;
        while (i < n) {
            sb.append(dictionary[i]);
            i++;
            if (i < n) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean isFull() {
        return n == dictionary.length;
    }


    protected static class KeysIterator<K, V> implements Iterator<K> {

        private Iterator<KeyValue<K, V>> keyValueIterator;

        public KeysIterator(Iterator<KeyValue<K, V>> iterador) {
            keyValueIterator = iterador;
        }

        public boolean hasNext() {
            return keyValueIterator.hasNext();
        }

        public K next() {
            if (!hasNext())
                throw new InvalidPositionException();
            return keyValueIterator.next().getKey();
        }

    }


    protected static class ValuesIterator<K, V> implements Iterator<V> {

        private Iterator<KeyValue<K, V>> iteradorClauValor;

        public ValuesIterator(Iterator<KeyValue<K, V>> iterador) {
            iteradorClauValor = iterador;
        }

        public boolean hasNext() {
            return iteradorClauValor.hasNext();
        }

        public V next() {
            if (!hasNext())
                throw new InvalidPositionException();
            return iteradorClauValor.next().getValue();
        }
    }
}

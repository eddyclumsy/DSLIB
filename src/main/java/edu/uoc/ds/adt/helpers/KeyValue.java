package edu.uoc.ds.adt.helpers;

import edu.uoc.ds.util.Utils;

/**
 * Class that pairs two objects. The first (key) must be identified
 * the pair, the second element (value) stores information
 * associated with the key and can be any object.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class KeyValue<K, V> implements java.io.Serializable, Comparable<KeyValue<K, V>> {
    /**
     * Attribute that determines the compatibility between
     * serializable objects of the same class. It is calculated
     * using a method in the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Key or index to identify.
     */
    protected K key;

    /**
     * Value associated with the key. It can have zero value.
     */
    protected V value;

    /**
     * Constructor with two parameters.
     *
     * @param key   the key (comparable)
     * @param value the value
     */
    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the key or index
     *
     * @return the Key
     */
    public K getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the new key
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(V value) {
        this.value = value;
    }
    /**
     * Gets the value
     *
     * @return the value associated with the key
     */
    public V getValue() {
        return value;
    }

    /**
     * Method that overwrites Object.hashCode (). Delegate in the same method
     * implemented or inherited by the key.
     *
     * @return hashcode of the key
     */
    public int hashCode() {
        return key.hashCode();
    }

    /**
     * Method that overwrites Object.equals (Object obj). Delegate in the
     * same method implemented or inherited by the key.
     * @return true if the object received as a parameter is of class
     * KeyValue and has the same key; otherwise it returns false
     *
     */
    public boolean equals(Object clauValor) {
        if (clauValor == null) return false;
        return key.equals(((KeyValue) clauValor).getKey());
    }


    /**
     * Method implemented by Comparable.compareTo (Object o). Delegate in the
     * same method implemented or inherited by the key.
     *
     * @param keyValue object of the same class (KeyValue)
     * @return a negative, zero, or positive integer, depending on whether the key
     *        of this object (this.key) is smaller, equal, or
     *        greater than the key received as to parameter (keyValue.key)
     *
     */
    public int compareTo(KeyValue<K, V> keyValue) {
        return ((Comparable<K>) key).compareTo(
                (keyValue).getKey());
    }


    /**
     * Method that redefines the conversion of the object to String by
     * facilitate code debugging.
     * @return character string with key and value
     *
     */
    public String toString() {
        return ("[" + key + ": " + value + "]");
    }
}

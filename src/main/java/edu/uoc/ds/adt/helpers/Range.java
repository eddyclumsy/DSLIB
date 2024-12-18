package edu.uoc.ds.adt.helpers;

import edu.uoc.ds.util.Utils;

/**
 *
 * Class that represents a position in an implemented container
 * using a vector representation. It has the utility of:
 * (a) From a Range object, the implementation of
 * container can constantly access the position of the vector
 * that stores it; and
 * (b) be able to change items within the container
 * without invalidating external references to items (of Position / Rank type)
 * previously created.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà.
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 *
 * @version 2.1.0
 */
public class Range<E> implements Position<E> {

    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * the element
     */
    private E elem;

    /**
     * index of the item inside the container.
     */
    private int index;

    /**
     * Constructor with two parameters.
     *
     * @param index  index of the item inside the container.
     * @param elem value of the element.
     */
    public Range(int index, E elem) {
        this.index = index;
        this.elem = elem;
    }

    /**
     * Setter of the attribute {@code element}
     *
     * @param elem nou valor de l'element contingut al node
     */
    public void setElem(E elem) {
        this.elem = elem;
    }


    /**
     * Getter of the attribute {@code elem}
     *
     * @return the element
     */
    public E getElem() {
        return elem;
    }


    /**
     * Setter of the attribute {@code index}.
     *
     * @param index position index inside the container.
     */
    public void setIndex(int index) {
        this.index = index;
    }


    /**
     * Getter of the attribute {@code index}
     *
     * @return non-negative integer
     */
    public int getIndex() {
        return index;
    }


    /**
     * Method that redefines the conversion of the object to String by
     * facilitate code debugging. Delegate toString() method of
     * item stored in position.
     *
     * @return Returns a String with the pattern "[index, value]"
     */
    public String toString() {
        return (elem == null) ? ("[" + index + ",null]") :
                ("[" + index + "," + elem.toString() + "]");
    }
}

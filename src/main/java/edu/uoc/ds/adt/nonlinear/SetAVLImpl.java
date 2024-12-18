package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.sequential.AbstractSet;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements the operations of a set by delegation in one
 * AVL binary tree.
 * <p>
 * Sets are structures that store non-repeating elements. The
 * class of objects must have an equality operation.
 * <p>
 * In this implementation the class of the elements is expected
 * implement the java.lang.Comparable interface or provide a
 * java.util.Comparator as the constructor parameter.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

public class SetAVLImpl<E> extends AbstractSet<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * AVL binary tree (by delegation)
     */
    protected AVLTree<E> avl;


    /**
     * Constructor without parameters. The classes of the elements are expected
     * implement the java.lang.Comparable interface.
     */
    public SetAVLImpl() {
        avl = new AVLTree<>();
    }


    /**
     * Constructor with a parameter and elements of a class comparable to
     * the given comparator.
     *
     * @param comparator comparator that allows you to deduce the priority
     */
    public SetAVLImpl(java.util.Comparator<E> comparator) {
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
     * Add an item, if possible.
     *
     * @param elem item to add to the set
     */
    public void add(E elem) {
        avl.add(elem);
    }

    /**
     * Check for an item.
     *
     * @param elem reference element
     * @return true or false, depending on whether or not the item is found
     */
    public boolean contains(E elem) {
        return avl.get(elem) != null;
    }

    /**
     * Delete an item, if possible.
     *
     * @param elem reference element
     * @return item deleted; or null, if it was not there
     */
    public E delete(E elem) {
        return avl.delete(elem);
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
    public Iterator<E> values() {
        return avl.values();
    }

    /**
     * Method overwriting Object.toString (). Remove separate items
     * for the platform line jump.
     *
     * @return list of items in a messy path
     */
    public String toString() {
        return Utils.delegatedContainerToString("ConjuntAVLImpl", avl);
    }
}

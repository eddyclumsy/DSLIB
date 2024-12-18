
package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.exceptions.IllegalArgumentException;
import edu.uoc.ds.traversal.Iterator;

/**
 * Class that implements those set operations that do not
 * depend on the chosen representation (AVL, Scattering Tables, ...).
 * These operations correspond to those they deal with
 * with subsets.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

public abstract class AbstractSet<E> implements Set<E> {

    /**
     * Adds the elements of a second set that do not exist in the
     * current set, if possible. If you find an equivalent item, according to the
     * based on comparison, overwrite it.
     *
     * @param set set that wants to join the current one;
     * may be empty, but not null
     * @throws IllegalArgumentException if the set is null
     * @pre conj! = null, new IllegalArgumentException ("null set");
     */
    public void union(Set<E> set) {
        Iterator<E> iter = set.values();
        while (iter.hasNext())
            add(iter.next());
    }

    /**
     * Delete items from the current set that do not exist in the
     * second set, if possible.
     *
     * @param set set that you want to intersect with the current one;
     * may be empty, but not null
     * @throws IllegalArgumentException if the set is null
     * @pre conj! = null, new ExceptionParameterIncorrect ("null set");
     *
     */
    public void intersection(Set<E> set) {
        List<E> elems2Delete = new LinkedList<>();
        // First we list the items we will have
        // to delete from the set
        Iterator<E> iter = values();
        while (iter.hasNext()) {
            E elem = iter.next();
            if (!set.contains(elem))
                elems2Delete.insertEnd(elem);
        }
        // second we delete the items
        iter = elems2Delete.values();
        while (iter.hasNext())
            delete(iter.next());
    }

    /**
     * Delete items that exist in a second from the current set
     * set, if possible.
     *
     * @param set set that you want to subtract from the current one;
     * may be empty, but not null
     * @throws IllegalArgumentException if the set is null
     * @pre conj! = null, new ExceptionParameterIncorrect (null set ")
     *
     */
    public void difference(Set<E> set) {
        Iterator<E> iter = set.values();
        while (iter.hasNext())
            delete(iter.next());
    }

}

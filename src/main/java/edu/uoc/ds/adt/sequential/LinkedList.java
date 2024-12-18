package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.IteratorTraversalImpl;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.util.Utils;

/**
 * Positional sequence that is characterized by having operations
 * based on the position of an item inside the container.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà.
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 * @see Position
 * @since 1.5
 */
public class LinkedList<E> implements List<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Number of items currently in the container.
     */
    protected int n;

    /**
     * Position of the last item in the list. In this representation
     * circular, the next position is the first.
     */
    protected LinkedNode<E> last;


    /**
     * Empty list.
     */

    public LinkedList() {
        last = null;
        n = 0;
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
     * Add an item to the top of the list.
     *
     * @param elem item to add to the list
     * @return new position containing item
     */
    public Position<E> insertBeginning(E elem) {
        return newPosition(last, elem);
    }


    /**
     * Add an item to the bottom of the list.
     *
     * @param elem item to add to the list
     * @return new position containing item
     */
    public Position<E> insertEnd(E elem) {
        last = newPosition(last, elem);
        return last;
    }

    /**
     * Add a list to the bottom of the list.
     *
     * @param list the list to add
     * @return new position containing the last item
     */
    public Position<E> insertAll(List<E> list) {
        Iterator<E> it = list.values();
        Position<E> last = null;
        while (it.hasNext()) {
            last = insertEnd(it.next());
        }
        return last;
    }


    /**
     * Add an item before the received position.
     *
     * @param elem item to add to the list
     * @param node position node
     * @return new position containing item
     * @throws InvalidPositionException whether the position is null or not
     * valid
     */
    public Position<E> insertBefore(Position<E> node, E elem) {
        Position<E> newNode;
        LinkedNode<E> prev = previous((LinkedNode<E>) node);
        if (prev == last)
            newNode = insertBeginning(elem);
        else
            newNode = insertAfter(prev, elem);
        return newNode;
    }


    /**
     * Add an item after the received position.
     *
     * @param elem item to add to the list
     * @param node position node
     * @return new position containing item
     * @throws InvalidPositionException whether the position is null or not
     * valid
     * @pre node! = null, InvalidPosition Exception
     */
    public Position<E> insertAfter(Position<E> node, E elem) {
        Position<E> newNode;
        if (last == node)
            newNode = insertEnd(elem);
        else
            newNode = newPosition((LinkedNode<E>) node, elem);
        return newNode;
    }


    /**
     * Delete the first position in the list.
     *
     * @return item in position
     * @throws EmptyContainerException if the list is empty
     * @pre isEmpty (), ExcepcioContenidorBuit
     */
    public E deleteFirst() {
        if (isEmpty())
            throw new EmptyContainerException();
        LinkedNode<E> primer = last.getNext();
        if (n == 1)
            last = null;
        else
            last.setNext(primer.getNext());
        n--;
        return primer.getElem();
    }


    /**
     * Delete received position.
     *
     * @param node position to be deleted
     * @return item in position
     * @throws InvalidPositionException whether the position is null or not
     * valid
     * @pre node! = null, InvalidPosition Exception
     */
    public E delete(Position<E> node) {
        if (n == 1)
            last = null;
        else {
            LinkedNode<E> curr = (LinkedNode<E>) node;
            LinkedNode<E> prev = previous(curr);
            prev.setNext(curr.getNext());
            if (curr == last) last = prev;
        }
        n--;
        return node.getElem();
    }


    /**
     * Delete the next position.
     *
     * @param node position previous to the one to be deleted; if is null
     * the first position is deleted
     * @return item in the next position
     * @throws InvalidPositionException if the position is invalid
     * @pre! isEmpty() && node! = last, InvalidPositionException
     */
    public E deleteNext(Position<E> node) {
        if (node == last)
            throw new InvalidPositionException();
        E deletedElem;
        if (node == null)
            deletedElem = deleteFirst();
        else {
            LinkedNode<E> curr = (LinkedNode<E>) node;
            LinkedNode<E> next = curr.getNext();
            curr.setNext(next.getNext());
            if (next == last) last = curr;
            deletedElem = next.getElem();
            n--;
        }
        return deletedElem;
    }


    /**
     * Replaces the item contained in the received position.
     *
     * @param elem new item
     * @param node position node
     * @return item in position
     * @throws InvalidPositionException whether the position is null or not
     * valid
     * @pre! isEmpty () && node! = null, InvalidPositionException
     */
    public E update(Position<E> node, E elem) {
        E old = node.getElem();
        ((LinkedNode<E>) node).setElem(elem);
        return old;
    }


    /**
     * Exchange items contained in received positions.
     *
     * @param node1 first of the two reference positions
     * @param node2 second of the two reference positions
     * @throws InvalidPositionException if any position is null or not
     * valid
     * @pre! isEmpty() && node1! = null && node2! = null, InvalidPositionException
     */
    public void swap(Position<E> node1, Position<E> node2) {
        E tmp = node1.getElem();
        ((LinkedNode<E>) node1).setElem(node2.getElem());
        ((LinkedNode<E>) node2).setElem(tmp);
    }


    /**
     * Returns the node before the received one as a parameter. In this list
     * simply chained the operation has a linear time cost: O (n).
     *
     * @param node reference position, not null
     * @return node before received as parameter
     * @pre size()>1
     */
    protected LinkedNode<E> previous(LinkedNode<E> node) {
        LinkedNode<E> prev = last;
        boolean found = false;
        while (!found) {
            found = prev.getNext() == node;
            if (!found)
                prev = prev.getNext();
        }
        return prev;
    }


    /**
     * Creates a chained node, places it next to the receipt as
     * parameterize and increase the number of items. It can be overwritten
     * for subclasses that need to work with descendants of
     * Chained Node.
     *
     * @param node position preceding that of the new node; can be null
     * @param elem item to add to the list
     * New @return node containing the received item as a parameter; located
     * between the node received as a parameter and its next
     */
    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        LinkedNode<E> newNode = null;
        if (node == null) {                // ser� primer, darrer i �nic node
            newNode = new LinkedNode<>(elem, null);
            newNode.setNext(newNode);
            last = newNode;
        } else {
            newNode = new LinkedNode<>(elem, node.getNext());
            node.setNext(newNode);
        }
        n++;                              // incrementa el nombre d'elements
        return newNode;
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
        return new IteratorTraversalImpl<>(positions());
    }


    /**
     * Method that supports multiple paths, from the positions of the
     * container, simultaneous and independent of each other.
     *
     * @return enumeration of container positions
     */
    public Traversal<E> positions() {
        return new UnidirectionalTraversalList<>(this);
    }


    /**
     * Class that implements a node with a node chain, that joins a
     * node with the following in a single-stranded data structure.
     * Only facilitates basic operations: builders and accessories
     * reading and writing. It can store any item (Object).
     *
     * @author Jordi Àlvarez Canal
     * @author Esteve Mariné Gallisà.
     * Data Structures
     * Universitat Oberta de Catalunya (UOC)
     *
     * @version 2.1.0
     */
    protected static class LinkedNode<E> implements Position<E> {

        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * Element contained in the node.
         */
        private E value;

        /**
         * pointer to the next
         */
        protected LinkedNode<E> next;

        /**
         * Constructor without parameters.
         */
        public LinkedNode() {
            value = null;
            next = null;
        }

        /**
         * Constructor with a parameter.
         *
         * @param elem value of the element contained in the node
         */
        public LinkedNode(E elem) {
            value = elem;
            next = null;
        }

        /**
         * Constructor with two parameters.
         *
         * @param ne node chaining
         * @param elem value of the element contained in the node
         */
        public LinkedNode(E elem, LinkedNode<E> ne) {
            value = elem;
            next = ne;
        }

        /**
         * Setter for the value contained in the node.
         *
         * @param elem new value of the element contained in the node
         */
        public void setElem(E elem) {
            value = elem;
        }

        /**
         * Getter for the item contained in the node.
         *
         * @return element contained in node
         */
        public E getElem() {
            return value;
        }

        /**
         * Setter for the next node
         *
         * @param node value for node
         */
        public void setNext(LinkedNode<E> node) {
            next = node;
        }

        /**
         * Getter for the next node
         *
         * @return the next node
         */
        public LinkedNode<E> getNext() {
            return next;
        }

        /**
         * Method that redefines the conversion of the object to String by
         * facilitate code debugging. Delegate toString () method of
         * item stored in position.
         *
         * @return character string representative of the item
         */
        public String toString() {
            return "[LinkedNode: " + ((value == null) ? "null" : value.toString()) + "]";
        }
    }


    /**
     * Class that provides a tour of the positions. Based on the
     * pattern Iterator, supports multiple simultaneous routes and
     * independent of the container. It is sensitive to possible alterations of
     * the position structure and is updated according to the changes.
     *
     * @see Traversal#hasNext()
     * @see Traversal#next()
     */

    protected static class UnidirectionalTraversalList<E> implements Traversal<E> {

        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * Current node
         */
        protected LinkedNode<E> currentNode;

        /**
         * pointer to the last node
         */
        protected LinkedNode<E> last;

        /**
         * There may be a following (circular chain).
         */
        protected boolean hasNext;


        public UnidirectionalTraversalList(LinkedList<E> list) {
            currentNode = list.last;
            last = list.last;
            hasNext = (currentNode != null);
        }

        /**
         * Check for a first or next position. S sensitive to
         * possible alterations to the position structure. Return false
         * if it is empty or the last position has already been visited.
         *
         * @return true or false, depending on whether you can advance or not
         */
        public boolean hasNext() {
            return hasNext;
        }

        /**
         * Forward first, if possible, and then return to position.
         *
         * @return following position
         * @throws InvalidPositionException if the next position is not
         * exists
         * @pre hasNext(), new InvalidPositionException ("no next")
         */
        public Position<E> next() {
            if (!hasNext())
                throw new InvalidPositionException();
            currentNode = currentNode.getNext();                 // actual <-- actual.seg�ent
            hasNext = (currentNode != last);
            return currentNode;
        }
    }

    /**
     * Method overwriting Object.toString (). Remove separate items
     * for the platform line jump.
     *
     * @return list of items
     */
    public String toString() {
        return Utils.containerToString("LinkedList", new UnidirectionalTraversalList<E>(this));
    }

}

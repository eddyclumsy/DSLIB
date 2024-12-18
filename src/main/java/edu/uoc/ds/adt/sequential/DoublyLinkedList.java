package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.*;
import edu.uoc.ds.util.Utils;

/**
 * Sequence that is characterized by having operations
 * based on the position of an item inside the container.
 * <p>
 * Inherits from chained list structure with a single chain (node
 * next) and adds a second to optimize the operations that
 * need to know the previous node within the sequence.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 * @see Position
 */
public class DoublyLinkedList<E> extends LinkedList<E> {
    /**
     * Attribute that determines the compatibility between
     * serializable objects of the same class. It is calculated
     * using a method in the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Delete the first position in the list.
     *
     * @return item in position
     * @throws EmptyContainerException if the list is empty
     * @pre! isEmpty(), EmptyContainerException
     */
    @Override
    public E deleteFirst() throws EmptyContainerException {
        if (isEmpty()) throw new EmptyContainerException();
        DoublyLinkedNode<E> first = (DoublyLinkedNode<E>) last.getNext();
        if (n == 1)
            last = null;
        else {
            DoublyLinkedNode<E> second = (DoublyLinkedNode<E>) first.getNext();
            deleteTheMiddle((DoublyLinkedNode<E>) last, first, second);
        }
        n--;
        return first.getElem();
    }


    /**
     * Delete the indicated position
     *
     * @param node position to be deleted
     * @return item in position
     * @throws InvalidPositionException whether the position is null or not
     *                                  valid
     * @pre !isEmpty() && node!=null, InvalidPositionException
     */
    @Override
    public E delete(Position<E> node) {
        DoublyLinkedNode<E> actual = (DoublyLinkedNode<E>) node;
        if (n == 1)
            last = null;
        else {
            DoublyLinkedNode<E> seguent = (DoublyLinkedNode<E>) actual.getNext();
            DoublyLinkedNode<E> anterior = actual.getPrevious();
            deleteTheMiddle(anterior, actual, seguent);
        }
        n--;
        return actual.getElem();
    }


    /**
     * Delete the next position.
     *
     * @param node position above the one you want to delete; if is null,
     *             the first position is removed.
     * @return item in the next position
     * @throws InvalidPositionException if the position is invalid
     * @pre !isEmpty() && node!=last, InvalidPositionException
     */
    @Override
    public E deleteNext(Position<E> node) {
        if (node == last)
            throw new InvalidPositionException();
        E elem2Delete;
        if (node == null)
            elem2Delete = deleteFirst();
        else {
            DoublyLinkedNode<E> curr = (DoublyLinkedNode<E>) node;
            DoublyLinkedNode<E> next = (DoublyLinkedNode<E>) curr.getNext();
            DoublyLinkedNode<E> nextNext = (DoublyLinkedNode<E>) next.getNext();
            deleteTheMiddle(curr, next, nextNext);
            if (next == last)
                last = curr;
            elem2Delete = next.getElem();
        }
        n--;
        return elem2Delete;
    }


    /**
     * Returns the node before the received one as a parameter. In this list
     * doubly chained the operation has a temporary cost u: O (1).
     *
     * @param node reference position, not null
     * @return node before received as parameter
     */
    @Override
    protected LinkedNode<E> previous(LinkedNode<E> node) {
        return ((DoublyLinkedNode<E>) node).getPrevious();
    }


    /**
     * Private helper method that given three nodes placed consecutively in the list,
     * eliminates the middle one. This method is used for node deletion methods.
     * The method only removes one item from the list but does not update the number of items
     * of this.
     *
     * @param previous Previous item.
     * @param middle   Central element, is the item to be removed from the list.
     * @param next     Element posterior.
     */

    private void deleteTheMiddle(DoublyLinkedNode<E> previous, DoublyLinkedNode<E> middle, DoublyLinkedNode<E> next) {
        // removes the center item from the list
        previous.setNext(next);
        next.setPrevious(previous);
        // removes any reference from the central item to an item in the list
        middle.setPrevious(null);
        middle.setNext(null);
        if (middle == last)
            last = previous;
    }


    /**
     * Create a linked node. It can be overwritten by the subclasses that
     * need to work with descendants of Linked Node.
     *
     * @param node position preceding that of the new node; may be null
     * @param elem item to add to the list
     *
     * @return node containing the received item as a parameter; located
     * between the node received as a parameter and its next
     */
    @Override
    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        DoublyLinkedNode<E> newNode = null;

        if (node == null) {
            newNode = new DoublyLinkedNode<>(elem);
            newNode.setNext(newNode);
            newNode.setPrevious(newNode);
            last = newNode;
        } else {
            DoublyLinkedNode<E> curr = (DoublyLinkedNode<E>) node;
            DoublyLinkedNode<E> next = (DoublyLinkedNode<E>) curr.getNext();
            newNode = new DoublyLinkedNode<>(next, elem, (DoublyLinkedNode<E>) node);
            node.setNext(newNode);
            next.setPrevious(newNode);
        }
        n++;
        return newNode;
    }

    /**
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
     * @see BidirectionalIterator#hasPrevious()
     * @see BidirectionalIterator#previous()
     */
    public BidirectionalIterator<E> values(BidirectionalTraversal.traversalMode inici) {
        return new BidirectionalIteratorTraversalImpl<>(positions(inici));
    }

    /**
     * Method that supports multiple paths, from the positions of the
     * container, simultaneous and independent of each other.
     *
     * @return enumeration of container positions
     * @see BidirectionalTraversal
     */
    public BidirectionalTraversal<E> positions(BidirectionalTraversal.traversalMode start) {
        return new BidirectionalTraversalList<>(this, start);
    }


    /**
     * Class that implements a node with two node chains. The
     * interpretation of this pair of chains can be different: following
     * / above in a doubly chained list; left child / child
     * standing in a binary tree; etc.
     *
     * @author Jordi Àlvarez Canal
     * @author Esteve Mariné Gallisà
     * <p>
     * Data Structures
     * Universitat Oberta de Catalunya (UOC)
     * @version 2.1.0
     */
    protected static class DoublyLinkedNode<E> extends LinkedNode<E> {

        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * pointer to the previous node.
         */
        protected DoublyLinkedNode<E> prev;


        /**
         * Constructor without parameters.
         */
        public DoublyLinkedNode() {
            super();
            setPrevious(null);
        }


        /**
         * Constructor with a parameter.
         *
         * @param elem value of the element contained in the node
         */
        public DoublyLinkedNode(E elem) {
            super(elem);
            setPrevious(null);
        }


        /**
         * Constructor with three parameters.
         *
         * @param next pointer to next node
         * @param elem value of the element contained in the node
         * @param prev pointer to previous node
         */
        public DoublyLinkedNode(DoublyLinkedNode<E> next, E elem,
                                DoublyLinkedNode<E> prev) {
            super(elem, next);
            setPrevious(prev);
        }

        /**
         * Second pointer (previous node in one
         * sequence, right child in a binary tree, etc.)
         *
         * @return second node pointer
         */
        public DoublyLinkedNode<E> getPrevious() {
            return prev;
        }

        /**
         * Setter for the previous pointer
         *
         * @param node value for node
         */
        public void setPrevious(DoublyLinkedNode<E> node) {
            prev = node;
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
     * @see BidirectionalTraversal#hasPrevious()
     * @see BidirectionalTraversal#previous()
     */
    protected static class BidirectionalTraversalList<E> extends UnidirectionalTraversalList<E>
            implements BidirectionalTraversal<E> {
        /**
         * Attribute that determines compatibility between objects
         * serializable of the same class. It is calculated
         * using a method of the Utilities class.
         */
        private static final long serialVersionUID = Utils.getSerialVersionUID();


        /**
         * There may be previous (circular chain control).
         */
        protected boolean hasPrevious;

        /**
         * Constructor with a parameter.
         *
         * @param direction direction of the route; Valid values are:
         * -Bidirectional iterator.BEGINNING i
         * -IteratorBidirectional.END
         */
        public BidirectionalTraversalList(DoublyLinkedList<E> list, traversalMode direction) {
            super(list);
            if (direction == traversalMode.BEGINNING)
                hasPrevious = false;
            else {
                hasNext = false;
                if (last != null)
                    currentNode = last.getNext();
                hasPrevious = (currentNode != null);
            }
        }


        /**
         * Check for a last or previous position. S sensitive to
         * possible alterations to the position structure. Return false
         * if it is empty or the last position has already been visited.
         *
         * @return true or false, depending on whether you can backtrack or not
         */
        public boolean hasPrevious() {
            return (last != null) && hasPrevious;
        }

        /**
         * Forward first, if possible, and then return to position.
         *
         * @return next position
         * @throws InvalidPositionException if the next position is not
         * exists
         * @pre hasNext(), InvalidPositionException
         */
        @Override
        public Position<E> next() {
            hasPrevious = true;
            return super.next();
        }


        /**
         * First step back, if possible, and then return to position.
         *
         * @return previous position
         * @throws InvalidPositionException if the previous position did not
         * exists
         * @pre hasPrevious(), InvalidPositionException
         */
        public Position<E> previous() {
            if (!hasPrevious())
                throw new InvalidPositionException();
            currentNode = ((DoublyLinkedNode<E>) currentNode).getPrevious();

            hasPrevious = (currentNode != last.getNext());
            hasNext = true;
            return currentNode;
        }
    }
}

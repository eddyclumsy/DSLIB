package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.NonComparableException;
import edu.uoc.ds.exceptions.IllegalArgumentException;
import edu.uoc.ds.util.Utils;

import java.util.Comparator;

/**
 * Class that implements a binary search tree, which is characterized
 * to have the root greatest than all the elements of its subtree
 * left (if any) and smaller than all elements of its subtree
 * right (if any); and, in addition, its left and right subtrees (if any
 * ha) are also search trees.
 * <p>
 * The class implements non-positional add, delete, and
 * consult a comparable item, and only have accessibility within it
 * package because it inherits some positional operations that could alter
 * the consistency of the search tree.
 * <p>
 * The element class is expected to implement the interface
 * java.lang.Comparable or provide a java.util.Comparator as
 * constructor parameter.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
class BinarySearchTreeLinked<E> extends BinaryTreeLinkedImpl<E> {
    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Specific comparator that allows to deduce the priority between the
     * elements. It can have a null value and then the interface is used
     * java.lang.Comparable
     */
    protected java.util.Comparator<E> comparator;

    /**
     * Constructor without parameters. The class of items must
     * implement the interface java.lang.Comparable.
     */
    public BinarySearchTreeLinked() {
        super();
    }

    /**
     * Constructor with a parameter and elements of a class comparable to
     * the given comparator.
     *
     * @param comparator comparator that allows you to deduce the priority
     * @throws IllegalArgumentException if the comparator is null
     * @pre comparator! = null, ExceptionParameterIncorrect ("null comparator")
     */
    public BinarySearchTreeLinked(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Add an item to the appropriate position, if possible. Yes
     * the item was already there, according to the comparator, overwrite it.
     *
     * @param elemComp comparable item to add to the tree
     * @throws NonComparableException if item is not
     *                                comparable
     */
    public void add(E elemComp) {
        addAtSubTree(isEmpty() ? null : root(), elemComp);
    }

    /**
     * Delete the item, if you find it according to the comparator.
     *
     * @param elemComp comparable item to delete from tree
     * @return the deleted item; or null, if it was not there
     * @throws EmptyContainerException if the tree is empty
     * @throws NonComparableException  if item �s not
     *                                 comparable
     */
    public E delete(E elemComp) {
        return deleteAtSubTree(null, root(), elemComp);
    }

    /**
     * Retrieves an element of the tree. If you can't find it, using
     * the comparator, returns null.
     *
     * @param elemComp comparable item to consult
     * @return the interested item; or null, if it was not there
     * @throws EmptyContainerException if the tree is empty
     * @throws NonComparableException  if item �s not
     *                                 comparable
     */
    public E get(E elemComp) {
        return seekInSubTree(root(), elemComp);
    }

    /**
     * Recursive method that guarantees the orderly insertion of the elements.
     * Does not allow equivalent items: if the item existed, according to
     * the comparison operation overwrites it; otherwise it creates a new node
     * to store the item and chain it to the location of the tree that is attached to it
     * corresponds.
     *
     * @param parent reference position (initially root)
     * @param elem   comparable item to add to the tree
     * @return new son; or the root, if the father was null
     */
    private Position<E> addAtSubTree(Position<E> parent, E elem) {
        if (parent == null)
            return super.add(parent, elem);
        Position<E> node = null;
        int comp = compare(elem, parent.getElem());
        // depending on the result of comparing we act. There are three different cases.
        // comp==0 (element is equal to parent), overwrites the parent
        if (comp == 0) {
            super.update(parent, elem);
            node = parent;
        }
        // comp<0: discard the right tree and continue adding it to the left
        else if (comp < 0) {
            if (leftChild(parent) == null)
                node = addLeftChild(parent, elem);
            else
                node = addAtSubTree(leftChild(parent), elem);
        }
        // comp>0: discard left and continue right
        else {
            if (rightChild(parent) == null)
                node = addRightChild(parent, elem);
            else
                node = addAtSubTree(rightChild(parent), elem);
        }
        balance(parent);
        return node;
    }


    /**
     * Recursive method that deletes the item, if it finds it according to
     * comparator.
     *
     * @param grandparent reference position (initially null)
     * @param parent      reference position (initially root)
     * @param elem        comparable item to be deleted from the tree
     * @return item deleted; or null, if it was not there
     */
    private E deleteAtSubTree(Position<E> grandparent, Position<E> parent, E elem) {
        E elem2Delete = null;
        if (parent == null)
            return null;
        int comp = compare(elem, parent.getElem());
        if (comp < 0)                                    // left
            elem2Delete = deleteAtSubTree(parent, leftChild(parent), elem);
        else if (comp > 0)                                 // right
            elem2Delete = deleteAtSubTree(parent, rightChild(parent), elem);
        else {                                                  // 0: found
            elem2Delete = parent.getElem();
            if (isLeaf(parent))                              // if there is no children
                delete(grandparent, parent);                   // super.delete
            else if ((leftChild(parent) != null) &&
                    (rightChild(parent) == null))        // left child
                replaceSubTree(grandparent, parent, leftChild(parent)); // replace.
            else if ((leftChild(parent) == null) &&
                    (rightChild(parent) != null))        // right child
                replaceSubTree(grandparent, parent, rightChild(parent));    // replaceee
            else {                                            // two children
                Position<E> min = rightChild(parent);      // minimum value => right branch
                while (leftChild(min) != null)
                    min = leftChild(min);
                swap(parent, min);          // replace the node
                //  and deletes the contents of the right subtree
                deleteAtSubTree(parent, rightChild(parent), min.getElem());
            }
        }
        balance(grandparent);    // if necessary, balance the tree or subtree of nodes
        return elem2Delete;                                              // found
    }


    /**
     * Retrieves an element of the tree. If you can't find it,
     * according to the comparator, returns null.
     *
     * @param parent   reference position (initially root)
     * @param elemComp comparable item to consult
     * @return the item in question, or null if it doesn't exist
     * @throws NonComparableException if any item is not
     *                                comparable
     */
    private E seekInSubTree(Position<E> parent, E elemComp) throws NonComparableException {
        boolean found = false;
        int comp;
        while (parent != null && !found) {
            comp = compare(elemComp, parent.getElem());
            found = comp == 0;
            if (comp < 0) parent = leftChild(parent);
            else if (comp > 0) parent = rightChild(parent);
        }
        return found && parent != null ? parent.getElem() : null;
    }


    /**
     * Method to check if there is a specific comparator. If you are not there
     * java.lang.Comparable comparison operation is used.
     *
     * @return true or false, if  there is a specific comparator
     */
    public boolean thereIsComparator() {
        return (comparator != null);
    }


    /**
     * Protected method that compares received items. If there is specified
     * comparator, the method is delegated to the
     * java method.util.Comparator.compare (o1, o2); otherwise delegated to
     * the java.lang.Comparable.compareTo (o) method implemented by the
     * element class.
     *
     * @param elem1 first object of the same class
     * @param elem2 second object of the same class
     * @return a negative integer, zero or positive, depending on whether the first
     * element has less, equal or more priority than the second
     * element, according to the implementation of the comparator
     * @throws NonComparableException if any item is not
     *                                comparable
     * @pre elem1! = null && elem2! = null, NonComparableException
     */
    protected int compare(E elem1, E elem2) throws NonComparableException {
        int comp = 0;
        if (comparator == null)
            comp = ((Comparable<E>) elem1).compareTo(elem2);
        else
            comp = comparator.compare(elem1, elem2);
        return comp;
    }

    /**
     * Method to be implemented by the subclasses that need to be worked on
     * with balanced trees. The temporal efficiency of operations
     * individual access to the elements of a search tree depends
     * exclusively of its height and, in the worst case, can
     * become linear. There are tree variants that guarantee a cost
     * logarithmic based on reducing the number of levels. To balance
     * the tree must be treated by the nodes from the place of insertion
     * or deletion to the root of the tree. This method is called a
     * each step by add() and delete(); if it is not redefined it does not
     * nothing. But it gives the lower classes the opportunity to implement
     * a treatment that guarantees balance while taking advantage of it
     * add and delete code that they inherited.
     *
     * @param bst binary tree or subtree to be balanced
     */
    protected void balance(Position<E> bst) {
        throw new UnsupportedOperationException();
    }

}

package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.sequential.Container;
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.traversal.Traversal;

/**
 * Interface that defines the operations of any tree.
 * <p>
 * Trees are structures that relate their elements, called
 * nodes, forming hierarchies: every node (except the root that is the head of
 * the hierarchy) is descending from a single node, and can be ascending
 * of other nodes (when you have no descendants it is called a leaf). When a node
 * may have an indeterminate number of children let's talk about general trees
 * (general tree) and, if you have a fixed number N, of trees of order N (n-ary
 * tree); in the latter the case of N = 2 stands out, the so-called trees
 * binary (binary tree).
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface Tree<E> extends Container<E> {
    /**
     * Retrieve the tree root, if any.
     *
     * @return tree root
     */
    Position<E> root();

    /**
     * Method that supports multiple paths, from children positions
     *
     * @param parent the reference position
     * @return enumeration of child positions
     */
    Traversal<E> children(Position<E> parent);

    /**
     * Check if the tree or subtree has a child.
     *
     * @param pos reference position
     * @return false or true, depending on whether or not you have a child
     */
    boolean isLeaf(Position<E> pos);

    /**
     * Add an item as a new child from the received position, if possible.
     * If the parent is null, it is added to the root; if it is a leaf, it is added
     * as the first child.
     *
     * @param parent reference position
     * @param elem item to add to the tree
     * @return new child; or the root, if the parent was null
     */
    Position<E> add(Position<E> parent, E elem);

    /**
     * Replaces the item contained in the received position.
     *
     * @param elem new item
     * @param pos reference position
     * @return item in position
     */
    E update(Position<E> pos, E elem);

    /**
     * Swap items in the tree
     * positions received. The resulting tree will contain the same
     * items it had, except for the two items received as
     * to parameter, which will appear interchanged. <p>
     * Whether the position objects are exchanged, or what
     * the elements contained in the positions are exchanged, it will depend
     * for specific implementation of tree.
     *
     * @param pos1 first of the two reference positions
     * @param pos2 second of the two reference positions
     */
    void swap(Position<E> pos1, Position<E> pos2);

    /**
     * Delete the subtree represented by the child position, if possible.
     * If the parent position is null, delete the entire tree.
     *
     * @param parent position of the parent; can be null
     * @param child position of the child
     */
    void delete(Position<E> parent, Position<E> child);

    /**
     * Traversal of the tree positions.
     *
     * @return enumeration of container positions by level
     */
    Traversal<E> positions();

    /**
     * Traversal in Pre Order of the positions of the tree.
     *
     * @return enumeration of preorder container positions
     */
    Traversal<E> preOrderTraversal();

    /**
     * Traversal in  Post order of the positions of the tree.
     *
     * @return enumeration of post-order container positions
     */
    Traversal<E> postOrderTraversal();


    /**
     * Traversal by levels of the positions of the tree.
     *
     * @return enumeration of level-order container positions
     */
    Traversal<E> levelOrderTraversal();
}

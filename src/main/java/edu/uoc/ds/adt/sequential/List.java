package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Traversal;

/**
 * Positional sequence that is characterized by having operations
 * based on the position of an item inside the container.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface List<E> extends Container<E> {
    /**
     * Add an item to the top of the list.
     *
     * @param elem item to add to the list
     * @return new position containing item
     */
    Position<E> insertBeginning(E elem);

    /**
     * Add an item to the bottom of the list.
     *
     * @param elem item to add to the list
     * @return new position containing item
     */
    Position<E> insertEnd(E elem);

    /**
     * Add a list to the bottom of the list.
     *
     * @param list the list to add
     * @return new position containing the last item
     */
    Position<E> insertAll(List<E> list);

    /**
     * Add an item before the received position.
     *
     * @param elem item to add to the list
     * @param pos reference position
     * @return new position containing item
     * @throws InvalidPositionException whether the position is null or not
     * valid
     */
    Position<E> insertBefore(Position<E> pos, E elem);

    /**
     * Add an item after the received position.
     *
     * @param elem item to add to the list
     * @param pos reference position
     * @return new position containing item
     * @throws InvalidPositionException whether the position is null or not
     * valid
     */
    Position<E> insertAfter(Position<E> pos, E elem);

    /**
     * Delete the first position in the list.
     *
     * @return item in position
     * @throws EmptyContainerException if the list is empty
     */
    E deleteFirst();

    /**
     * Delete received position.
     *
     * @param pos position you want to delete
     * @return item in position
     * @throws InvalidPositionException whether the position is null or not
     * valid
     */
    E delete(Position<E> pos);

    /**
     * Delete the next position.
     *
     * @param node position above the one you want to delete
     * @return item in the next position
     * @throws InvalidPositionException whether the position is null or not
     * valid
     */
    E deleteNext(Position<E> node);

    /**
     * Replaces the item contained in the received position.
     *
     * @param elem new item
     * @param pos reference position
     * @return item in position
     * @throws InvalidPositionException whether the position is null or not
     * valid
     */
    E update(Position<E> pos, E elem);

    /**
     * Exchange items contained in received positions.
     *
     * @param pos1 first of the two reference positions
     * @param pos2 second of the two reference positions
     * @throws InvalidPositionException whether the position is null or not
     * valid
     */
    void swap(Position<E> pos1, Position<E> pos2);


    /**
     * @return A traversal of the positions.
     */
    Traversal<E> positions();

}

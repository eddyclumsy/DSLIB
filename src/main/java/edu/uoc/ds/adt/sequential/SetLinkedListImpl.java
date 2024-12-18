package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;

public class SetLinkedListImpl<E> extends AbstractSet<E> {
    List<E> data;

    public SetLinkedListImpl() {
        data = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Iterator<E> values() {
        return data.values();
    }

    @Override
    public void add(E elem) {
        if (!contains(elem)) {
            data.insertEnd(elem);
        }
    }

    @Override
    public boolean contains(E elem) {
        boolean found = false;
        Iterator<E> it = values();
        while (it.hasNext() && !found) {
            found = it.next().equals(elem);
        }
        return found;
    }

    @Override
    public E delete(E elem) {
        boolean found = false;
        Traversal<E> traversal = data.positions();
        Position<E> position = null;
        while (traversal.hasNext() && !found) {
            position = traversal.next();
            found = position.getElem().equals(elem);
        }
        if (found) {
           data.delete(position);
        }
        return (found?position.getElem():null);
    }
}

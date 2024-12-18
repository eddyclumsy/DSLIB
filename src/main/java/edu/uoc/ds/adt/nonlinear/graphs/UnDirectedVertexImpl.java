package edu.uoc.ds.adt.nonlinear.graphs;

import edu.uoc.ds.adt.sequential.DoublyLinkedList;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.util.Utils;

/**
 * Class that implements a vertex for the case of Directed Graphs.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

public class UnDirectedVertexImpl<E, L> extends VertexImpl<E, L> {

    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    protected DoublyLinkedList<Edge<L, E>> edges;


    public UnDirectedVertexImpl(E element) {
        super(element);
        edges = new DoublyLinkedList<>();
    }


    public void addEdge(Edge<L, E> edge) {
        Position<Edge<L, E>> position = edges.insertEnd(edge);
        EdgeImpl<L, E> edgeI = (EdgeImpl<L, E>) edge;
        if (this == edgeI.vertexA)
            edgeI.setPositionVertexAList(position);
        else
            edgeI.setPositionVertexBList(position);
    }


    public Iterator<Edge<L, E>> edges() {
        return edges.values();
    }


    public void deleteEdge(EdgeImpl<L, E> edge) {
        Position<Edge<L, E>> position;
        EdgeImpl<L, E> edgeI = edge;
        if (edgeI.vertexA == this)
            position = edgeI.positionVertextListA;
        else
            position = edgeI.positionVertexListB;
        edges.delete(position);
    }


}

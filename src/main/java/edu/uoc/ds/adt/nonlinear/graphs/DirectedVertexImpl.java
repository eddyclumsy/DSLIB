package edu.uoc.ds.adt.nonlinear.graphs;

import edu.uoc.ds.adt.sequential.DoublyLinkedList;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.MultipleIterator;
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

public class DirectedVertexImpl<E, L> extends VertexImpl<E, L> {

    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    protected DoublyLinkedList<Edge<L, E>> edgesWithSrc2Vertex;
    protected DoublyLinkedList<Edge<L, E>> edgesWithDest2Vertex;


    public DirectedVertexImpl(E element) {
        super(element);
        edgesWithSrc2Vertex = new DoublyLinkedList<>();
        edgesWithDest2Vertex = new DoublyLinkedList<>();
    }


    public void addEdge(Edge<L, E> edge) {
        EdgeImpl<L, E> edgeI = (EdgeImpl<L, E>) edge;
        if (edgeI.vertexA == this)
            edgeI.setPositionVertexAList(edgesWithSrc2Vertex.insertEnd(edge));
        if (edgeI.vertexB == this)
            edgeI.setPositionVertexBList(edgesWithDest2Vertex.insertEnd(edge));
    }


    public void deleteEdge(EdgeImpl<L, E> edge) {
        Position<Edge<L, E>> position;
        if (edge.vertexA == this) {
            position = edge.positionVertextListA;
            edgesWithSrc2Vertex.delete(position);
        } else {
            position = edge.positionVertexListB;
            edgesWithDest2Vertex.delete(position);
        }
    }


    public Iterator<Edge<L, E>> edges() {
        MultipleIterator<Edge<L, E>> edges = new MultipleIterator<>();
        edges.addIterator(edgesWithSrc2Vertex.values());
        edges.addIterator(edgesWithDest2Vertex.values());
        return edges;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        DirectedVertexImpl dobj = (DirectedVertexImpl) obj;
        return (dobj!=null?this.getValue().equals(dobj.getValue()): false);
    }
}
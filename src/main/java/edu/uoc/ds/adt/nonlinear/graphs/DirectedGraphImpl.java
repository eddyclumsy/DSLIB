
package edu.uoc.ds.adt.nonlinear.graphs;

import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.util.Utils;


/**
 * Implementaci� d'un graf dirigit mitjan�ant llistes d'adjac�ncia.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class DirectedGraphImpl<E, L> extends AbstractGraph<E, L>
        implements DirectedGraph<E, L> {


    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    public DirectedGraphImpl() {
        super();
    }


    protected VertexImpl<E, L> createVertex(E value) {
        return new DirectedVertexImpl<>(value);
    }


    public DirectedEdge<L, E> newEdge(Vertex<E> src,
                                      Vertex<E> dest) {
        DirectedVertexImpl<E, L> s = (DirectedVertexImpl<E, L>) src;
        DirectedVertexImpl<E, L> d = (DirectedVertexImpl<E, L>) dest;
        DirectedEdgeImpl<L, E> edge = new DirectedEdgeImpl<>(s, d);
        edge.add2Graph(this);
        return edge;
    }


    public DirectedEdge<L, E> getEdge(Vertex<E> src,
                                      Vertex<E> dest) {
        DirectedEdge<L, E> res = null;
        Iterator<Edge<L, E>> edges = edgesWithSource(src);
        while (edges.hasNext() && res == null) {
            DirectedEdge<L, E> edge = (DirectedEdge<L, E>) edges.next();
            if (edge.getVertexDst() == dest)
                res = edge;
        }
        return res;
    }


    public Iterator<Edge<L, E>> edgesWithSource(Vertex<E> vertex) {
        DirectedVertexImpl<E, L> v = (DirectedVertexImpl<E, L>) vertex;
        return v.edgesWithSrc2Vertex.values();
    }


    public Iterator<Edge<L, E>> edgedWithDestination(Vertex<E> vertex) {
        DirectedVertexImpl<E, L> v = (DirectedVertexImpl<E, L>) vertex;
        return v.edgesWithDest2Vertex.values();
    }

}

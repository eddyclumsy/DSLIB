
package edu.uoc.ds.adt.nonlinear.graphs;


import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.util.Utils;

/**
 * Implementation of an undirected graph using adjacency lists.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class UnDirectedGraphImpl<E, L> extends AbstractGraph<E, L>
        implements UnDirectedGraph<E, L> {


    /**
     * Attribute that determines compatibility between objects
     * serializable of the same class. It is calculated
     * using a method of the Utilities class.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    public UnDirectedGraphImpl() {
        super();
    }


    protected VertexImpl<E, L> createVertex(E value) {
        return new UnDirectedVertexImpl<>(value);
    }


    public UnDirectedEdge<L, E> newEdge(Vertex<E> vertex1,
                                        Vertex<E> vertex2) {
        UnDirectedVertexImpl<E, L> v1 = (UnDirectedVertexImpl<E, L>) vertex1;
        UnDirectedVertexImpl<E, L> v2 = (UnDirectedVertexImpl<E, L>) vertex2;
        UnDirectedEdgeImpl<L, E> edge = new UnDirectedEdgeImpl<>(v1, v2);
        edge.add2Graph(this);
        return edge;
    }


    public UnDirectedEdge<L, E> getEdge(Vertex<E> vertex1,
                                        Vertex<E> vertex2) {
        UnDirectedEdge<L, E> result = null;
        UnDirectedVertexImpl<E, L> v1 = (UnDirectedVertexImpl<E, L>) vertex1;
        Iterator<Edge<L, E>> edges = v1.edges();
        while (edges.hasNext() && result == null) {
            EdgeImpl<L, E> edge = (EdgeImpl<L, E>) edges.next();
            if (edge.incideixEnVertexs(vertex1, vertex2))
                result = (UnDirectedEdge<L, E>) edge;
        }
        return result;
    }

}

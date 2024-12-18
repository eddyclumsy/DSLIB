package edu.uoc.ds.adt.nonlinear.graphs;

import edu.uoc.ds.traversal.Iterator;

/**
 * DirectedGraph.java
 * Directed graph.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà.
 *
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 */
public interface DirectedGraph<E, L> extends Graph<E, L> {

    /**
     * Creates and includes a new edge in the graph.
     *
     * @pre n!=null && m!=null
     * @post (@ return.getVertex1()==n &&  @return.getVertex2()==m) ||
     * (@return.getVertex1()==m &&  @return.getVertex2()==n)
     */
    DirectedEdge<L, E> newEdge(Vertex<E> n, Vertex<E> m);

    /**
     * Gets the edge (if any) between vertices n and m.
     * Returns null if there is no edge between them.
     *
     * @pre n !=null && m!=null
     * @post (@ return.vertexs().contains(m) &&  @return.vertexs().contains(n))||@return==null
     */
    DirectedEdge<L, E> getEdge(Vertex<E> n, Vertex<E> m);

    /**
     * Gets an iterator to the edges originating from v.
     *
     * @pre n!=null && m!=null
     * @post forall @return.value()=> getVertexSrc()==v
     */
    Iterator<Edge<L, E>> edgesWithSource(Vertex<E> v);

    /**
     * Gets an iterator to the bound edges in v.
     *
     * @pre n!=null && m!=null
     * @post forall @return.value()=> getVertexDst()==v
     */
    Iterator<Edge<L, E>> edgedWithDestination(Vertex<E> v);
}

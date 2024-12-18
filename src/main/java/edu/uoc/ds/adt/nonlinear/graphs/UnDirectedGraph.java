package edu.uoc.ds.adt.nonlinear.graphs;

/**
 * Undirected graph.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface UnDirectedGraph<E, L> extends Graph<E, L> {

    /**
     * Creates and includes a new edge in the graph.
     *
     * @pre n!=null && m!=null && n!=m
     * @post (@ return.getVextex1 ()==n &&  @return.getVertex2()==m) ||
     * (@return.getVextex1()==m &&  @return.getVertex2()==n)
     */
    UnDirectedEdge<L, E> newEdge(Vertex<E> n, Vertex<E> m);

    /**
     * Gets the edge that connects two given vertices.
     *
     * @pre n!=null & m!=null & n!=m
     * @post (@ return.getVextex1 ()==n &&  @return.getVertex2()==m) ||
     * (@return.getVextex1()==m &&  @return.getVertex2()==n)
     */
    UnDirectedEdge<L, E> getEdge(Vertex<E> n, Vertex<E> m);

}

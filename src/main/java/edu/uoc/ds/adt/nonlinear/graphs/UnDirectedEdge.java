package edu.uoc.ds.adt.nonlinear.graphs;

/**
 * UnDirectedEdge.java
 * Edge of an undirected graph.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface UnDirectedEdge<L, E> extends Edge<L, E> {

    Vertex<E> getVertex1();

    Vertex<E> getVertex2();


}

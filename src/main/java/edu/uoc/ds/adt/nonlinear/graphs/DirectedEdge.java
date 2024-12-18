package edu.uoc.ds.adt.nonlinear.graphs;

/**
 Class representing a directed edge
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface DirectedEdge<L, E> extends Edge<L, E> {


    Vertex<E> getVertexSrc();
    Vertex<E> getVertexDst();

}

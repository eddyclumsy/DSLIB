package edu.uoc.ds.adt.nonlinear.graphs;

import java.io.Serializable;

/**
 * Edge of a graph (directed or undirected).
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà.
 * Estructura de la Informació,
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 * @since 1.5
 */
public interface Edge<L, E> extends Serializable {

    /**
     * Gets the value associated with the edge.
     *
     * @return null if the graph is unrated.
     */
    L getLabel();

    /**
     * Sets the value associated with the edge.
     *
     * @pre true
     * @post getValue () @ pre == v
     */
    void setLabel(L v);

    /**
     * Indicates whether the edge affects the vertex v.
     *
     * @pre v! = null
     * @post true
     */
    boolean edgeInVertex(Vertex<E> v);

}

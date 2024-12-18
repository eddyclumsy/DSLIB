package edu.uoc.ds.adt.nonlinear.graphs;

/**
 * Vertex (node) of a graph.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà.
 * Estructura de la Informació,
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.0.0
 * @since 1.5
 */
public interface Vertex<E> extends java.io.Serializable {

    /**
     * Gets the object with information associated with the Vertex.
     */
    E getValue();

}

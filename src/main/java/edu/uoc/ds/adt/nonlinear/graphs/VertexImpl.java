
package edu.uoc.ds.adt.nonlinear.graphs;

import edu.uoc.ds.traversal.Iterator;


/**
 * Classe que implementa un v�rtex.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

abstract class VertexImpl<E, L> implements Vertex<E> {

    /**
     * Node value.
     */
    private E element;


    /**
     * Construct a vertex from its value.
     *
     * @param element
     */
    protected VertexImpl(E element) {
        this.element = element;
    }


    /**
     * Returns the value of the node.
     */
    public E getValue() {
        return element;
    }


    /**
     * Returns the set of edges of the graph in which the node participates.
     *
     * @return Set of edges in which the node participates.
     */
    public abstract Iterator<Edge<L, E>> edges();


    /**
     * Removes the reference to an edge.
     *
     * @param edge Edge from which we want to remove the reference.
     */
    public abstract void deleteEdge(EdgeImpl<L, E> edge);


    /**
     * Adds a reference to the edge as the edge connecting the vertex
     * with another vertex.
     *
     * @param edge The added edge.
     */
    public abstract void addEdge(Edge<L, E> edge);


    /**
     * Implementaci� del m�tode heretat d'Object.
     */
    public int hashCode() {
        return element.hashCode();
    }


    /**
     * Implementation of the inherited method of Object.
     */
    public boolean equals(Object vertex) {
        if (vertex!=null && getClass() != vertex.getClass()) return false;
        return vertex!=null && element.equals(((VertexImpl<E, L>) vertex).getValue());
    }


    /**
     * Implementation of the inherited method of Object.
     */
    public String toString() {
        return "(Vertex:" + element + ")";
    }

}

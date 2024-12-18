package edu.uoc.ds.adt.nonlinear.graphs;


import edu.uoc.ds.traversal.Iterator;

/**
 * graph.java
 * Graph (directed or not).
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public interface Graph<E, L> {

    /**
     * Gets an iterator to the vertices of the graph.
     *
     * @pre true
     * @post @return.size()>=1 (a graph must have at least one vertex,
     * according to the mathematical definition).
     */
    Iterator<Vertex<E>> vertexs();


    /**
     * Returns the number of vertexes in the graph..
     *
     * @return n
     */
    int numVertexs();

    /**
     * Gets an iterator to the edges of the graph.
     *
     */
    Iterator<Edge<L, E>> edges();

    /**
     * Creates a new vertex with an associated value, and includes it in the graph.
     *
     * @pre value != null
     * @post vertex().size()=@old.vertexs().size()+1
     * @post @return.get().equals(value);
     */
    Vertex<E> newVertex(E value);

    /**
     * Removes the vertex from the graph (if it was in it), including
     * all the edges in which it participates.
     *
     * @pre n!=null
     * @post @old.vertexs().contains(n)=>vertexs().size()=@old.vertexs().size()-1
     */
    void deleteVertex(Vertex<E> vertex);


    /**
     * Returns the vertex corresponding to a determined element.
     *
     * @param elem
     * @return v
     */
    Vertex<E> getVertex(E elem);


    /**
     * Removes the edge from the graph (if it was in it)
     *
     * @pre a !=null
     * @post @old.edges().contains(a)=>edges().size()=@old.edges().size()-1
     */
    void deleteEdge(Edge<L, E> edge);


    /**
     * gets an iterator to vertices adjacent to vertex v.
     *
     * @pre v!=null
     * @post true
     */
    Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex);

}

package edu.uoc.ds.adt.nonlinear.graphs;


/**
 * Class that represents the edge of a graph (directed or undirected).
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
abstract class AbstractEdge<L, E> implements Edge<L, E> {
    /**
     * First vertex of the edge. In the case of a directed edge, it represents
     * the origin.
     */
    protected Vertex<E> vertexA;

    /**
     * Second vertex of the edge. In the case of a directed edge it represents
     * the destination.
     */
    protected Vertex<E> vertexB;

    /**
     * Value of the edge tag, or null if the relation has no value.
     */
    protected L label;


    /**
     * Constructor with two parameters.
     *
     * @param vertex1 first vertex of the edge (or origin in the case of directed)
     * @param vertex2 second vertex of the edge (or destination in the case of directed)
     * @pre vertex1! = null && vertex2! = null
     */
    protected AbstractEdge(Vertex<E> vertex1, Vertex<E> vertex2) {
        this.vertexA = vertex1;
        this.vertexB = vertex2;
    }


    /**
     * Getter for label. The value associated with the edge.
     *
     * @return edge value; or null, if it is an unrated relationship
     */
    public L getLabel() {
        return label;
    }


    /**
     * Setter for the value associated with the edge.
     *
     * @param label value of the relationship
     * @pre true
     * @post getValue() @ pre == value
     */
    public void setLabel(L label) {
        this.label = label;
    }


    /**
     * Indicates whether the edge affects a given vertex, as a source, or
     * destination.
     *
     * @param vertex vertex of which you want to know if it belongs to the edge
     * @return true if the edge hits the vertex; false otherwise
     * @pre vertext != null
     */
    public boolean edgeInVertex(Vertex<E> vertex) {
        return (this.vertexA.equals(vertex) || this.vertexB.equals(vertex));
    }


    /**
     * Method overwride Object.equals (Object obj). Delegate in the
     * same methods implemented or inherited by the vertexs.
     *
     * @return true if the received object as a parameter is of the class
     * Directed Edge and has the same vertices in the same order;
     * otherwise returns false
     */
    @Override
    public boolean equals(Object object) {
        if (object != null && getClass() != object.getClass())
            return false;
        AbstractEdge<L, E> edge = (AbstractEdge<L, E>) object;
        return (edge!=null && vertexA.equals(edge.vertexA) &&
                vertexB.equals(edge.vertexB));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    /**
     * Method overwriting Object.toString (). Delegate to them
     * methods implemented or inherited by vertices and by the label (yes
     * there are).
     *
     * @return character string with first vertex, tag if in
     * has and the second vertex
     */
    public String toString() {
        return "(" + vertexA + ":" + label + ":" + vertexB + ")";
    }
}

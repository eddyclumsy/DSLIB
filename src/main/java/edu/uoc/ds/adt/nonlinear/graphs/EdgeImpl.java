
package edu.uoc.ds.adt.nonlinear.graphs;


import edu.uoc.ds.adt.helpers.Position;

/**
 * Classe que implementa una aresta.
 *
 * @param <L>
 * @param <E>
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

abstract class EdgeImpl<L, E> implements Edge<L, E> {

    protected VertexImpl<E, L> vertexA;
    protected VertexImpl<E, L> vertexB;


    /**
     * Etiqueta de l'aresta.
     */
    protected L label;


    /**
     * Posicio de l'aresta a la llista d'arestes de GrafAbstracte.
     * Implementa el patr� Locator, i d'aquesta manera aconseguim
     * poder esborrar l'aresta de la llista en un temps constant
     * (ja que no cal buscar-la).
     */
    protected Position<Edge<L, E>> posicioLlistaArestesGraf;

    protected Position<Edge<L, E>> positionVertextListA;

    protected Position<Edge<L, E>> positionVertexListB;


    protected EdgeImpl(VertexImpl<E, L> vertex1, VertexImpl<E, L> vertex2) {
        this.vertexA = vertex1;
        this.vertexB = vertex2;
    }


    /**
     * Retorna l'etiqueta associada a l'aresta.
     */
    public L getLabel() {
        return label;
    }


    /**
     * Modifica el valor de l'etiqueta associada al node.
     */
    public void setLabel(L etiqueta) {
        this.label = etiqueta;
    }


    public void setPositionGraphList(Position<Edge<L, E>> posicio) {
        posicioLlistaArestesGraf = posicio;
    }

    public void setPositionVertexAList(Position<Edge<L, E>> posicio) {
        positionVertextListA = posicio;
    }

    public void setPositionVertexBList(Position<Edge<L, E>> posicio) {
        positionVertexListB = posicio;
    }


    /**
     * Consulta si l'aresta incideix en un v�rtex.
     */
    public boolean edgeInVertex(Vertex<E> vertex) {
        return (this.vertexA.equals(vertex) || this.vertexB.equals(vertex));
    }


    /**
     * Consulta si l'aresta incideix en tots dos v�rtexs a la vegada, sense importar l'ordre.
     *
     * @param vertex1
     * @param vertex2
     * @return
     */
    public boolean incideixEnVertexs(Vertex<E> vertex1, Vertex<E> vertex2) {
        return edgeInVertex(vertex1) && edgeInVertex(vertex2);
    }


    /**
     * El.limina l'aresta del graf.
     *
     * @param graf Graf al qual pertany l'aresta.
     */
    public void eliminarDelGraf(AbstractGraph<E, L> graf) {
        graf.edgesList.delete(posicioLlistaArestesGraf);
        vertexA.deleteEdge(this);
        vertexB.deleteEdge(this);
    }


    /**
     * Afegeix l'aresta al graf. Els v�rtexs associats a l'aresta han de pert�nyer al graf.
     *
     * @param graf Graf al qual s'afegeix l'aresta.
     */
    public void add2Graph(AbstractGraph<E, L> graf) {
        posicioLlistaArestesGraf = graf.edgesList.insertEnd(this);
        vertexA.addEdge(this);
        vertexB.addEdge(this);
    }


    /**
     * donat un v�rtex pertanyent a l'aresta, aquest m�tode retorna l'altre v�rtex
     * pertanyent a l'aresta.
     *
     * @param vertex
     * @return
     */
    public Vertex<E> extremAlternatiu(Vertex<E> vertex) {
        return (vertex == this.vertexA) ? vertexB : vertexA;
    }


    /**
     * Redefinici� d'aquest m�tode de object.
     */
    @Override
    public boolean equals(Object object) {
        if (object!=null && getClass() != object.getClass())
            return false;
        EdgeImpl<L, E> edge = (EdgeImpl<L, E>) object;
        return edge!=null && vertexA.equals(edge.vertexA) &&
                vertexB.equals(edge.vertexB);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    /**
     * Redefinici� d'aquest m�tode de object.
     */
    public String toString() {
        return "(" + vertexA + ":" + label + ":" + vertexB + ")";
    }

}

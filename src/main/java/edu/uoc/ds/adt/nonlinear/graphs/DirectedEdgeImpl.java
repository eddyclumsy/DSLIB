package edu.uoc.ds.adt.nonlinear.graphs;

import edu.uoc.ds.util.Utils;

/**
 * Class representing an edge of a directed graph. The relationship between
 * two vertices of the same domain and goes from the origin vertex to the destination vertex.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
class DirectedEdgeImpl<L, E> extends EdgeImpl<L, E>
        implements DirectedEdge<L, E> {
    /**
     * Atribut que en Java 1.5 determina la compatibilitat entre objectes
     * serialitzables de la mateixa classe. L'deitificador es calcula
     * mitjan�ant un m�tode de la classe Utilitats.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Constructor amb dos par�metres.
     *
     * @param origen v�rtex origen de l'aresta
     * @param desti  v�rtex dest� de l'aresta
     * @throws ExcepcioParametreIncorrecte si algun v�rtex �s null
     * @pre origen!=null && desti!=null, ExcepcioParametreIncorrecte
     */
    public DirectedEdgeImpl(DirectedVertexImpl<E, L> origen, DirectedVertexImpl<E, L> desti) {
        super(origen, desti);
    }

    /**
     * Accessor de lectura del v�rtex origen de la relaci� dirigida.
     *
     * @return v�rtex origen
     */
    public Vertex<E> getVertexSrc() {
        return vertexA;
    }

    /**
     * Accessor de lectura del v�rtex dest� de la relaci� dirigida.
     *
     * @return v�rtex dest�
     */
    public Vertex<E> getVertexDst() {
        return vertexB;
    }


}
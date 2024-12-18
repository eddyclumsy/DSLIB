package edu.uoc.ds.adt.nonlinear.graphs;

import edu.uoc.ds.util.Utils;

/**
 * Classe que representa una aresta d'un graf dirigit. La relaci� �s entre
 * dos v�rtexs del mateix domini i va del v�rtex origen al de dest�.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class UnDirectedEdgeImpl<L, E> extends EdgeImpl<L, E>
        implements UnDirectedEdge<L, E> {
    /**
     * Atribut que en Java 1.5 determina la compatibilitat entre objectes
     * serialitzables de la mateixa classe. L'deitificador es calcula
     * mitjan�ant un m�tode de la classe Utilitats.
     */
    private static final long serialVersionUID = Utils.getSerialVersionUID();


    /**
     * Constructor amb dos par�metres.
     *
     * @param v1 v�rtex A de l'aresta
     * @param v2 v�rtex B de l'aresta
     * @throws ExcepcioParametreIncorrecte si algun v�rtex �s null
     * @pre v1!=null && v2!=null, ExcepcioParametreIncorrecte
     */
    public UnDirectedEdgeImpl(UnDirectedVertexImpl<E, L> v1, UnDirectedVertexImpl<E, L> v2) {
        super(v1, v2);
    }

    public Vertex<E> getVertex1() {
        return vertexA;
    }

    public Vertex<E> getVertex2() {
        return vertexB;
    }


}
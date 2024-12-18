package edu.uoc.ds.adt.nonlinear.graphs;


import org.junit.Before;

/**
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class UnDirectedGraphTest extends GraphTest {


    @Before
    public void setUp() {
        graph = new UnDirectedGraphImpl<>();
        init();
    }


    @Override
    protected Edge<Integer, Character> newEdge(
            Graph<Character, Integer> graph, Vertex<Character> v1,
            Vertex<Character> v2) {
        return ((UnDirectedGraph<Character, Integer>) graph).newEdge(v1, v2);
    }


    @Override
    protected Edge<Integer, Character> getEdge(
            Graph<Character, Integer> graf, Vertex<Character> v1,
            Vertex<Character> v2) {
        return ((UnDirectedGraph<Character, Integer>) graf).getEdge(v1, v2);
    }

}

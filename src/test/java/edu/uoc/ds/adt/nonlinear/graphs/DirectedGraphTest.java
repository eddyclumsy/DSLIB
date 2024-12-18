package edu.uoc.ds.adt.nonlinear.graphs;


import edu.uoc.ds.adt.helpers.KeyValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.algorithms.MinimumPaths;

/**
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class DirectedGraphTest extends GraphTest {

    @Before
    public void setUp() {
        graph = new DirectedGraphImpl<>();
        init();
    }


    @Override
    protected Edge<Integer, Character> newEdge(
            Graph<Character, Integer> graf, Vertex<Character> v1,
            Vertex<Character> v2) {
        return ((DirectedGraph<Character, Integer>) graf).newEdge(v1, v2);
    }

    @Override
    protected Edge<Integer, Character> getEdge(
            Graph<Character, Integer> graf, Vertex<Character> v1,
            Vertex<Character> v2) {
        return ((DirectedGraph<Character, Integer>) graf).getEdge(v1, v2);
    }


    @Test
    public void minimunPathsTest() {
        MinimumPaths minimumPaths = new MinimumPaths();

        Vertex<Character> vertexA = graph.getVertex('A');
        KeyValue<Vertex<Character>, Number>[] keyValues = minimumPaths.calculate((DirectedGraph) graph, vertexA);

        Assert.assertEquals(Character.valueOf('H'), keyValues[0].getKey().getValue());
        Assert.assertEquals(6.0, keyValues[0].getValue());

        Assert.assertEquals(Character.valueOf('G'), keyValues[1].getKey().getValue());
        Assert.assertEquals(Double.POSITIVE_INFINITY, keyValues[1].getValue());

        Assert.assertEquals(Character.valueOf('F'), keyValues[2].getKey().getValue());
        Assert.assertEquals(4.0, keyValues[2].getValue());

        Assert.assertEquals(Character.valueOf('E'), keyValues[3].getKey().getValue());
        Assert.assertEquals(5.0, keyValues[3].getValue());

        Assert.assertEquals(Character.valueOf('D'), keyValues[4].getKey().getValue());
        Assert.assertEquals(Double.POSITIVE_INFINITY, keyValues[4].getValue());

        Assert.assertEquals(Character.valueOf('C'), keyValues[5].getKey().getValue());
        Assert.assertEquals(5.0, keyValues[5].getValue());

        Assert.assertEquals(Character.valueOf('B'), keyValues[6].getKey().getValue());
        Assert.assertEquals(Double.POSITIVE_INFINITY, keyValues[6].getValue());

        Assert.assertEquals(Character.valueOf('A'), keyValues[7].getKey().getValue());
        Assert.assertEquals(0.0, keyValues[7].getValue());
    }
}

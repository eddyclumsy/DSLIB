package edu.uoc.ds.adt.nonlinear.graphs;


import edu.uoc.ds.adt.sequential.ContainerTest;
import org.junit.Test;
import edu.uoc.ds.traversal.Iterator;

import static org.junit.Assert.*;

/**
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public abstract class GraphTest extends ContainerTest {

    protected Graph<Character, Integer> graph;

    protected abstract Edge<Integer, Character> newEdge(Graph<Character, Integer> graph, Vertex<Character> v1, Vertex<Character> v2);

    protected abstract Edge<Integer, Character> getEdge(Graph<Character, Integer> graph, Vertex<Character> v1, Vertex<Character> v2);


    protected void checkVertexs(Iterator<Vertex<Character>> i, char[] labels) {
        boolean foundLabel = true;
        boolean[] labelsVisitades = new boolean[labels.length];
        while (i.hasNext() && foundLabel) {
            char label = i.next().getValue();
            int position = 0;
            boolean foundPosition = false;
            while (position < labels.length && !foundPosition) {
                foundPosition = !labelsVisitades[position] && label == labels[position];
                if (!foundPosition)
                    position++;
            }
            if (foundPosition)
                labelsVisitades[position] = true;
            else {
                foundLabel = false;
                fail("label " + label + " not found in defined set of labels");
            }
        }
        for (int j = 0; j < labels.length; j++)
            if (!labelsVisitades[j])
                fail("label " + labels[j] + " not present in Iterator of Vertex");
    }


    protected void checkEdge(Iterator<Edge<Integer, Character>> i, int[] labels) {
        boolean labelFound = true;
        boolean[] visitedLabels = new boolean[labels.length];
        while (i.hasNext() && labelFound) {
            int label = i.next().getLabel();
            int position = 0;
            boolean positionFound = false;
            while (position < labels.length && !positionFound) {
                positionFound = !visitedLabels[position] && label == labels[position];
                if (!positionFound)
                    position++;
            }
            if (positionFound)
                visitedLabels[position] = true;
            else {
                labelFound = false;
                fail("label " + label + " not found in defined set of labels");
            }
        }
        for (int j = 0; j < labels.length; j++)
            if (!visitedLabels[j])
                fail("label " + labels[j] + " not present in Iterator of Edge");
    }


    /**
     *
     */
    protected void init() {

        assertFalse(graph.vertexs().hasNext());

        Vertex<Character> va = graph.newVertex('A');
        Vertex<Character> vb = graph.newVertex('B');
        Vertex<Character> vc = graph.newVertex('C');
        Vertex<Character> vd = graph.newVertex('D');

        assertEquals(4, graph.numVertexs());

        assertEquals(4, numElems(graph.vertexs()));

        Vertex<Character> ve = graph.newVertex('E');
        Vertex<Character> vf = graph.newVertex('F');
        Vertex<Character> vg = graph.newVertex('G');
        Vertex<Character> vh = graph.newVertex('H');
        assertEquals(8, graph.numVertexs());

        assertFalse(graph.edges().hasNext());
        Edge<Integer, Character> edgeBA = newEdge(graph, vb, va);
        edgeBA.setLabel(2);

        Edge<Integer, Character> edgeAC = newEdge(graph, va, vc);
        edgeAC.setLabel(5);

        Edge<Integer, Character> edgeDA = newEdge(graph, vd, va);
        edgeDA.setLabel(10);

        assertEquals(3, numElems(graph.edges()));

        Edge<Integer, Character> edgeAE = newEdge(graph, va, ve);
        edgeAE.setLabel(5);

        Edge<Integer, Character> edgeAF = newEdge(graph, va, vf);
        edgeAF.setLabel(4);

        Edge<Integer, Character> edgeGB = newEdge(graph, vg, vb);
        edgeGB.setLabel(1);

        assertEquals(6, numElems(graph.edges()));
        Edge<Integer, Character> edgeBH = newEdge(graph, vb, vh);
        edgeBH.setLabel(7);
        Edge<Integer, Character> edgeGC = newEdge(graph, vg, vc);
        edgeGC.setLabel(9);
        Edge<Integer, Character> edgeHE = newEdge(graph, vh, ve);
        edgeHE.setLabel(18);
        Edge<Integer, Character> edgeFH = newEdge(graph, vf, vh);
        edgeFH.setLabel(2);
        assertEquals(10, numElems(graph.edges()));
        assertEquals(va, graph.getVertex('A'));
        assertEquals(vb, graph.getVertex('B'));
        assertEquals(vc, graph.getVertex('C'));
        assertEquals(vd, graph.getVertex('D'));
        assertEquals(ve, graph.getVertex('E'));
        assertEquals(vf, graph.getVertex('F'));
        assertEquals(vg, graph.getVertex('G'));
        assertEquals(vh, graph.getVertex('H'));
        assertEquals(edgeBA, getEdge(graph, vb, va));
        assertEquals(edgeAC, getEdge(graph, va, vc));
        assertEquals(edgeDA, getEdge(graph, vd, va));
        assertEquals(edgeAE, getEdge(graph, va, ve));
        assertEquals(edgeAF, getEdge(graph, va, vf));
        assertEquals(edgeGB, getEdge(graph, vg, vb));
        assertEquals(edgeBH, getEdge(graph, vb, vh));
        assertEquals(edgeGC, getEdge(graph, vg, vc));
        assertEquals(edgeHE, getEdge(graph, vh, ve));
        assertEquals(edgeFH, getEdge(graph, vf, vh));
        if (graph instanceof UnDirectedGraph) {
            assertEquals(edgeBA, getEdge(graph, vb, va));
            assertEquals(edgeAC, getEdge(graph, vc, va));
            assertEquals(edgeDA, getEdge(graph, va, vd));
            assertEquals(edgeAE, getEdge(graph, ve, va));
            assertEquals(edgeAF, getEdge(graph, vf, va));
            assertEquals(edgeGB, getEdge(graph, vb, vg));
            assertEquals(edgeBH, getEdge(graph, vh, vb));
            assertEquals(edgeGC, getEdge(graph, vc, vg));
            assertEquals(edgeHE, getEdge(graph, ve, vh));
            assertEquals(edgeFH, getEdge(graph, vh, vf));
        }
    }


    @Test
    public void adjacencyTest() {
        Vertex<Character> va = graph.getVertex('A');
        Iterator<Vertex<Character>> i = graph.adjacencyList(va);
        char[] valuesA = {'B', 'C', 'D', 'E', 'F'};
        checkVertexs(i, valuesA);
        Vertex<Character> vd = graph.getVertex('D');
        i = graph.adjacencyList(vd);
        char[] valuesD = {'A'};
        checkVertexs(i, valuesD);
        Vertex<Character> ve = graph.getVertex('E');
        i = graph.adjacencyList(ve);
        char[] valuesE = {'A', 'H'};
        checkVertexs(i, valuesE);
    }


    @Test
    public void vertexTest() {
        char[] labels = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        checkVertexs(graph.vertexs(), labels);
    }


    @Test
    public void edgesTest() {
        int[] labels = {1, 2, 2, 4, 5, 5, 7, 9, 10, 18};
        checkEdge(graph.edges(), labels);
    }


    @Test
    public void getEdgeTest() {
        Vertex<Character> va = graph.getVertex('A');
        Vertex<Character> vc = graph.getVertex('C');
        Vertex<Character> vg = graph.getVertex('G');
        Vertex<Character> vb = graph.getVertex('B');
        Edge<Integer, Character> edgeAC = getEdge(graph, va, vc);
        assertEquals(5, edgeAC.getLabel().intValue());

        Edge<Integer, Character> edgeGC = getEdge(graph, vg, vc);
        assertEquals(9, edgeGC.getLabel().intValue());

        Edge<Integer, Character> edgeGB = getEdge(graph, vg, vb);
        assertEquals(1, edgeGB.getLabel().intValue());

        Edge<Integer, Character> edgeBA = getEdge(graph, vb, va);
        assertEquals(2, edgeBA.getLabel().intValue());
    }


    @Test
    public void deleteVertex() {
        graph.deleteVertex(graph.getVertex('B'));
        assertEquals(7, graph.numVertexs());
        char[] labels1 = {'A', 'C', 'D', 'E', 'F', 'G', 'H'};
        checkVertexs(graph.vertexs(), labels1);

        graph.deleteVertex(graph.getVertex('D'));
        assertEquals(6, graph.numVertexs());
        char[] labels2 = {'A', 'C', 'E', 'F', 'G', 'H'};
        checkVertexs(graph.vertexs(), labels2);

        graph.deleteVertex(graph.getVertex('F'));
        assertEquals(5, graph.numVertexs());
        char[] labels3 = {'A', 'C', 'E', 'G', 'H'};
        checkVertexs(graph.vertexs(), labels3);

        Vertex<Character> va = graph.getVertex('A');
        Iterator<Vertex<Character>> i = graph.adjacencyList(va);
        char[] valuesA = {'C', 'E'};
        checkVertexs(i, valuesA);

        Vertex<Character> vd = graph.getVertex('D');
        assertNull(vd);

        Vertex<Character> ve = graph.getVertex('E');
        i = graph.adjacencyList(ve);
        char[] valuesE = {'A', 'H'};
        checkVertexs(i, valuesE);
    }


    @Test
    public void deleteEdgeTest() {
        Vertex<Character> va = graph.getVertex('A');
        Vertex<Character> vb = graph.getVertex('B');
        Vertex<Character> vc = graph.getVertex('C');
        Vertex<Character> vd = graph.getVertex('D');
        Vertex<Character> vf = graph.getVertex('F');
        Vertex<Character> vg = graph.getVertex('G');
        Vertex<Character> vh = graph.getVertex('H');

        graph.deleteEdge(getEdge(graph, vb, vh));
        graph.deleteEdge(getEdge(graph, va, vf));
        graph.deleteEdge(getEdge(graph, vd, va));
        graph.deleteEdge(getEdge(graph, vg, vc));

        assertNull(getEdge(graph, vb, vh));
        assertNull(getEdge(graph, va, vf));
        assertNull(getEdge(graph, vd, va));
        assertNull(getEdge(graph, vg, vc));

        assertNull(getEdge(graph, vh, vb));
        assertNull(getEdge(graph, vf, va));
        assertNull(getEdge(graph, va, vd));
        assertNull(getEdge(graph, vc, vg));

        int[] labels = {1, 2, 2, 5, 5, 18};
        checkEdge(graph.edges(), labels);

        char[] valuesA = {'B', 'C', 'E'};
        checkVertexs(graph.adjacencyList(va), valuesA);
        char[] valuesB = {'G', 'A'};
        checkVertexs(graph.adjacencyList(vb), valuesB);
        char[] valuesC = {'A'};
        checkVertexs(graph.adjacencyList(vc), valuesC);
        char[] valuesD = {};
        checkVertexs(graph.adjacencyList(vd), valuesD);
        char[] valuesF = {'H'};
        checkVertexs(graph.adjacencyList(vf), valuesF);
        char[] valuesG = {'B'};
        checkVertexs(graph.adjacencyList(vg), valuesG);
        char[] valuesH = {'F', 'E'};
        checkVertexs(graph.adjacencyList(vh), valuesH);
    }


}

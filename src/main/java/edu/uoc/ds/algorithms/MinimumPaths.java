package edu.uoc.ds.algorithms;


import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.adt.nonlinear.graphs.DirectedGraph;
import edu.uoc.ds.adt.nonlinear.graphs.Edge;
import edu.uoc.ds.adt.nonlinear.graphs.Vertex;
import edu.uoc.ds.traversal.Iterator;

/**
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class MinimumPaths<E, L extends Number> {


    protected static class VCM<E> {
        private Vertex<E> vertex;
        private double distance;
        private boolean isMinimunDistance;

        public VCM(Vertex<E> vertex, double d) {
            this.vertex = vertex;
            this.distance = d;
            this.isMinimunDistance = false;
        }

        public void hasMinimunDistance() {
            isMinimunDistance = true;
        }

        public boolean isMinimunDistance() {
            return isMinimunDistance;
        }

        public double getDistance() {
            return distance;
        }

        public Vertex<E> getVertex() {
            return vertex;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String toString() {
            return "[" + vertex + "," + distance + "," + isMinimunDistance + "]";
        }

    }


    public KeyValue<Vertex<E>, Number>[] calculate(DirectedGraph<E, L> graph, Vertex<E> vertex) {
        VCM<E>[] d = new VCM[graph.numVertexs()];
        int vertexsWithFinalDistance = 0;
        Iterator<Vertex<E>> vertexs = graph.vertexs();
        // calculem dist�ncies incials
        for (int i = 0; vertexs.hasNext(); i++) {
            Vertex<E> v = vertexs.next();
            d[i] = new VCM<>(v, directDistance(graph, vertex, v));
            if (d[i].getDistance() == 0) {
                d[i].hasMinimunDistance();
                vertexsWithFinalDistance++;
            }
        }

        int positionW = 0;
        while (vertexsWithFinalDistance < graph.numVertexs() - 1 && positionW != -1) {
            positionW = closerVertex(graph, d);

            if (positionW >= 0) {
                d[positionW].hasMinimunDistance();
                vertexsWithFinalDistance++;

                double distanceAW = d[positionW].getDistance();
                for (int i = 0; i < d.length; i++) {
                    if (!d[i].isMinimunDistance()) {
                        double distanceFromW = directDistance(graph, d[positionW].getVertex(), d[i].getVertex());
                        if (distanceAW + distanceFromW < d[i].getDistance())
                            d[i].setDistance(distanceAW + distanceFromW);
                    }
                }
            }
        }
        KeyValue<Vertex<E>, Number>[] result = new KeyValue[d.length];
        for (int i = 0; i < d.length; i++)
            result[i] = new KeyValue<>(d[i].getVertex(), d[i].getDistance());
        return result;
    }


    private int closerVertex(DirectedGraph<E, L> graph, VCM<E>[] currentDistance) {
        double minim = Double.POSITIVE_INFINITY;
        int positioW = -1;
        // we choose vertex with less direct distance than not s
        for (int i = 0; i < currentDistance.length; i++)
            if (!currentDistance[i].isMinimunDistance() &&
                 (currentDistance[i].getDistance() < minim)) {
                    minim = currentDistance[i].getDistance();
                    positioW = i;
                }
        return positioW;
    }


    private double directDistance(DirectedGraph<E, L> graph, Vertex<E> src, Vertex<E> dest) {
        double value;
        if (src == dest)
            value = 0;
        else {
            Edge<L, E> edge = graph.getEdge(src, dest);
            if (edge != null)
                value = edge.getLabel().doubleValue();
            else
                value = Double.POSITIVE_INFINITY;
        }
        return value;
    }

}

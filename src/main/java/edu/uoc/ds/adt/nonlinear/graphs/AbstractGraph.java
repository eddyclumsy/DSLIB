
package edu.uoc.ds.adt.nonlinear.graphs;

import java.io.Serializable;

import edu.uoc.ds.adt.sequential.DictionaryLinkedListImpl;
import edu.uoc.ds.adt.sequential.DoublyLinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.adt.nonlinear.Dictionary;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;


/**
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
abstract class AbstractGraph<E, L> implements Graph<E, L>, Serializable {

    DoublyLinkedList<Edge<L, E>> edgesList;
    Dictionary<E, Vertex<E>> vertexDictionary;


    protected AbstractGraph() {
        vertexDictionary = newDictionaryVertexs();
        edgesList = new DoublyLinkedList<>();
    }


    protected Dictionary<E, Vertex<E>> newDictionaryVertexs() {
        return new DictionaryLinkedListImpl<>();
    }


    public Iterator<Vertex<E>> vertexs() {
        return vertexDictionary.values();
    }


    public int numVertexs() {
        return vertexDictionary.size();
    }


    public Iterator<Edge<L, E>> edges() {
        return edgesList.values();
    }


    public Vertex<E> newVertex(E value) {
        Vertex<E> vertex = this.createVertex(value);
        vertexDictionary.put(value, vertex);
        return vertex;
    }


    public void deleteVertex(Vertex<E> vertex) {
        VertexImpl<E, L> v = (VertexImpl<E, L>) vertex;
        Iterator<Edge<L, E>> edges = v.edges();
        List<Edge<L, E>> edges2Delete = new LinkedList<>();
        while (edges.hasNext()) {
            Edge<L, E> aresta = edges.next();
            edges2Delete.insertEnd(aresta);
        }
        while (!edges2Delete.isEmpty()) {
            Edge<L, E> aresta = edges2Delete.deleteFirst();
            deleteEdge(aresta);
        }
        vertexDictionary.delete(vertex.getValue());
    }


    public void deleteEdge(Edge<L, E> edge) {
        ((EdgeImpl<L, E>) edge).eliminarDelGraf(this);
    }


    public Iterator<Vertex<E>> adjacencyList(Vertex<E> vertex) {
        return new AdjacencyIterator<>((VertexImpl<E, L>) vertex);
    }


    public Vertex<E> getVertex(E elem) {
        return vertexDictionary.get(elem);
    }


    protected abstract VertexImpl<E, L> createVertex(E value);


    public String toString() {
        return "[Graph\n  " + edgesList + "\n" + vertexDictionary + "]";
    }



    static class AdjacencyIterator<E, L> implements Iterator<Vertex<E>> {

        Iterator<Edge<L, E>> edges;
        Vertex<E> vertex;

        public AdjacencyIterator(VertexImpl<E, L> vertex) {
            this.vertex = vertex;
            edges = vertex.edges();
        }


        public Vertex<E> next() {
            EdgeImpl<L, E> aresta = (EdgeImpl<L, E>) edges.next();
            return aresta.extremAlternatiu(vertex);
        }


        public boolean hasNext() {
            return edges.hasNext();
        }


    }


}

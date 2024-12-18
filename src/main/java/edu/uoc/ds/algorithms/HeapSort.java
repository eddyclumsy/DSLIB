package edu.uoc.ds.algorithms;

import edu.uoc.ds.adt.nonlinear.PriorityQueue;


/**
 * HeapSort algorithm. Use a priority queue for
 * sort a sequence of items.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

public class HeapSort<E> implements SortingAlgorithm<E> {
    private E[] data;

    public HeapSort() {

    }

    public HeapSort(E[] vector) {
        this.data = vector;
    }

    public void sort(E[] vector, int n) {
        PriorityQueue<E> c = new PriorityQueue<>(vector.length);
        for (int i = 0; i < n; i++)
            c.add(vector[i]);
        for (int i = 0; i < n; i++)
            vector[i] = c.poll();
    }

    public void sort(int n) {
        sort(data, n);
    }

}

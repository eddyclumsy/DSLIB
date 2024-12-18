package edu.uoc.ds.algorithms;


/**
 * MergeSort algorithm. Use the divide and conquer technique,
 * dividing the vector to be ordered into two parts
 * recursively applies the algorithm to merge later
 * both parts. The base case is to merge two
 * sequences of one element each.
 *
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */

public class MergeSort<E> implements SortingAlgorithm<E> {

    public void sort(E[] vector, int n) {
        E[] auxVector = (E[]) new Object[n];
        mergeSort(vector, auxVector, 0, n - 1);
    }


    private void mergeSort(E[] vector, E[] auxVector, int start, int end) {
        int middle = (end + start) / 2;
        if (start < end) {
            mergeSort(vector, auxVector, start, middle);
            mergeSort(vector, auxVector, middle + 1, end);
            merge(vector, auxVector, start, middle, middle + 1, end);
        }
    }


    private void merge(E[] vector, E[] auxVector, int start1, int end1, int start2, int end2) {
        int i1 = start1;
        int i2 = start2;
        int k = start1;
        while (k <= end2) {
            if (i2 > end2 || i1 <= end1 && compare(vector, i1, i2) <= 0) {
                auxVector[k] = vector[i1];
                i1++;
            } else {
                auxVector[k] = vector[i2];
                i2++;
            }
            k++;
        }
        for (int i = start1; i <= end2; i++)
            vector[i] = auxVector[i];
    }


    private int compare(E[] vector, int i, int j) {
        return ((Comparable<E>) vector[i]).compareTo(vector[j]);
    }


}

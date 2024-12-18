package edu.uoc.ds.adt.algorithms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.algorithms.MergeSort;

public class MergeSortTest {

    private MergeSort<Integer> mergeSort;

    @Before
    public void setUp() {
        mergeSort = new MergeSort<>();
    }


    @Test
    public void sort() {
        Integer[] v = {12, 2, 8, 5, 1, 10, 4, 3, 9, 7};
        mergeSort.sort(v, 10);

        Assert.assertEquals(1, v[0], 0);
        Assert.assertEquals(2, v[1], 0);
        Assert.assertEquals(3, v[2], 0);
        Assert.assertEquals(4, v[3], 0);
        Assert.assertEquals(5, v[4], 0);
        Assert.assertEquals(7, v[5], 0);
        Assert.assertEquals(8, v[6], 0);
        Assert.assertEquals(9, v[7], 0);
        Assert.assertEquals(10, v[8], 0);
        Assert.assertEquals(12, v[9], 0);


    }

}

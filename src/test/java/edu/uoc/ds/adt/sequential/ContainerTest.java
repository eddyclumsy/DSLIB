package edu.uoc.ds.adt.sequential;

import org.junit.Assert;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;

public class ContainerTest {
    public static final int FULL_SIZE = 3;


    protected int numElems(Iterator r) {
        int n = 0;
        while (r.hasNext()) {
            r.next();
            n++;
        }
        return n;
    }

    protected int numElems(Traversal r) {
        int n = 0;
        while (r.hasNext()) {
            r.next();
            n++;
        }
        return n;
    }

    protected void checkTraversal(int[] expectedArray, Traversal<Integer> traversal) {
        for (int v : expectedArray) {
            Assert.assertEquals(v, traversal.next().getElem(), 0);
        }
    }
}

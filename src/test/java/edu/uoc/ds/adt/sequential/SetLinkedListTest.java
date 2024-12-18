package edu.uoc.ds.adt.sequential;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SetLinkedListTest {
    Set<String> theSet;

    @Before
    public void setUp() {
        theSet = new SetLinkedListImpl<>();
        theSet.add("A");
        theSet.add("B");
        theSet.add("C");
        theSet.add("D");
        Assert.assertEquals(4, theSet.size());
    }

    @After
    public void tearDown() {
        theSet = null;
    }

    @Test
    public void addTest() {
        theSet.add("E");
        Assert.assertEquals(5, theSet.size());
        theSet.add("B");
        Assert.assertEquals(5, theSet.size());
        theSet.add("A");
        Assert.assertEquals(5, theSet.size());
        theSet.add("F");
        Assert.assertEquals(6, theSet.size());

    }
}

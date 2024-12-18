package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.traversal.Iterator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;



public class StackArrayTest extends ContainerTest {

    private Stack<Integer> stack;

    @Before
    public void setUp() {
        stack = new StackArrayImpl<>(FULL_SIZE);
        stack.push(5);
        Assert.assertEquals(1, stack.size());
        stack.push(12);
        Assert.assertEquals(2, stack.size());
    }

    @After
    public void tearDown() {
        stack = null;
    }

    @Test
    public void pushTest() {
        Assert.assertEquals(2, stack.size());
        stack.push(20);
        Assert.assertEquals(3, stack.size());
    }

    @Test
    public void peekTest() {
        Assert.assertEquals(2, stack.size());
        int elem = stack.peek();
        Assert.assertEquals(12, elem);
        Assert.assertEquals(2, stack.size());
    }

    @Test
    public void popTest() {
        Assert.assertEquals(2, stack.size());
        int elem = stack.pop();
        Assert.assertEquals(12, elem);
    }


    @Test
    public void emptyTest() {
        Assert.assertEquals(2, stack.size());
        int elem = stack.pop();
        Assert.assertEquals(12, elem);
        Assert.assertEquals(1, stack.size());

        elem = stack.pop();
        Assert.assertEquals(5, elem);
        Assert.assertEquals(0, stack.size());

        Assert.assertThrows(EmptyContainerException.class, () -> stack.pop());
    }

    @Test
    public void fullTest() {
        Assert.assertEquals(2, stack.size());
        stack.push(8);
        Assert.assertEquals(3, stack.size());
        Assert.assertTrue(((StackArrayImpl) stack).isFull());

        Assert.assertThrows(FullContainerException.class, ()->stack.push(999));

    }

    @Test
    public void valuesTest() {
        pushTest();
        Iterator<Integer> it = stack.values();
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(20, it.next(),0);

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(12, it.next(),0);

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(5, it.next(),0);

    }
}

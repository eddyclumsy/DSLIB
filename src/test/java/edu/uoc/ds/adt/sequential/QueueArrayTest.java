package edu.uoc.ds.adt.sequential;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;


public class QueueArrayTest extends ContainerTest {

    private Queue<Integer> queue;

    @Before
    public void setUp() {
        queue = new QueueArrayImpl<Integer>(FULL_SIZE);
        queue.add(54);
        queue.add(60);
    }

    @After
    public void tearDown() {
        queue = null;
    }

    @Test
    public void addTest() {
        Assert.assertEquals(2, queue.size());
        queue.add(34);
        Assert.assertEquals(3, queue.size());
    }

    @Test
    public void peekTest() {
        Assert.assertEquals(2, queue.size());
        int elem = queue.peek();
        Assert.assertEquals(54, elem);
        Assert.assertEquals(2, queue.size());
    }

    @Test
    public void pollTest() {
        Assert.assertEquals(2, queue.size());
        int elem = queue.poll();
        Assert.assertEquals(54, elem);
        Assert.assertEquals(1, queue.size());
    }


    @Test
    public void emptyTest() {
        Assert.assertEquals(2, queue.size());

        int elem = queue.poll();
        Assert.assertEquals(54, elem);
        Assert.assertEquals(1, queue.size());

        elem = queue.poll();
        Assert.assertEquals(60, elem);
        Assert.assertEquals(0, queue.size());

        Assert.assertTrue(queue.isEmpty());
        Assert.assertThrows(EmptyContainerException.class, ()-> queue.poll());
    }

    @Test
    public void fullTest() {

        Assert.assertEquals(2, queue.size());
        queue.add(44);
        Assert.assertTrue(((QueueArrayImpl) queue).isFull());

        Assert.assertThrows(FullContainerException.class, ()-> queue.add(5));
    }


}

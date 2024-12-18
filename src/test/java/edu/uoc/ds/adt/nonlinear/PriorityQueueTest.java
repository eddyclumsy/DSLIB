package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;

import java.util.Comparator;

public class PriorityQueueTest {


    private PriorityQueue<Integer> priorityQueue;

    private Comparator<Integer> CMP = (o1, o2) -> o2.compareTo(o1);

    @Before
    public void setUp() {
        priorityQueue = new PriorityQueue<Integer>(10, CMP);
    }


    @Test
    public void emptyTest() {
        Assert.assertTrue(priorityQueue.isEmpty());
        Assert.assertThrows(EmptyContainerException.class, ()->priorityQueue.poll());
    }


    @Test
    public void addTest() {
        priorityQueue.add(7);
        Assert.assertEquals(1, priorityQueue.size());
        Assert.assertEquals(7, priorityQueue.peek(), 0);

        priorityQueue.add(3);
        Assert.assertEquals(2, priorityQueue.size());
        Assert.assertEquals(7, priorityQueue.peek(), 0);

        priorityQueue.add(5);
        Assert.assertEquals(3, priorityQueue.size());
        Assert.assertEquals(7, priorityQueue.peek(), 0);

        priorityQueue.add(1);
        Assert.assertEquals(4, priorityQueue.size());
        Assert.assertEquals(7, priorityQueue.peek(), 0);

        priorityQueue.add(9);
        Assert.assertEquals(5, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(0);
        Assert.assertEquals(6, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(8);
        Assert.assertEquals(7, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(6);
        Assert.assertEquals(8, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(4);
        Assert.assertEquals(9, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(2);
        Assert.assertEquals(10, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);
        Assert.assertTrue(priorityQueue.isFull());

        Iterator<Integer> it = priorityQueue.values();

        Assert.assertEquals(9,  it.next(),0);
        Assert.assertEquals(8,  it.next(),0);
        Assert.assertEquals(7,  it.next(),0);
        Assert.assertEquals(6,  it.next(),0);
        Assert.assertEquals(5,  it.next(),0);
        Assert.assertEquals(4,  it.next(),0);
        Assert.assertEquals(3,  it.next(),0);
        Assert.assertEquals(2,  it.next(),0);
        Assert.assertEquals(1,  it.next(),0);
        Assert.assertEquals(0,  it.next(),0);

        Assert.assertEquals(10,  priorityQueue.size());

    }


    @Test
    public void addAndFullContainerTest() {
        priorityQueue.add(7);
        Assert.assertEquals(1, priorityQueue.size());
        Assert.assertEquals(7, priorityQueue.peek(), 0);

        priorityQueue.add(3);
        Assert.assertEquals(2, priorityQueue.size());
        Assert.assertEquals(7, priorityQueue.peek(), 0);

        priorityQueue.add(5);
        Assert.assertEquals(3, priorityQueue.size());
        Assert.assertEquals(7, priorityQueue.peek(), 0);

        priorityQueue.add(1);
        Assert.assertEquals(4, priorityQueue.size());
        Assert.assertEquals(7, priorityQueue.peek(), 0);

        priorityQueue.add(9);
        Assert.assertEquals(5, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(0);
        Assert.assertEquals(6, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(8);
        Assert.assertEquals(7, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(6);
        Assert.assertEquals(8, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(4);
        Assert.assertEquals(9, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);

        priorityQueue.add(2);
        Assert.assertEquals(10, priorityQueue.size());
        Assert.assertEquals(9, priorityQueue.peek(), 0);
        Assert.assertTrue(priorityQueue.isFull());

        Assert.assertThrows(FullContainerException.class, ()->priorityQueue.add(200));
    }

    @Test
    public void pullTest() {
        priorityQueue.add(7);
        priorityQueue.add(3);
        priorityQueue.add(5);
        priorityQueue.add(1);
        priorityQueue.add(9);
        priorityQueue.add(0);
        priorityQueue.add(8);
        priorityQueue.add(6);
        priorityQueue.add(4);
        priorityQueue.add(2);
        Assert.assertEquals(10, priorityQueue.size());

        Assert.assertEquals(9, priorityQueue.poll(), 0);
        Assert.assertEquals(9, priorityQueue.size());

        Assert.assertEquals(8, priorityQueue.poll(), 0);
        Assert.assertEquals(8, priorityQueue.size());

        Assert.assertEquals(7, priorityQueue.poll(), 0);
        Assert.assertEquals(7, priorityQueue.size());

        Assert.assertEquals(6, priorityQueue.poll(), 0);
        Assert.assertEquals(6, priorityQueue.size());

        Assert.assertEquals(5, priorityQueue.poll(), 0);
        Assert.assertEquals(5, priorityQueue.size());

        Assert.assertEquals(4, priorityQueue.poll(), 0);
        Assert.assertEquals(4, priorityQueue.size());

        Assert.assertEquals(3, priorityQueue.poll(), 0);
        Assert.assertEquals(3, priorityQueue.size());

        Assert.assertEquals(2, priorityQueue.poll(), 0);
        Assert.assertEquals(2, priorityQueue.size());

        Assert.assertEquals(1, priorityQueue.poll(), 0);
        Assert.assertEquals(1, priorityQueue.size());

        Assert.assertEquals(0, priorityQueue.poll(), 0);
        Assert.assertEquals(0, priorityQueue.size());
        Assert.assertTrue(priorityQueue.isEmpty());

    }
}

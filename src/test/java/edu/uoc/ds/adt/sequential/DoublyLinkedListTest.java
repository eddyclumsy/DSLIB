package edu.uoc.ds.adt.sequential;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.BidirectionalTraversal;


public class DoublyLinkedListTest extends ContainerTest {

    DoublyLinkedList<Integer> doublyLinkedList;

    @Before
    public void setUp() {
        doublyLinkedList = new DoublyLinkedList<>();
    }

    @Test
    public void bidirectionalTraversalFromBeginningTest() {
        Assert.assertTrue(doublyLinkedList.isEmpty());

        doublyLinkedList.insertEnd(1);
        doublyLinkedList.insertEnd(2);
        doublyLinkedList.insertEnd(3);
        doublyLinkedList.insertEnd(4);
        doublyLinkedList.insertEnd(5);

        BidirectionalTraversal<Integer> traversal = doublyLinkedList.positions(BidirectionalTraversal.traversalMode.BEGINNING);
        Assert.assertEquals(1, traversal.next().getElem().intValue());
        Assert.assertEquals(2, traversal.next().getElem().intValue());
        Assert.assertEquals(3, traversal.next().getElem().intValue());
        Assert.assertEquals(4, traversal.next().getElem().intValue());
        Assert.assertEquals(5, traversal.next().getElem().intValue());
    }


    @Test
    public void bidirectionalTraversalFromBeginningAndInvalidPositionExceptionTest() {
        Assert.assertTrue(doublyLinkedList.isEmpty());

        doublyLinkedList.insertEnd(1);
        doublyLinkedList.insertEnd(2);
        doublyLinkedList.insertEnd(3);
        doublyLinkedList.insertEnd(4);
        doublyLinkedList.insertEnd(5);

        BidirectionalTraversal<Integer> traversal = doublyLinkedList.positions(BidirectionalTraversal.traversalMode.BEGINNING);
        Assert.assertEquals(1, traversal.next().getElem().intValue());
        Assert.assertEquals(2, traversal.next().getElem().intValue());
        Assert.assertEquals(3, traversal.next().getElem().intValue());
        Assert.assertEquals(4, traversal.next().getElem().intValue());
        Assert.assertEquals(5, traversal.next().getElem().intValue());

        Assert.assertThrows(InvalidPositionException.class, ()->traversal.next());
    }

    @Test
    public void bidirectionalTraversalFromEndTest() {
        Assert.assertTrue(doublyLinkedList.isEmpty());

        doublyLinkedList.insertEnd(1);
        doublyLinkedList.insertEnd(2);
        doublyLinkedList.insertEnd(3);
        doublyLinkedList.insertEnd(4);
        doublyLinkedList.insertEnd(5);

        BidirectionalTraversal<Integer> traversal = doublyLinkedList.positions(BidirectionalTraversal.traversalMode.END);
        Assert.assertEquals(5, traversal.previous().getElem().intValue());
        Assert.assertEquals(4, traversal.previous().getElem().intValue());
        Assert.assertEquals(3, traversal.previous().getElem().intValue());
        Assert.assertEquals(2, traversal.previous().getElem().intValue());
        Assert.assertEquals(1, traversal.previous().getElem().intValue());
    }

    @Test
    public void bidirectionalTraversalFromEndAndInvalidPositionTest() {
        Assert.assertTrue(doublyLinkedList.isEmpty());

        doublyLinkedList.insertEnd(1);
        doublyLinkedList.insertEnd(2);
        doublyLinkedList.insertEnd(3);
        doublyLinkedList.insertEnd(4);
        doublyLinkedList.insertEnd(5);

        BidirectionalTraversal<Integer> traversal = doublyLinkedList.positions(BidirectionalTraversal.traversalMode.END);
        Assert.assertEquals(5, traversal.previous().getElem().intValue());
        Assert.assertEquals(4, traversal.previous().getElem().intValue());
        Assert.assertEquals(3, traversal.previous().getElem().intValue());
        Assert.assertEquals(2, traversal.previous().getElem().intValue());
        Assert.assertEquals(1, traversal.previous().getElem().intValue());

        Assert.assertThrows(InvalidPositionException.class, ()->traversal.previous());
    }
}

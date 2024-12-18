package edu.uoc.ds.adt.sequential;

import edu.uoc.ds.adt.helpers.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;

public class LinkedListTest extends ContainerTest {

    List<Integer> list;

    @Before
    public void setUp() {
        list = new LinkedList<Integer>();
    }

    @Test
    public void insertTest() {
        Assert.assertTrue(list.isEmpty());
        int elem = list.insertEnd(1).getElem();
        Assert.assertEquals(1, elem);
        Assert.assertEquals(1, list.size());
        Assert.assertFalse(list.isEmpty());


        elem = list.insertEnd(2).getElem();
        Assert.assertEquals(2, elem);
        Assert.assertEquals(2, list.size());
        Assert.assertFalse(list.isEmpty());

        elem = list.insertEnd(3).getElem();
        Assert.assertEquals(3, elem);
        Assert.assertEquals(3, list.size());
        Assert.assertFalse(list.isEmpty());

        elem = list.insertEnd(4).getElem();
        Assert.assertEquals(4, elem);
        Assert.assertEquals(4, list.size());
        Assert.assertFalse(list.isEmpty());
    }


    @Test
    public void insertAfterTest() {
        Assert.assertTrue(list.isEmpty());
        Position<Integer> p1 = list.insertBeginning(1);
        Position<Integer> p4 = list.insertAfter(p1, 4);
        Position<Integer> p2 = list.insertAfter(p1, 2);
        Position<Integer> p3 = list.insertAfter(p2, 3);
        Position<Integer> p5 = list.insertAfter(p4, 5);

        Traversal<Integer> t = list.positions();
        Assert.assertEquals(p1, t.next());
        Assert.assertEquals(p2, t.next());
        Assert.assertEquals(p3, t.next());
        Assert.assertEquals(p4, t.next());
        Assert.assertEquals(p5, t.next());
    }


    @Test
    public void deleteTest() {
        Assert.assertTrue(list.isEmpty());
        Position<Integer> p1 = list.insertBeginning(1);
        Position<Integer> p4 = list.insertAfter(p1, 4);
        Position<Integer> p2 = list.insertAfter(p1, 2);
        Position<Integer> p3 = list.insertAfter(p2, 3);
        Position<Integer> p5 = list.insertAfter(p4, 5);

        int elem = list.delete(p1);
        Assert.assertEquals(1, elem);
        Assert.assertEquals(4, list.size());

        elem = list.delete(p3);
        Assert.assertEquals(3, elem);
        Assert.assertEquals(3, list.size());

        elem = list.delete(p5);
        Assert.assertEquals(5, elem);
        Assert.assertEquals(2, list.size());

        elem = list.delete(p4);
        Assert.assertEquals(4, elem);
        Assert.assertEquals(1, list.size());

        elem = list.delete(p2);
        Assert.assertEquals(2, elem);
        Assert.assertEquals(0, list.size());

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void deleteTestAndInvalidPositionException() {
        Assert.assertTrue(list.isEmpty());
        Position<Integer> p1 = list.insertBeginning(1);
        Position<Integer> p4 = list.insertAfter(p1, 4);
        Position<Integer> p2 = list.insertAfter(p1, 2);
        Position<Integer> p3 = list.insertAfter(p2, 3);
        Position<Integer> p5 = list.insertAfter(p4, 5);

        Assert.assertThrows(InvalidPositionException.class, ()-> list.deleteNext(p5));
    }


    @Test
    public void deleteAndEmptyExceptionTest() {
        Assert.assertTrue(list.isEmpty());
        int elem = list.insertEnd(1).getElem();
        Assert.assertEquals(1, elem);
        Assert.assertEquals(1, list.size());
        Assert.assertFalse(list.isEmpty());


        elem = list.insertEnd(2).getElem();
        Assert.assertEquals(2, elem);
        Assert.assertEquals(2, list.size());
        Assert.assertFalse(list.isEmpty());

        elem = list.insertEnd(3).getElem();
        Assert.assertEquals(3, elem);
        Assert.assertEquals(3, list.size());
        Assert.assertFalse(list.isEmpty());

        elem = list.insertEnd(4).getElem();
        Assert.assertEquals(4, elem);
        Assert.assertEquals(4, list.size());
        Assert.assertFalse(list.isEmpty());

        elem = list.deleteFirst();
        Assert.assertEquals(1, elem);
        Assert.assertEquals(3, list.size());
        Assert.assertFalse(list.isEmpty());

        elem = list.deleteFirst();
        Assert.assertEquals(2, elem);
        Assert.assertEquals(2, list.size());
        Assert.assertFalse(list.isEmpty());

        elem = list.deleteFirst();
        Assert.assertEquals(3, elem);
        Assert.assertEquals(1, list.size());
        Assert.assertFalse(list.isEmpty());

        elem = list.deleteFirst();
        Assert.assertEquals(4, elem);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(list.isEmpty());

        Assert.assertThrows(EmptyContainerException.class, ()-> list.deleteFirst());
    }

    /**
     * Crea una llista amb afegirAlPrincipi, i n'elimina els elements amb esborrarPrimer.
     * Operacions comprovades: afegirAlPrincipi, esborrarPrimer, estaBuit.
     */
    @Test
    public void insertBeginningTest() {
        Assert.assertTrue(list.isEmpty());
        int elem = list.insertBeginning(1).getElem();
        Assert.assertEquals(1, elem);
        elem = list.insertBeginning(2).getElem();
        Assert.assertEquals(2, elem);
        elem = list.insertBeginning(3).getElem();
        Assert.assertEquals(3, elem);
        elem = list.insertBeginning(4).getElem();
        Assert.assertEquals(4, elem);
        Assert.assertEquals(4, list.size());


        Iterator<Integer> it = list.values();
        Assert.assertEquals(4, it.next().intValue());
        Assert.assertEquals(3, it.next().intValue());
        Assert.assertEquals(2, it.next().intValue());
        Assert.assertEquals(1, it.next().intValue());

    }

    @Test
    public void deleteNextTest() {
        Assert.assertTrue(list.isEmpty());
        Position<Integer> p1 = list.insertBeginning(1);
        Position<Integer> p4 = list.insertAfter(p1, 4);
        Position<Integer> p2 = list.insertAfter(p1, 2);
        Position<Integer> p3 = list.insertAfter(p2, 3);
        Position<Integer> p5 = list.insertAfter(p4, 5);
        Assert.assertEquals(5, list.size());

        int elem = list.deleteNext(p1);
        Assert.assertEquals(2, elem);
        Assert.assertEquals(4, list.size());

        elem = list.deleteNext(p3);
        Assert.assertEquals(4, elem);
        Assert.assertEquals(3, list.size());

        elem = list.deleteNext(p3);
        Assert.assertEquals(5, elem);
        Assert.assertEquals(2, list.size());

        elem = list.deleteNext(p1);
        Assert.assertEquals(3, elem);
        Assert.assertEquals(1, list.size());

        elem = list.deleteFirst();
        Assert.assertEquals(1, elem);
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(list.isEmpty());
    }


    @Test
    public void testPositions() {
        Iterator<Integer> iterator = list.values();
        Traversal<Integer> traversal = list.positions();
        Assert.assertTrue(list.isEmpty());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertFalse(traversal.hasNext());
        Position<Integer> p1 = list.insertBeginning(1);

        iterator = list.values();
        traversal = list.positions();
        int elemR = traversal.next().getElem();
        int elemI = iterator.next();
        Assert.assertEquals(elemI, elemR);
        Assert.assertFalse(iterator.hasNext());
        Assert.assertFalse(traversal.hasNext());
        Position<Integer> p4 = list.insertAfter(p1, 4);
        Position<Integer> p2 = list.insertAfter(p1, 2);
        list.insertAfter(p2, 3);
        list.insertAfter(p4, 5);

        iterator = list.values();
        traversal = list.positions();
        while (traversal.hasNext()) {
            elemR = traversal.next().getElem();
            elemI = iterator.next();
            Assert.assertEquals(elemI, elemR);
        }
        Assert.assertFalse(iterator.hasNext());
        Assert.assertFalse(traversal.hasNext());
    }


    @Test
    public void insertAllTest() {
        List<String> firstList = new LinkedList<>();
        firstList.insertEnd("A");
        firstList.insertEnd("B");
        firstList.insertEnd("C");
        firstList.insertEnd("D");
        Assert.assertEquals(4, firstList.size());

        List<String> secondList = new LinkedList<>();
        secondList.insertEnd("E");
        secondList.insertEnd("F");
        secondList.insertEnd("G");
        secondList.insertEnd("H");
        Assert.assertEquals(4, secondList.size());

        List<String> mergeList = new LinkedList<>();
        mergeList.insertAll(firstList);
        Assert.assertEquals(4, mergeList.size());

        mergeList.insertAll(secondList);
        Assert.assertEquals(8, mergeList.size());

        Iterator<String> it = mergeList.values();
        Assert.assertEquals("A", it.next());
        Assert.assertEquals("B", it.next());
        Assert.assertEquals("C", it.next());
        Assert.assertEquals("D", it.next());
        Assert.assertEquals("E", it.next());
        Assert.assertEquals("F", it.next());
        Assert.assertEquals("G", it.next());
        Assert.assertEquals("H", it.next());
        Assert.assertFalse(it.hasNext());

    }

}

package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.ContainerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.traversal.Traversal;

/**
 * @author Jordi Àlvarez Canal
 * @author Esteve Mariné Gallisà
 * <p>
 * Data Structures
 * Universitat Oberta de Catalunya (UOC)
 * @version 2.1.0
 */
public class BinaryTreeTest extends ContainerTest {

    AbstractTree<Integer> tree;

    protected Position<Integer> findPosition(Tree<Integer> tree, int elem) {
        Position<Integer> position = null;
        Traversal<Integer> r = tree.positions();
        while (r.hasNext() && position == null) {
            Position<Integer> p = r.next();
            if (p.getElem() == elem)
                position = p;
        }
        return position;
    }


    /**
     * This method creates an incomplete tree. Each node has at most two children.
     * During the creation process, several checks are made on the nodes that
     * are added to the tree.
     * The tree created is:
     *                                  7
     *                              4          2
     *                                 1      8   15
     *                               6           9
     *                                          5
     *
     *
     * <p>
     * If the created container is a binary tree, the tree is created as a binary tree. If not,
     * is done as a general tree.
     *
     * @return
     */
    @Before
    public void setUp() {
        tree = new BinaryTreeArrayImpl<Integer>();
        init();
    }

    public void init() {
        Assert.assertTrue(tree.isEmpty());
        Assert.assertEquals(0, tree.size());
        Position<Integer> pos7 = tree.add(null, 7);
        Assert.assertSame(tree.root() , pos7);
        Assert.assertTrue(tree.isLeaf(pos7));
        Assert.assertTrue(!tree.isEmpty());
        Assert.assertEquals(1, tree.size());
        Assert.assertEquals(1, tree.size(pos7));
        Position<Integer> pos4 = addLeft(tree, pos7, 4);
        Assert.assertTrue(tree.isLeaf(pos4));
        Assert.assertTrue(!tree.isLeaf(pos7));
        Assert.assertEquals(2, tree.size());
        Assert.assertEquals(2, tree.size(pos7));
        Position<Integer> pos2 = addRight(tree, pos7, 2);
        Assert.assertTrue(!tree.isLeaf(pos7));
        Assert.assertTrue(tree.isLeaf(pos2));
        Assert.assertEquals(3, tree.size());
        Assert.assertEquals(3, tree.size(pos7));
        Position<Integer> pos1 = addRight(tree, pos4, 1);
        Assert.assertTrue(tree.isLeaf(pos1));
        Assert.assertTrue(!tree.isLeaf(pos4));
        Assert.assertEquals(4, tree.size());
        Assert.assertEquals(2, tree.size(pos4));
        Position<Integer> pos6 = addLeft(tree, pos1, 6);
        Assert.assertTrue(!tree.isLeaf(pos1));
        Assert.assertTrue(tree.isLeaf(pos6));
        Assert.assertEquals(5, tree.size());
        Assert.assertEquals(2, tree.size(pos1));
        Position<Integer> pos8 = addLeft(tree, pos2, 8);
        Assert.assertTrue(!tree.isLeaf(pos2));
        Assert.assertTrue(tree.isLeaf(pos8));
        Assert.assertEquals(6, tree.size());
        Assert.assertEquals(2, tree.size(pos2));
        Position<Integer> pos15 = addRight(tree, pos2, 15);
        Assert.assertTrue(!tree.isLeaf(pos2));
        Assert.assertTrue(tree.isLeaf(pos15));
        Assert.assertEquals(7, tree.size());
        Assert.assertEquals(3, tree.size(pos2));
        Position<Integer> pos9 = addLeft(tree, pos15, 9);
        Assert.assertTrue(tree.isLeaf(pos9));
        Assert.assertTrue(!tree.isLeaf(pos15));
        Assert.assertEquals(8, tree.size());
        Position<Integer> pos5 = addLeft(tree, pos9, 5);
        Assert.assertTrue(!tree.isLeaf(pos9));
        Assert.assertTrue(tree.isLeaf(pos5));
        Assert.assertSame(tree.root(),  pos7);
        Assert.assertEquals(9, tree.size());
        Assert.assertEquals(9, tree.size(pos7));
        Assert.assertEquals(5, tree.size(pos2));
    }


    protected Position<Integer> addLeft(Tree<Integer> tree, Position<Integer> pare, int elem) {
        return tree.add(pare, elem);
    }


    protected Position<Integer> addRight(AbstractTree<Integer> tree, Position<Integer> parent, int elem) {
        return tree.add(parent, elem);
    }


    @Test
    public void levelOrderTraversalTest() {
        Traversal<Integer> traversal = tree.levelOrderTraversal();
        int[] expectedArray = {7, 4, 2, 1, 8, 15, 6, 9, 5};
        checkTraversal(expectedArray, traversal);
    }

    @Test
    public void preOrderTraversalTest() {
        Traversal<Integer> traversal = tree.preOrderTraversal();
        int[] expectedArray = {7, 4, 1, 6, 2, 8, 15, 9, 5};
        checkTraversal(expectedArray, traversal);
    }


    @Test
    public void postOrderTraversalTest() {
        Traversal<Integer> traversal = tree.postOrderTraversal();
        int[] expectedArray = {6, 1, 4, 8, 5, 9, 15, 2, 7};
        checkTraversal(expectedArray, traversal);
    }

    @Test
    public void deleteTest() {
        Position<Integer> pos2 = findPosition(tree, 2);
        Position<Integer> pos15 = findPosition(tree, 15);
        /*                                       7
         *                                  4          2
         *                                    1      8   15*
         *                                   6           9
         *                                              5
         */
        tree.delete(pos2, pos15);
        Traversal<Integer> traversal = tree.levelOrderTraversal();
        /*                                       7
         *                                  4          2
         *                                    1      8
         *                                   6
         */
        int[] expectedElems = {7, 4, 2, 1, 8, 6};
        checkTraversal(expectedElems, traversal);
        Assert.assertEquals(6, tree.size());
        Assert.assertEquals(1, numElems(tree.children(pos2)));


        Position<Integer> pos1 = findPosition(tree, 1);
        Position<Integer> pos6 = findPosition(tree, 6);
        /*                                       7
         *                                  4          2
         *                                    1      8
         *                                   6*
         */
        tree.delete(pos1, pos6);
        /*                                       7
         *                                  4          2
         *                                    1      8
         */
        int[] expectedElems2 = {7, 4, 2, 1, 8};
        traversal = tree.levelOrderTraversal();
        checkTraversal(expectedElems2, traversal);
        Assert.assertEquals(5, tree.size());
        Assert.assertEquals(0, numElems(tree.children(pos1)));

        Position<Integer> pos7 = findPosition(tree, 7);
        Position<Integer> pos4 = findPosition(tree, 4);
        /*                                       7
         *                                  4*          2
         *                                    1      8
         */
        tree.delete(pos7, pos4);
        /*                                       7
         *                                               2
         *                                           8
         */
        Assert.assertEquals(3, tree.size());
        int[] expectedElems3 = {7, 2, 8};
        traversal = tree.levelOrderTraversal();
        checkTraversal(expectedElems3, traversal);
    }


    @Test
    public void swapTest() {
        Position<Integer> pos4 = findPosition(tree, 4);
        Position<Integer> pos15 = findPosition(tree, 15);
        Position<Integer> pos8 = findPosition(tree, 8);
        /*                                       7
         *                                  4          2
         *                                    1     *8*   *15*
         *                                   6           9
         *                                              5
         */
        tree.swap(pos8, pos15);
        /*                                       7
         *                                  4          2
         *                                    1      *15*   *8*
         *                                   6      9
         *                                        5
         */
        Assert.assertEquals(9, tree.size());
        Traversal<Integer> traversal = tree.levelOrderTraversal();
        int[] expectedElems = {7, 4, 2, 1, 15, 8, 6, 9, 5};
        checkTraversal(expectedElems, traversal);

        /*                                       7
         *                                 *4*          2
         *                                    1      *15*   8
         *                                   6      9
         *                                        5
         */
        tree.swap(pos15, pos4);
        /*                                       7
         *                                 *15*          2
         *                               1           *4*     8
         *                            6            9
         *                                      5
         */
        Assert.assertEquals(9, tree.size());
        traversal = tree.levelOrderTraversal();
        int[] expectedElems2 = {7, 15, 2, 1, 4, 8, 6, 9, 5};
        checkTraversal(expectedElems2, traversal);


        /*                                       7
         *                                 15            2
         *                               1           *4*     *8*
         *                            6            9
         *                                      5
         */
        tree.swap(pos4, pos8);
        /*                                       7
         *                                  15          2
         *                               1           *8*     *4*
         *                            6            9
         *                                      5
         */
        Assert.assertEquals(9, tree.size());
        traversal = tree.levelOrderTraversal();
        int[] expectedElems3 = {7, 15, 2, 1, 8, 4, 6, 9, 5};
        checkTraversal(expectedElems3, traversal);

        /*                                       7
         *                                 *15*          2
         *                               1           8     *4*
         *                            6            9
         *                                      5
         */
        tree.swap(pos4, pos15);
        /*                                       7
         *                                 *4*          2
         *                               1           8     *15*
         *                            6            9
         *                                      5
         */

        traversal = tree.levelOrderTraversal();
        int[] expectedElems4 = {7, 4, 2, 1, 8, 15, 6, 9, 5};
        checkTraversal(expectedElems4, traversal);
    }


    @Test
    public void updateTest() {
        Position<Integer> pos4 = findPosition(tree, 4);
        Position<Integer> pos15 = findPosition(tree, 15);
        Position<Integer> pos8 = findPosition(tree, 8);
        /*
         *                                       7
         *                                  4          2
         *                                    1      *8*   15
         *                                   6           9
         *                                              5
         */
        tree.update(pos8, 15);
        /*
         *                                       7
         *                                  4          2
         *                                    1    *15*   15
         *                                   6          9
         *                                             5
         */
        Traversal<Integer> traversal = tree.levelOrderTraversal();
        int[] expectedElems1 = {7, 4, 2, 1, 15, 15, 6, 9, 5};
        checkTraversal(expectedElems1, traversal);

        /*
         *                                       7
         *                                  4          2
         *                                    1     15   *15*
         *                                   6          9
         *                                             5
         */
        tree.update(pos15, 8);
        /*
         *                                       7
         *                                  4          2
         *                                    1     15   *8*
         *                                   6          9
         *                                             5
         */
        traversal = tree.levelOrderTraversal();
        int[] expectedElems2 = {7, 4, 2, 1, 15, 8, 6, 9, 5};
        checkTraversal(expectedElems2, traversal);

        /*
         *                                       7
         *                                  *4*        2
         *                                     1    15     8
         *                                   6          9
         *                                             5
         */
        tree.update(pos4, 16);
        /*
         *                                       7
         *                                  *16*        2
         *                                     1    15    8
         *                                   6          9
         *                                             5
         */
        traversal = tree.levelOrderTraversal();
        int[] expectedElems3 = {7, 16, 2, 1, 15, 8, 6, 9, 5};
        checkTraversal(expectedElems3, traversal);

        /*
         *                                       7
         *                                  16           2
         *                                     1    *15*    8
         *                                   6          9
         *                                             5
         */
        tree.update(pos8, 17);
        /*
         *                                       7
         *                                  16        2
         *                                     1    *17*    8
         *                                   6          9
         *                                            5
         */
        traversal = tree.levelOrderTraversal();
        int[] expectedElems4 = {7, 16, 2, 1, 17, 8, 6, 9, 5};
        checkTraversal(expectedElems4, traversal);

        /*
         *                                       7
         *                                  16          2
         *                                     1    *15*    17
         *                                   6          9
         *                                            5
         */
        tree.update(pos15, 18);
        /*
         *                                       7
         *                                  16          2
         *                                     1    *18*    17
         *                                   6          9
         *                                            5
         */
        traversal = tree.levelOrderTraversal();
        int[] expectedElems5 = {7, 16, 2, 1, 17, 18, 6, 9, 5};
        checkTraversal(expectedElems5, traversal);
    }

}

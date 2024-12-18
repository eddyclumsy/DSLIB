package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.sequential.ContainerTest;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.traversal.Traversal;

public class AVLTreeTest extends ContainerTest {
    AVLTree<Integer> avl;

    @Before
    public void setUp() {
        avl = new AVLTree<Integer>();
    }

    /**
     * <PRE>
     * <p>
     *          50                            100
     *        /    \                        /     \
     *       /      \          DD          /       \
     *      10     100    ---------->    50        300
     *    /   \                        /   \         \
     *  /       \                    /      \         \
     *  75       300                10      75       500
     *   \
     *    \
     *    500
     *
     * </PRE>
     */
    @Test
    public void testAddDD1() {

        avl.add(50);
        avl.add(10);
        avl.add(100);
        avl.add(75);
        avl.add(300);

        Traversal<Integer> traversal = avl.levelOrderTraversal();
        int[] expected1 = {50, 10, 100, 75, 300};
        checkTraversal(expected1, traversal);

        avl.add(500);
        traversal = avl.levelOrderTraversal();
        int[] expected2 = {100, 50, 300, 10, 75, 500};
        checkTraversal(expected2, traversal);
    }

    /**
     *            50                        100
     *          /    \                     /     \
     *         /      \        DD         /       \
     *       10       100  ---------->   50       300
     *      /   \                                   \
     *    /       \                                  \
     *  75       300                                 75
     * </PRE>
     */
    @Test
    public void testAddDD2() {

        avl.add(50);
        avl.add(10);
        avl.add(100);
        avl.add(75);
        avl.add(300);

        Traversal<Integer> traversal = avl.levelOrderTraversal();
        int[] expected1 = {50, 10, 100, 75, 300};
        checkTraversal(expected1, traversal);

        avl.delete(10);

        traversal = avl.levelOrderTraversal();
        int[] expected2 = {100, 50, 300, 75};
        checkTraversal(expected2, traversal);
    }

}

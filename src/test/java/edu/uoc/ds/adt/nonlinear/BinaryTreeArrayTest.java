package edu.uoc.ds.adt.nonlinear;

import org.junit.Before;


public class BinaryTreeArrayTest extends BinaryTreeTest {


    @Before
    public void setUp() {
        tree = new BinaryTreeArrayImpl<>();
        init();
    }

}

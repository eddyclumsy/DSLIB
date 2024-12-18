package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.modelstest.Gender;
import edu.uoc.ds.adt.modelstest.Muppet;
import org.junit.Assert;
import org.junit.Test;
import edu.uoc.ds.adt.sequential.FiniteContainer;
import edu.uoc.ds.traversal.Iterator;

public abstract class DictionaryTest {

    protected Dictionary<String, Muppet> dictionary;


    @Test
    public void putTest() {
        Assert.assertEquals(3, dictionary.size());
        dictionary.put("Rowlf1962", new Muppet("Rowlf1962", "Rowlf the XXX", Gender.Female));
        Assert.assertEquals(4, dictionary.size());
        Assert.assertTrue(((FiniteContainer) dictionary).isFull());
    }

    @Test
    public void getTest() {
        Assert.assertEquals(3, dictionary.size());
        Muppet muppet = dictionary.get("KERM1955");
        Assert.assertEquals("Kermit the Frog", muppet.name());
    }

    @Test
    public void deleteTest() {
        Assert.assertEquals(3, dictionary.size());
        dictionary.delete("KERM1955");
        Assert.assertEquals(2, dictionary.size());
        Muppet muppet = dictionary.get("KERM1955");
        Assert.assertNull(muppet);
    }

    @Test
    public void containsTest() {
        Assert.assertEquals(3, dictionary.size());
        Assert.assertTrue(dictionary.containsKey("KERM1955"));
    }

    @Test
    public void keysTest() {
        Assert.assertEquals(3, dictionary.size());
        Iterator<String> it = dictionary.keys();

        Assert.assertEquals("ELM1980", it.next());
        Assert.assertEquals("PIG1974", it.next());
        Assert.assertEquals("KERM1955", it.next());
    }

    @Test
    public void valuesTest() {
        Assert.assertEquals(3, dictionary.size());
        Iterator<Muppet> it = dictionary.values();

        Assert.assertEquals("Elmo", it.next().name());
        Assert.assertEquals("Miss Piggy", it.next().name());
        Assert.assertEquals("Kermit the Frog", it.next().name());
    }


}

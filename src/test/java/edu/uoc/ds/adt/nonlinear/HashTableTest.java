package edu.uoc.ds.adt.nonlinear;

import edu.uoc.ds.adt.modelstest.Gender;
import edu.uoc.ds.adt.modelstest.Muppet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.uoc.ds.traversal.Iterator;


public class HashTableTest extends DictionaryTest {

    @Before
    public void setUp() {
        dictionary = new HashTable<>();
        dictionary.put("ELM1980", new Muppet("ELM1980", "Elmo", Gender.Male));
        dictionary.put("PIG1974", new Muppet("PIG1974", "Miss Piggy", Gender.Female));
        dictionary.put("KERM1955", new Muppet("KERM1955", "Kermit the Frog", Gender.Female));
    }

    @Test
    public void putTest() {
        Assert.assertEquals(3, dictionary.size());
        dictionary.put("Rowlf1962", new Muppet("Rowlf1962", "Rowlf the XXX", Gender.Female));
        Assert.assertEquals(4, dictionary.size());
        Muppet muppet1 = dictionary.get("Rowlf1962");
        Assert.assertEquals("Rowlf the XXX", muppet1.name());
        dictionary.put("Rowlf1962", new Muppet("Rowlf1962", "Rowlf the Dog", Gender.Female));
        Muppet muppet2 = dictionary.get("Rowlf1962");
        Assert.assertEquals("Rowlf the Dog", muppet2.name());
        Assert.assertEquals(4, dictionary.size());

    }

    @Test
    public void keysTest() {
        Assert.assertEquals(3, dictionary.size());
        Iterator<String> it = dictionary.keys();

        Assert.assertEquals("KERM1955", it.next());
        Assert.assertEquals("ELM1980", it.next());
        Assert.assertEquals("PIG1974", it.next());
    }

    @Test
    public void valuesTest() {
        Assert.assertEquals(3, dictionary.size());
        Iterator<Muppet> it = dictionary.values();

        Assert.assertEquals("Kermit the Frog", it.next().name());
        Assert.assertEquals("Elmo", it.next().name());
        Assert.assertEquals("Miss Piggy", it.next().name());
    }


}
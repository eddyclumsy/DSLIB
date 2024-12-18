package edu.uoc.ds.adt.sequential;

import org.junit.Before;
import edu.uoc.ds.adt.modelstest.Gender;
import edu.uoc.ds.adt.modelstest.Muppet;
import edu.uoc.ds.adt.nonlinear.DictionaryTest;

public class DictionaryArrayTest extends DictionaryTest {

    public static final int FULL_SIZE = 4;


    @Before
    public void setUp() {
        dictionary = new DictionaryArrayImpl<>(FULL_SIZE);
        dictionary.put("ELM1980", new Muppet("ELM1980", "Elmo", Gender.Male));
        dictionary.put("PIG1974", new Muppet("PIG1974", "Miss Piggy", Gender.Female));
        dictionary.put("KERM1955", new Muppet("KERM1955", "Kermit the Frog", Gender.Female));
    }


}

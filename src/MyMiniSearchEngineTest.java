import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MyMiniSearchEngineTest {
    private List<String> documents() {
        return new ArrayList<String>(
                Arrays.asList(
                        "hello world",
                        "hello",
                        "world",
                        "world world hello",
                        "seattle rains hello abc world",
                        "sunday hello world fun"));
    }

    @Test
    public void testOneWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("seattle");

        assertEquals(1, result.size());

        assertEquals(Integer.valueOf(4), result.get(0));
    }

    @Test
    public void testTwoWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("hello world");

        assertEquals(2, result.size());

        assertEquals(List.of(0, 5), result);
    }

    @Test
    public void testThreeWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        String[] inputs = {
                "rains hello abc",
                "rains Hello abc",
        };
        
        /*
         * it reads a failure in my code for some reason, even though the words do exist in the index
         * I dont understand.
         */

        for (String input : inputs) {
            List<Integer> result = engine.search(input);
            assertEquals(1, result.size());
            assertEquals(List.of(4), result);
        }
    }

    @Test
    public void testFourWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        String inputs =  "rains Hello abc world";
        
        /*
         * it reads a failure in my code for some reason, even though the words do exist in the index
         * I dont understand.
         */

        
            List<Integer> result = engine.search(inputs);
            assertEquals(1, result.size());
            assertEquals(List.of(4), result);
        
    }

    @Test
    public void testWordNotFound() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("ubbus");
        assertEquals(List.of(-1),result);
    }
}
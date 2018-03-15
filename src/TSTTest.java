import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class TSTTest {

    @Test
    public void testEmpty(){
        TST<Integer> trie = new TST<>();
        assertEquals("size of an empty trie should be 0",0, trie.size());
        assertFalse("searching an empty trie should return false",trie.contains(""));
        assertNull("getting from an empty trie should return null",trie.get(""));
    }

    @Test
    public void testPut() {
        TST<Integer> trie = new TST<>();
        trie.put("Hello", 0);
        trie.put("Senatus", 1);
        trie.put("Populus que", 2);
        trie.put("Romanus", 3);
        assertTrue("'Hello' should be present", trie.contains("Hello"));
        assertTrue("'Populus que' should be present", trie.contains("Populus que"));
        assertFalse("'Robbie' should not be present", trie.contains("Robbie"));
    }

    @Test
    public void testGet() {
        TST<Integer> trie = new TST<>();
        trie.put("Hello", 0);
        trie.put("Senatus", 1);
        trie.put("Populus que", 2);
        trie.put("Romanus", 3);
        assertEquals("'Romanus' value should equal 3", (Integer) 3, trie.get("Romanus"));
        assertEquals("'Populus que' value should equal 3", (Integer) 2, trie.get("Populus que"));

        assertNull("blah",trie.get("romanus"));
    }

    @Test
    public void testContains() {
        TST<Integer> trie = new TST<>();
        trie.put("Hello", 0);
        trie.put("Senatus", 1);
        trie.put("Populus que", 2);
        trie.put("Romanus", 3);
        assertTrue("Tree should contain 'Romanus'", trie.contains("Romanus"));
        assertTrue("Tree should contain 'Populus que'", trie.contains("Populus que"));
        assertFalse("Tree does not contain 'paella'", trie.contains("paella"));
        assertFalse("Trie returns false for empty String", trie.contains(""));
    }

    @Test
    public void testKeysWithPrefix() {
        TST<Integer> trie = new TST<>();
        trie.put("Hello", 0);
        trie.put("Senatus", 1);
        trie.put("Populus que", 2);
        trie.put("Publicola", 3);
        trie.put("Pompey Magnus", 4);
        trie.put("Paterfamilias", 5);
        trie.put("Romanus", 6);
        LinkedList<String> orderedKeys = trie.keysWithPrefix("Po");
        LinkedList<String> correctOrder = new LinkedList<>();
        correctOrder.add("Pompey Magnus");
        correctOrder.add("Populus que");
        assertEquals(correctOrder, orderedKeys);
    }

    @Test
    public void testEdgy() {
        TST<Integer> trie = new TST<>();
        assertFalse((trie.contains("Try")));
        trie.put("Try", 0);
        assertFalse(trie.contains(""));
        assertFalse(trie.contains(null));

        trie = new TST<>();
        assertNull(trie.get("Try"));
        trie.put("Try", 0);
        assertNull(trie.get(""));
        assertNull(trie.get(null));

        trie = new TST<>();
        trie.put("", 0);
        trie.put(null, 2);

        trie = new TST<>();
        assertNull(trie.keysWithPrefix(""));
        assertNull(trie.keysWithPrefix(null));
        trie.put("Patrick <3", 1);
        assertNull(trie.keysWithPrefix("Conor"));
    }



//    public static void main(String[] args) throws IOException, ParseException, FileNotFoundException {
//        JSONParser parser = new JSONParser();
//        Object object = parser.parse(new FileReader("src/BUSES_SERVICE_0.json"));
//        JSONArray array = (JSONArray) object;
//        TST<Integer> trie = new TST<>();
//        int k = 0;
//        for (Object obj : array) {
//            String dest = ((JSONObject) obj).get("VehicleNo").toString();
//            if (!trie.contains(dest)) {
//                k++;
//                trie.put(dest, 1);
//            }
//            else {
//                int count = trie.get(dest);
//                trie.put(dest, count + 1);
//            }
//        }
//        System.out.println("Unique Destinations: " + k);
//
//        System.out.println("SOUTHSIDE destination present: " + trie.contains("SOUTHSIDE"));
//
//        int n = 0;
//        for (Object obj : array) {
//            if (((JSONObject) obj).get("Destination").toString().startsWith("DOWN")) {
//                n++;
//            }
//        }
//
//        System.out.println("Buses going to down: " + n);
//
//        TST trie2 = new TST<Long>();
//        In in = new In("src/google-books-common-words.txt");
//        String wordsFull = in.readAll();
//        String[] wordsArray = wordsFull.split("\n");
//        int c = 0;
//        for(String w : wordsArray) {
//            String[] wData = w.split("\t");
//            if (wData[0].startsWith("TEST")) {
//                c++;
//            }
//            Long val = Long.parseLong(wData[1]);
//            trie2.put(wData[0], val);
//        }
//        System.out.println("Words present: " + trie2.size());
//        System.out.println("ALGORITHM count: " + trie2.get("ALGORITHM"));
//        System.out.println("EMOJI present: " + trie2.contains("EMOJI"));
//        System.out.println("BLAH present: " + trie2.contains("BLAH"));
//        System.out.println("TEST prefix count: " + trie2.keysWithPrefix("TEST").size());
//        System.out.println(trie2.keysWithPrefix("TEST"));
//        System.out.println(trie2.size());
//    }
}
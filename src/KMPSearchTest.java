import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Question answers.
 *
 * 1. "VehicleNo" occurs 987 times in BUSES_SERVICE_0.json
 *
 * 2. Vehicle Number 16555 IS present in the data.
 *
 * 3. First occurence of HAMPTON PARK is at index 19774.
 *
 * 4. No information about vehicle number 9043409 contained in the data.
 *
 **/

public class KMPSearchTest {

    @Test
    public void testEmpty() {
        assertEquals("Empty text or pattern is invalid",-1,KMPSearch.searchFirst("",""));
        assertEquals("Empty text or pattern is invalid",0,KMPSearch.searchAll("",""));
        assertFalse("Empty text or pattern is invalid",KMPSearch.contains("",""));
    }

    @Test
    public void testContains() {
        String s = "Then out spake brave Horatius, the captain of the gate:" +
                "To every man upon this Earth death cometh soon or late" +
                " and how better for man to die than against fearful odds." +
                "For the ashes of his fathers and the temples of his gods.";
        assertTrue("Text contains 'Temples'", KMPSearch.contains(s, "temples"));
        assertFalse("Text does not contain 'Sean'", KMPSearch.contains(s, "Sean"));
    }

    @Test
    public void testSearchFirst() {
        String s = "Then out spake brave Horatius, the captain of the gate:" +
                "To every man upon this Earth death cometh soon or late" +
                " and how better for man to die than against fearful odds." +
                "For the ashes of his fathers and the temples of his gods.";
        assertEquals("First occurrence of Horatius should occur", 21, KMPSearch.searchFirst(s, "Horatius"));
        assertEquals("No occurence of 'Robert'", -1, KMPSearch.searchFirst(s, "Robert"));
    }

    @Test
    public void testSearchAll() {
        String s = "Then out spake brave Horatius, the captain of the gate:" +
                "To every man upon this Earth death cometh soon or late" +
                " and how better for man to die than against fearful odds." +
                "For the ashes of his fathers and the temples of his gods.";
        assertEquals("Count of 'of'", 3, KMPSearch.searchAll(s, "of"));
        assertEquals("Count of 'Luan'", 0, KMPSearch.searchAll(s, "Disco"));
    }

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader("src/BUSES_SERVICE_0.json"));
        JSONArray array = (JSONArray) object;
        String data = array.toString();

        //Question One
        String pattern = "VehicleNo";
        int vehicleCount = KMPSearch.searchAll(data, pattern);
        System.out.println(vehicleCount + " hits on '" + pattern + "' in data.\n");

        //Question Two
        pattern = "16555";
        if (KMPSearch.contains(data, pattern)) {
            System.out.println("Text contains the pattern '" + pattern + "'.\n");
        }
        else {
            System.out.println("Text does not contain pattern.\n");
        }

        //Question Three
        pattern = "HAMPTON PARK";
        int index = KMPSearch.searchFirst(data, pattern);
        System.out.println("First instance of '" + pattern + "' occurs at index number " + index + ".\n");

        //Question Four
        pattern = "9043409";
        if (KMPSearch.contains(data, pattern)) {
            System.out.println("Text contains the pattern '" + pattern + "'.\n");
        }
        else {
            System.out.println("Text does not contain pattern.\n");
        }
    }

}
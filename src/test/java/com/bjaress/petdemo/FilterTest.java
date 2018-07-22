package com.bjaress.petdemo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/**
 * Unit test for filtering CSV rows.
 */
public class FilterTest extends TestCase {

    public void testEmptyQuery() {
        Filter filter = Filter.from(
                new String[] {"header1"},
                Collections.emptyMap());

        assertTrue(filter.isMatch(new String[] {}));
        assertTrue(filter.isMatch(new String[] {"data1"}));
        assertTrue(filter.isMatch(new String[] {"data1", "data2"}));
    }

    public void testExactMatch() {
        Map<String, String> query = new HashMap<>();
        query.put("header1", "data1");
        query.put("header2", "data2");

        Filter filter = Filter.from(
                new String[] {"header1", "header2"},
                query);

        assertTrue(filter.isMatch(new String[] {"data1", "data2"}));
    }

    public void testSubsetMatch() {
        Map<String, String> query = new HashMap<>();
        query.put("header1", "data1");

        Filter filter = Filter.from(
                new String[] {"header1", "header2"},
                query);

        assertTrue(filter.isMatch(new String[] {"data1", "data2"}));
    }

    public void testHeaderMismatch() {
        Map<String, String> query = new HashMap<>();
        query.put("header1", "data1");

        Filter filter = Filter.from(
                new String[] {"header2"},
                query);

        assertFalse(filter.isMatch(new String[] {"data2"}));
    }

    public void testDataMismatch() {
        Map<String, String> query = new HashMap<>();
        query.put("header1", "data1");

        Filter filter = Filter.from(
                new String[] {"header1"},
                query);

        assertFalse(filter.isMatch(new String[] {"data2"}));
    }


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FilterTest( String testName ) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(FilterTest.class);
    }

}

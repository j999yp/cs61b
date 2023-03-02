package lab9tester;

import static org.junit.Assert.*;

import org.junit.Test;
import lab9.BSTMap;

import java.util.*;

/**
 * Tests by Brendan Hu, Spring 2015, revised for 2018 by Josh Hug
 */
public class TestBSTMap {

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<String, String>();
            BSTMap<String, Integer> b = new BSTMap<String, Integer>();
            BSTMap<Integer, String> c = new BSTMap<Integer, String>();
            BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i));
            assertTrue(b.get("hi" + i).equals(1 + i));
            assertTrue(b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null, b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertTrue(b.get("hi") != null);
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }

    @Test
    public void remove_test()
    {
        BSTMap<Integer, String> t = new BSTMap<Integer, String>();
        // left subtree
        t.put(2,"2");
        t.put(1,"1");
        t.put(0,"0");
        t.put(3,"3");
        t.remove(1);
        List<Integer> a = Arrays.asList(new Integer[]{2,0,3});
        List<Integer> b = new ArrayList<Integer>();
        t.preorder(b,t.root);
        assertTrue(a.equals(b));
        t.clear();
        // right subtree
        t.put(2,"2");
        t.put(1,"1");
        t.put(3,"3");
        t.put(4,"4");
        t.remove(3);
        a = Arrays.asList(new Integer[]{2,1,4});
        b.clear();
        t.preorder(b, t.root);
        assertTrue(a.equals(b));
        t.clear();
        // leaf node
        t.put(2,"2");
        t.put(1,"1");
        t.put(3,"3");
        t.put(0,"0");
        t.put(4,"4");
        t.remove(0);
        t.remove(4);
        a = Arrays.asList(new Integer[]{2,1,3});
        b.clear();
        t.preorder(b, t.root);
        assertTrue(a.equals(b));
        t.clear();
        // 2 children
        t.put(6,"6");
        t.put(1,"1");
        t.put(0,"0");
        t.put(4,"4");
        t.put(2,"2");
        t.put(5,"5");
        t.put(3,"3");
        t.remove(1);
        a = Arrays.asList(new Integer[]{6,2,0,4,3,5});
        b.clear();
        t.preorder(b, t.root);
        assertTrue(a.equals(b));
        t.clear();
    }

    @Test
    public void set_test(){
        BSTMap<Integer, String> t = new BSTMap<Integer, String>();
        t.put(2,"2");
        t.put(1,"1");
        t.put(0,"0");
        t.put(3,"3");
        Set<Integer> s = new HashSet<Integer>();
        s.add(0);s.add(1);s.add(2);s.add(3);
        assertTrue(s.equals(t.keySet()));
    }

    @Test
    public void val_check_test(){
        BSTMap<Integer, String> t = new BSTMap<Integer, String>();
        t.put(2,"2");
        t.put(1,"1");
        t.put(0,"0");
        t.put(3,"3");
        t.remove(2, "1");
        List<Integer> a = Arrays.asList(new Integer[]{2,1,0,3});
        List<Integer> b = new ArrayList<Integer>();
        t.preorder(b, t.root);
        assertTrue(a.equals(b));
        t.remove(2, "2");
        a = Arrays.asList(new Integer[]{3,1,0});
        b.clear();
        t.preorder(b, t.root);
        assertTrue(a.equals(b));
    }
}

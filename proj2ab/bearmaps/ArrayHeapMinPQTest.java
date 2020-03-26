package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void containsTest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("c", 3.0);
        pq.add("a", 1.0);
        pq.add("b", 2.0);
        assertTrue(pq.contains("a"));
        assertFalse(pq.contains("d"));
    }

    @Test
    public void addTest(){
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("c", 3.0);
        pq.add("a", 1.0);
        pq.add("b", 2.0);
        pq.add("d",0.5);
        assertEquals("d", pq.getSmallest());
    }

    @Test
    public void getSmallestTest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("c", 3.0);
        pq.add("a", 1.0);
        pq.add("b", 2.0);
        pq.add("d", 0.5);
        assertEquals("d", pq.getSmallest());
    }

    @Test
    public void removeSmallestTest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("c", 3.0);
        pq.add("a", 1.0);
        assertEquals("a", pq.getSmallest());
        pq.add("b", 2.0);
        pq.add("d", 0.5);
        assertEquals("d", pq.removeSmallest());
        assertEquals("a", pq.getSmallest());
        assertEquals("a", pq.removeSmallest());
        assertEquals("b", pq.getSmallest());
        assertEquals("b", pq.removeSmallest());
        assertEquals("c", pq.getSmallest());
        assertEquals("c", pq.removeSmallest());
    }

    @Test
    public void sizeTest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        assertEquals(0, pq.size());
        pq.add("c", 3.0);
        pq.add("a", 1.0);
        assertEquals(2, pq.size());
        pq.add("b", 2.0);
        pq.add("d", 0.5);
        assertEquals(4, pq.size());
        pq.removeSmallest();
        assertEquals(3, pq.size());
    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("c", 3.0);
        pq.add("a", 1.0);
        assertEquals("a", pq.getSmallest());
        pq.add("b", 2.0);
        assertEquals("a", pq.getSmallest());
        pq.add("d", 0.5);
        assertEquals("d", pq.getSmallest());
        pq.changePriority("d",5.0);
        assertEquals("a", pq.getSmallest());
        assertEquals("a", pq.removeSmallest());
        assertEquals("c", pq.removeSmallest());
        assertEquals("b", pq.removeSmallest());
        //assertEquals("a", pq.getSmallest());
        ///pq.changePriority("d", 1.5);
        //assertEquals("a", pq.removeSmallest());
        //assertEquals("d", pq.getSmallest());
        //pq.changePriority("d",3.5);
        //assertEquals("b", pq.getSmallest());
        //pq.changePriority("d", 2.5);
        //pq.removeSmallest();
        //assertEquals("b", pq.getSmallest());
    }
}

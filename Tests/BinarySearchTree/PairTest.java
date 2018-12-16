package BinarySearchTree;

import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    @Test
    public void firstSecond() {
        Pair<Integer, String> pair1 = new Pair<>(5, "abc");
        Pair<Integer, String> pair2 = new Pair<>(3, "cba");
        assertEquals(Integer.valueOf(5), pair1.first());
        assertEquals(Integer.valueOf(3), pair2.first());
        assertEquals("abc", pair1.second());
        assertEquals("cba", pair2.second());
    }

    @Test
    public void equalsTest() {
        Pair<Integer, String> pair1 = new Pair<>(5, "abc");
        Pair<Integer, String> pair2 = new Pair<>(5, "abc");
        assertEquals(pair1, pair2);
        pair2 = new Pair<>(3, "cba");
        assertNotEquals(pair1, pair2);
        assertNotEquals(pair1, "Patata");
    }

}
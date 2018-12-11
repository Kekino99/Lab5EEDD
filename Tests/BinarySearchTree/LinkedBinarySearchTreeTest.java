package BinarySearchTree;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class LinkedBinarySearchTreeTest {

    @org.junit.Test
    public void iterator() {
    }

    @org.junit.Test
    public void isEmpty() {
    }

    @org.junit.Test
    public void root() {
    }

    @org.junit.Test
    public void left() {
    }

    @org.junit.Test
    public void right() {
    }

    @org.junit.Test
    public void containsKey() {
    }

    @org.junit.Test
    public void get() {
    }

    @org.junit.Test
    public void put() {
        LinkedBinarySearchTree<Integer, String> tree1 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, String> tree2 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());

        assertEquals(tree1, tree2);
        tree1 = tree1.put(5, "abc");
        tree2 = tree2.put(5, "abc");
        assertEquals(tree1, tree2);
        tree1 = tree1.put(3, "cba");
        tree2 = tree2.put(3, "cba");
        assertEquals(tree1, tree2);
        tree1 = tree1.put(7, "Sergi");
        tree2 = tree2.put(7, "Sergi");
        assertEquals(tree1, tree2);
        tree2 = tree2.put(6, "Jp");
        assertNotEquals(tree1, tree2);
    }

    @Test
    public void notSameSize() {
        LinkedBinarySearchTree<Integer, String> tree1 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, String> tree2 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        tree2.put(5, "abc");
        System.out.println(tree2.toString());
        assertNotEquals(tree1, tree2);
    }

    @Test
    public void update() {
        LinkedBinarySearchTree<Integer, String> tree1 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, String> tree2 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());

        tree1 = tree1.put(5, "abc");
        tree1 = tree1.put(3, "cba");
        tree1 = tree1.put(5, "sss");
        tree2 = tree2.put(5, "sss");
        tree2 = tree2.put(3, "cba");
        assertEquals(tree1, tree2);
    }

    @Test
    public void rearrengedTree() {
        LinkedBinarySearchTree<Integer, String> tree1 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, String> tree2 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());

        tree1 = tree1.put(3, "cba");
        tree1 = tree1.put(7, "Sergi");
        tree1 = tree1.put(5, "abc");
        tree2 = tree2.put(5, "abc");
        tree2 = tree2.put(3, "cba");
        tree2 = tree2.put(7, "Sergi");
        assertNotEquals(tree1, tree2);

    }

    @org.junit.Test
    public void remove() {
        LinkedBinarySearchTree<Integer, String> tree1 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, String> tree2 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());

        tree1 = tree1.put(5, "abc");
        tree2 = tree2.put(5, "abc");
        tree2 = tree2.put(3, "cba");
        tree1 = tree1.put(7, "Sergi");
        tree2 = tree2.put(7, "Sergi");
        assertNotEquals(tree1, tree2);
        tree2 = tree2.remove(3);
        assertEquals(tree1, tree2);
    }

    @org.junit.Test
    public void equals() {
    }

    @Test
    public void string() {
        LinkedBinarySearchTree<Integer, String> tree1 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        tree1.put(1, "abc");
        assertEquals("abc, ", tree1.toString());
    }
}
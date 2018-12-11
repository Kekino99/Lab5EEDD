package BinarySearchTree;

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

        tree1.put(0, "abc");
        tree2.put(0, "abc");
        assertEquals(tree1, tree2);

    }

    @org.junit.Test
    public void remove() {
    }

    @org.junit.Test
    public void equals() {
    }
}
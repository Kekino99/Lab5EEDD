package BinarySearchTree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

public class LinkedBinarySearchTreeTest {

    @org.junit.Test
    public void iterator() {
        //Inorder in a BST should return an ordered keys  list iterator. So I'm planning on using a random BST
        //creator of 20 element to see if the list is sorted. Value is not important, so it will be all "".
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            tree = tree.put(random.nextInt(), "");
        }

        Iterator<Pair<Integer, String>> iter = tree.iterator();
        Pair<Integer, String> last = iter.next();
        //At least has 1 element, (1 putted, 19 updated), so the iter.next() can't fail.
        while (iter.hasNext()) {
            Pair<Integer, String> actual = iter.next();
            if (actual.first() <= last.first()) {
                Assert.fail();
            }
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void noElementIterator() throws NoSuchElementException {
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        Iterator<Pair<Integer, String>> iter = tree.iterator();
        iter.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iteratorRemove() throws UnsupportedOperationException {
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        Iterator<Pair<Integer, String>> iter = tree.iterator();
        iter.remove();
    }

    @org.junit.Test
    public void isEmpty() {
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());

        assertTrue(tree.isEmpty());
        tree = tree.put(5, "abc");
        assertFalse(tree.isEmpty());
    }

    @org.junit.Test
    public void root() {
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());

        tree = tree.put(5, "abc");
        tree = tree.put(3, "cba");
        tree = tree.put(7, "Sergi");
        tree = tree.put(6, "Se");

        assertEquals(new Pair<>(5, "abc"), tree.root());
    }


    @org.junit.Test
    public void leftAndRight() {
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, String> treeL =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, String> treeR =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());


        tree = tree.put(5, "abc");
        tree = tree.put(3, "cba");
        tree = tree.put(7, "Sergi");
        tree = tree.put(6, "Se");

        treeL = treeL.put(3, "cba");
        treeR = treeR.put(7, "Sergi");
        treeR = treeR.put(6, "Se");
        assertEquals(treeL, tree.left());
        assertEquals(treeR, tree.right());


    }

    @org.junit.Test
    public void containsKey() {
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());

        tree = tree.put(5, "abc");
        tree = tree.put(3, "cba");
        tree = tree.put(7, "Sergi");
        tree = tree.put(6, "Se");

        assertTrue(tree.containsKey(5));
        assertTrue(tree.containsKey(3));
        assertTrue(tree.containsKey(7));
        assertTrue(tree.containsKey(6));
        assertFalse(tree.containsKey(1));
        assertFalse(tree.containsKey(2));
        assertFalse(tree.containsKey(4));
        assertFalse(tree.containsKey(8));
    }

    @org.junit.Test
    public void get() {
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());

        tree = tree.put(5, "abc");
        tree = tree.put(3, "cba");
        tree = tree.put(7, "Sergi");
        tree = tree.put(6, "Se");

        assertEquals("abc", tree.get(5));
        assertEquals("cba", tree.get(3));
        assertEquals("Sergi", tree.get(7));
        assertEquals("Se", tree.get(6));
        assertNull(tree.get(128));

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
        tree1 = tree1.put(6, "Se");
        tree2 = tree2.put(6, "Ser");
        assertNotEquals("different, but with same number of elements in the same spot", tree1, tree2);

    }

    @Test
    public void notSameSize() {
        LinkedBinarySearchTree<Integer, String> tree1 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        LinkedBinarySearchTree<Integer, String> tree2 =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        tree2 = tree2.put(5, "abc");
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
        tree1 = tree2;
        tree2 = tree2.remove(200);
        if(tree1 != tree2) { //I want to know that they are exactly the same object, not a "clone".
            Assert.fail();
        }
        tree2 = tree2.put(6, "aaa");
        tree2 = tree2.put(9, "aab");
        tree2 = tree2.put(8, "aba");
        tree2 = tree2.put(10, "abb");
        tree1 = tree1.remove(7);
        tree1 = tree1.put(6, "aaa");
        tree1 = tree1.put(9, "aab");
        tree1 = tree1.put(8, "aba");
        tree1 = tree1.put(10, "abb");
        tree2 = tree2.remove(7);
        assertEquals(tree1, tree2);
        tree2 = tree1.put(3, "cba");
        tree2 = tree2.put(-2, "000");
        tree2 = tree2.put(4, "001");
        tree2 = tree2.put(-1, "010");
        tree2 = tree2.put(1, "011");
        tree2 = tree2.put(0, "100");
        tree1 = tree1.put(1, "011");
        tree1 = tree1.put(-2, "000");
        tree1 = tree1.put(-1, "010");
        tree1 = tree1.put(0, "100");
        tree1 = tree1.put(4, "001");
        tree2 = tree2.remove(3);
        assertEquals(tree1, tree2);
        tree1 = tree1.remove(-2);
        tree2 = tree2.remove(-2);
        assertEquals(tree1, tree2);


    }

    @Test
    public void string() {
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());

        tree = tree.put(5, "abc");
        assertEquals("(5, abc), ", tree.toString());
        tree = tree.put(3, "cba");
        tree = tree.put(7, "Sergi");
        tree = tree.put(6, "Se");
        assertEquals("(3, cba), (5, abc), (6, Se), (7, Sergi), ", tree.toString());
    }

    @Test
    public void equalsTest() {
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        assertNotEquals(tree, "Patata");
    }
}
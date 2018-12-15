package BinarySearchTree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

public class InorderTest {

    @Test
    public void inorder() {
        //Inorder in a BST should return an ordered keys  list iterator. So I'm planning on using a random BST
        //creator of 20 element to see if the list is sorted. Value is not important, so it will be all "".
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            tree = tree.put(random.nextInt(), "");
        }

        ArrayList<Pair<Integer, String>> inorder = Inorder.inorder(tree);
        Iterator<Pair<Integer, String>> iter = inorder.iterator();
        Pair<Integer, String> last = iter.next();
        //At least has 1 element, (1 putted, 19 updated), so the iter.next() can't fail.
        while (iter.hasNext()) {
            Pair<Integer, String> actual = iter.next();
            if (actual.first() <= last.first()) {
                Assert.fail();
            }
        }

    }

    @Test
    public void inorder2() {
        //Inorder in a BST should return an ordered keys  list iterator. So I'm planning on using a random BST
        //creator of 20 element to see if the list is sorted. Value is not important, so it will be all "".
        LinkedBinarySearchTree<Integer, String> tree =
                new LinkedBinarySearchTree<Integer, String>(Comparator.naturalOrder());
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            tree = tree.put(random.nextInt(), "");
        }

        ArrayList<Pair<Integer, String>> inorder = Inorder.inorder2(tree);
        Iterator<Pair<Integer, String>> iter = inorder.iterator();
        Pair<Integer, String> last = iter.next();
        //At least has 1 element, (1 putted, 19 updated), so the iter.next() can't fail.
        while (iter.hasNext()) {
            Pair<Integer, String> actual = iter.next();
            if (actual.first() <= last.first()) {
                Assert.fail();
            }
        }
    }
}
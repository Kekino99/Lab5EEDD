package BinarySearchTree;

import Stack.LinkedStack;

import java.util.ArrayList;

/**
 * Contains useful static methods to make an inorder ArrayList from a LBST passed as a parameter
 */
public class Inorder {

    /**
     * @param tree Tree which wants to be have an inorder traversal
     * @param <K>  Parameter key value passed in the LBST parameter. Can be accessed with first()
     * @param <V>  Parameter value value passed in the LBST parameter. Can be accessed with second()
     * @return an inorder sorted arrayList of the LBST passed as a parameter.
     */
    public static <K, V> ArrayList<Pair<K, V>> inorder(LinkedBinarySearchTree<K, V> tree) {
        ArrayList<Pair<K, V>> array = new ArrayList<>();
        LinkedStack<LinkedBinarySearchTree<K, V>> stack
                = new LinkedStack<LinkedBinarySearchTree<K, V>>();
        pushLeft(stack, tree);
        while(!stack.isEmpty()) {
            LinkedBinarySearchTree<K, V> actual = stack.top();
            stack.pop();
            pushLeft(stack, actual.right());
            array.add(actual.root());
        }
        return array;
    }

    private static <K, V> void pushLeft(LinkedStack<LinkedBinarySearchTree<K, V>> stack, LinkedBinarySearchTree<K, V> tree) {
        LinkedBinarySearchTree<K, V> actual = tree;
        while(!actual.isEmpty()) {
            stack.push(actual);
            actual = actual.left();
        }
    }

    /**
     * @param tree Tree which wants to be have an inorder traversal
     * @param <K>  Parameter key value passed in the LBST parameter. Can be accessed with first()
     * @param <V>  Parameter value value passed in the LBST parameter. Can be accessed with second()
     * @return an inorder sorted arrayList of the LBST passed as a parameter.
     */
    public static <K, V> ArrayList<Pair<K, V>> inorder2(LinkedBinarySearchTree<K, V> tree) {
        ArrayList<Pair<K, V>> array = new ArrayList<>();
        for (Pair<K, V> pair : tree) {
            array.add(pair);
        }
        return array;
    }

}

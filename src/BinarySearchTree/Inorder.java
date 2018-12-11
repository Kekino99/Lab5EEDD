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
        LinkedStack<Pair<LinkedBinarySearchTree<K, V>, Boolean>> stack
                = new LinkedStack<Pair<LinkedBinarySearchTree<K, V>, Boolean>>();
        stack.push(new Pair<>(tree, false));
        while(hasNext(stack)) {
            Pair<LinkedBinarySearchTree<K, V>, Boolean> actual = stack.top();
            stack.pop();
            if(actual.second()) {
                array.add(actual.first().root());
            } else {
                stack.push(new Pair<>(actual.first().right(), false));
                stack.push(new Pair<>(actual.first(), true));
                stack.push(new Pair<>(actual.first().left(), false));
            }
        }
        return array;
    }

    private static <K, V> boolean hasNext(LinkedStack<Pair<LinkedBinarySearchTree<K, V>, Boolean>> stack) {
        while(!stack.isEmpty()){
            if(stack.top().first().isEmpty()){
                stack.pop();
            } else {
                return true;
            }
        }
        return false;
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

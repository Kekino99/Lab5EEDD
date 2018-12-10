package BinarySearchTree;

import Stack.LinkedStack;

import java.util.ArrayList;

public class Inorder {
    //TODO javadoc and Tests
    public static <K, V> ArrayList<Pair<K, V>> inorder(LinkedBinarySearchTree<K, V> tree) {
        ArrayList<Pair<K, V>> array = new ArrayList<>();
        LinkedStack<Pair<LinkedBinarySearchTree<K, V>, Boolean>> stack
                = new LinkedStack<Pair<LinkedBinarySearchTree<K, V>, Boolean>>();
        stack.push(new Pair<>(tree, false));
        while(!stack.isEmpty()) {
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
}

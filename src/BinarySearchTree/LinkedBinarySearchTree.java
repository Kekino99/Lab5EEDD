package BinarySearchTree;

import java.util.Comparator;

/**
 * Makes a "unstable" binary search tree.
 *
 * @param <K> The key value, which will be compared and the natural way of order things.
 * @param <V> The value stored in the binary Tree. Both seen as a SQL parameters k would be the primary key and v would
 *            be the values associated with that key.
 */
public class LinkedBinarySearchTree<K, V> implements BinarySearchTree<K, V> {

    private final Node<K, V> root;
    private final Comparator<K> comparator;

    private static class Node<K, V> {
        private final K key;
        private final V value;
        private final Node<K, V> left;
        private final Node<K, V> right;


        private Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }



    }

    public LinkedBinarySearchTree(Comparator<K> comparator) {
        this.comparator = comparator;
        this.root = new Node<>(null, null, null, null);
    }

    private LinkedBinarySearchTree(Comparator<K> comparator,
                                   Node<K, V> root) {
        this.comparator = comparator;
        this.root = root;
    }


    /**
     * @return True if the BinarySearchTree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * @param key the primary key that will be searched in the tree.
     * @return returns true if the BinarySearchTree contains key value
     */
    @Override
    public boolean containsKey(K key) {
        return false;
    }

    /**
     * @param key The key associated with the value.
     * @return returns the value with the K associated. Returns null if there
     * is no key.
     */
    @Override
    public V get(K key) {
        return null;
    }

    /**
     * @param key   the key value that will be searched of later on to get the
     *              value.
     * @param value the value stored in the BinarySearchTree
     * @return returns the BinarySearchTree with the pair of values added.
     */
    @Override
    public BinarySearchTree<K, V> put(K key, V value) {
        return null;
    }

    /**
     * @param key the key value that will be searched of later on to get the
     *            value.
     * @return returns the BinarySearchTree  with the node removed. If it
     * hadn't had the value it should return the same BinarySearchTree
     */
    @Override
    public BinarySearchTree<K, V> remove(K key) {
        return null;
    }
}

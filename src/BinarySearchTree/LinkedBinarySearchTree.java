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

        private boolean hasBothChild(){
            return hasLeftChild() && hasRightChild();
        }


        private boolean hasLeftChild(){
            return this.left != null;
        }

        private boolean hasRightChild(){
            return this.right != null;
        }

    }

    /**
     * Returns a void LinkedBinaryTree
     * @param comparator comparator that will be used to store and search elements.
     */
    public LinkedBinarySearchTree(Comparator<K> comparator) {
        this.comparator = comparator;
        this.root = new Node<>(null, null, null, null);
    }

    private LinkedBinarySearchTree(Comparator<K> comparator, Node<K, V> root) {
        this.comparator = comparator;
        this.root = root;
    }


    /**
     * @return True if the BinarySearchTree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root.left==null;
    }

    /**
     * @param key the primary key that will be searched in the tree.
     * @return returns true if the BinarySearchTree contains key value
     */
    @Override
    public boolean containsKey(K key) {
        return nodePosition(key).key!=null;

    }

    /**
     * @param key The key associated with the value.
     * @return returns the value with the K associated. Returns null if there
     * is no key.
     */
    @Override
    public V get(K key) {
        return nodePosition(key).value;
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
        if(containsKey(key)){
            return new LinkedBinarySearchTree<>(comparator, removing(root.left, key));
        } else {
            return this;
        }
    }


    private Node<K, V> removing(Node<K, V> node, K key) {
        if(node.key==key) {
            if(node.hasBothChild()){
                Node<K, V> maximum = maxOfNode(node.left);
                Node<K, V> removed = removing(node.left, maximum.key);
                return new Node<>(maximum.key, maximum.value, removed, node.right);
            } else if(node.hasLeftChild()){
                return node.left;
            } else if(node.hasRightChild()) {
                return node.right;
            } else {
                return null;
            }
        } else if (comparator.compare(node.key, key) < 0) {
            return new Node<>(node.key, node.value, removing(node.left, key), node.right);
        } else {
            return new Node<>(node.key, node.value, node.left, removing(node.right, key));
        }
    }


    //Costs O(logN)

    /**
     * @param key the key that wants to be searched.
     * @return Returns the node that contein the key. If key i not initialitzated it will return null.
     */
    private Node<K, V> nodePosition(K key) {
        Node<K, V> actual = navigate(root.left, key);

        while(actual.key != key && actual.key != null){
            actual = navigate(actual, key);
        }

        return actual;

    }

    /**
     * Compares the key with the node key and returns the nearest node of the left or right node.
     * Node.key must be different from key to work it out.
     * @param node the node that wants to be navigated
     * @param key the key that is searched.
     * @return returns the nearest node of the left or right node
     */
    private Node<K, V> navigate(Node<K, V> node, K key) {
        return (comparator.compare(node.key, key) < 0) ? root.left : root.right;
    }

    private Node<K, V> maxOfNode(Node<K, V> node){
        Node<K, V> actual = node.right;
        while(actual.right!=null){
            actual = actual.right;
        }
        return actual;
    }
}

package BinarySearchTree;

/**
 * An interface for a BinarySearchTree.
 * @param <K> The "key" value that will be used to "order" the
 *           BinarySearchTree
 * @param <V> The "value" value that will be stored in the BinarySearchTree.
 */
public interface BinarySearchTree<K, V> {

    /**
     * @return True if the BinarySearchTree is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @param key the primary key that will be searched in the tree.
     * @return returns true if the BinarySearchTree contains key value
     */
    boolean containsKey(K key);

    /**
     * @param key The key associated with the value.
     * @return returns the value with the K associated. Returns null if there
     * is no key.
     */
    V get(K key);

    /**
     * @param key   the key value that will be searched of later on to get the
     *              value.
     * @param value the value stored in the BinarySearchTree
     * @return returns the BinarySearchTree with the pair of values added.
     */
    BinarySearchTree<K, V> put(K key, V value);

    /**
     * @param key the key value that will be searched of later on to get the
     *            value.
     * @return returns the BinarySearchTree  with the node removed. If it
     * hadn't had the value it should return the same BinarySearchTree
     */
    BinarySearchTree<K, V> remove(K key);
}

package BinarySearchTree;

import Stack.LinkedStack;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 * Makes a immutable binary search tree.
 *
 * @param <K> The key value, which will be compared and the natural way of order things.
 * @param <V> The value stored in the binary Tree. Both seen as a SQL
 *            parameters k would be the primary key and v would
 *            be the values associated with that key.
 */
public class LinkedBinarySearchTree<K, V> implements BinarySearchTree<K, V>,
        BinaryTree<Pair<K, V>>, Iterable<Pair<K, V>> {

    private Node<K, V> root;
    private final Comparator<K> comparator;

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new Iter();
    }

    private final class Iter implements Iterator<Pair<K, V>> {
        /* Self notes of the implementation:
         * There is no need of a modCount as the class is not mutable
         * Remove method is tricky, as the tree is immutable so it can't be done.
         */
        LinkedStack<Node<K, V>> stack;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Pair<K, V> next() throws NoSuchElementException {

            if (hasNext()) {
                Node<K, V> actual = stack.top();
                stack.pop();
                pushLeft(actual.right);
                return new Pair<>(actual.key, actual.value);
            } else {
                throw new NoSuchElementException();
            }
        }

        private void pushLeft(Node<K, V> node) {
            Node<K, V> actual = node;
            while (actual != null) {
                stack.push(actual);
                actual = actual.left;
            }
        }

        /**
         * Unsupported Operation, java docs from interface Iterator
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         * <p>
         * The behavior of an iterator is unspecified if this method is called
         * after a call to the {@link #forEachRemaining forEachRemaining} method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        Iter() {
            stack = new LinkedStack<Node<K, V>>();
            pushLeft(LinkedBinarySearchTree.this.root);
        }


    }

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

        private boolean hasBothChild() {
            return hasLeftChild() && hasRightChild();
        }


        private boolean hasLeftChild() {
            return this.left != null;
        }

        private boolean hasRightChild() {
            return this.right != null;
        }

    }

    /**
     * Returns a void LinkedBinaryTree
     *
     * @param comparator comparator that will be used to store and search elements.
     *                   Must be consistent with equals to LinkedBinaryTree.equals work.
     */
    public LinkedBinarySearchTree(Comparator<K> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    private LinkedBinarySearchTree(Comparator<K> comparator, Node<K, V> root) {
        this.comparator = comparator;
        this.root = root;
        //The first node is to represent the void tree, s the left is the root of the actual tree.
    }


    /**
     * @return True if the LinkedBinarySearchTree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }


    /**
     * @return the element "root" of the BinaryTree.
     */
    @Override
    public Pair<K, V> root() {
        if (root != null) {
            // Equivalent with isEmpty, but compiler set a warning
            // to unchecked NullPointerException, so i preferred this
            return new Pair<>(root.key, root.value);
        } else {
            throw new NullPointerException("Void tree");
        }
    }

    /**
     * If searched a left child that doesn't exist casts a NullPointerException
     *
     * @return the left child
     */
    @Override
    public LinkedBinarySearchTree<K, V> left() throws NullPointerException {
        if (root != null) {
            return new LinkedBinarySearchTree<>(this.comparator, root.left);
        } else {
            throw new NullPointerException("Void tree");
        }
    }

    /**
     * If searched a right child that doesn't exist casts a NullPointerException
     *
     * @return the right child
     */
    @Override
    public LinkedBinarySearchTree<K, V> right() throws NullPointerException {
        if (root != null) {
            return new LinkedBinarySearchTree<>(this.comparator, root.right);
        } else {
            throw new NullPointerException("Void tree");
        }
    }

    /**
     * @param key the primary key that will be searched in the tree.
     * @return returns true if the BinarySearchTree contains key value
     */
    @Override
    public boolean containsKey(K key) {
        return nodePosition(key) != null;

    }

    /**
     * @param key The key associated with the value.
     * @return returns the value with the K associated. Returns null if there
     * is no key.
     */
    @Override
    public V get(K key) {
        Node<K, V> node = nodePosition(key);
        return (node == null) ? null : node.value;
    }

    /**
     * @param key   the key value that will be searched of later on to get the
     *              value.
     * @param value the value stored in the BinarySearchTree
     * @return returns the BinarySearchTree with the pair of values added.
     * @throws NullPointerException if key or value is null.
     */
    @Override
    public LinkedBinarySearchTree<K, V> put(K key, V value) throws NullPointerException {
        if (key != null && value != null) {
            return new LinkedBinarySearchTree<>(this.comparator, putting(root, key, value));
        } else {
            throw new NullPointerException("Key or value are null");
        }
    }

    /**
     * @param key the key value that will be searched of later on to get the
     *            value.
     * @return returns the BinarySearchTree  with the node removed. If it
     * hadn't had the value it should return the same BinarySearchTree
     * @throws NullPointerException if key is null
     */
    @Override
    public LinkedBinarySearchTree<K, V> remove(K key) {
        if (key == null) {
            throw new NullPointerException("Key is null");
        } else {
            try {
                return new LinkedBinarySearchTree<>(comparator, removing(root, key));
            } catch (NoSuchElementException ex) {
                return this;
            }
        }

    }

    private Node<K, V> putting(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value, null, null);
        } else if (comparator.compare(node.key, key) == 0) {
            return new Node<>(key, value, node.left, node.right);
        } else if (comparator.compare(node.key, key) > 0) {
            return new Node<>(node.key, node.value, putting(node.left, key, value), node.right);
        } else {
            return new Node<>(node.key, node.value, node.left, putting(node.right, key, value));
        }
    }

    // Node exists, as it checks before calling the function.
    private Node<K, V> removing(Node<K, V> node, K key) throws NoSuchElementException {
        if (node == null) {
            throw new NoSuchElementException();
        } else if (comparator.compare(node.key, key) == 0) {
            if (node.hasBothChild()) {
                Node<K, V> maximum = maxOfNode(node.left);
                Node<K, V> removed = removing(node.left, maximum.key);
                return new Node<>(maximum.key, maximum.value, removed, node.right);
            } else if (node.hasLeftChild()) {
                return node.left;
            } else if (node.hasRightChild()) {
                return node.right;
            } else {
                return null;
            }
        } else if (comparator.compare(node.key, key) > 0) {
            return new Node<>(node.key, node.value, removing(node.left, key), node.right);
        } else {
            return new Node<>(node.key, node.value, node.left, removing(node.right, key));

        }
    }


    //Costs O(logN)

    /**
     * @param key the key that wants to be searched.
     * @return Returns the node that contains the key.
     * If the key was not initialized it will return null.
     */
    private Node<K, V> nodePosition(K key) {
        Node<K, V> actual = root;

        while (actual != null && comparator.compare(actual.key, key) != 0) {
            actual = navigate(actual, key);
        }

        return actual;

    }

    /**
     * Compares the key with the node key and returns the nearest node of the left or right node.
     * Node.key must be different from key to work it out.
     *
     * @param node the node that wants to be navigated
     * @param key  the key that is searched.
     * @return returns the nearest node of the left or right node
     */
    private Node<K, V> navigate(Node<K, V> node, K key) {
        return (comparator.compare(node.key, key) > 0) ? node.left : node.right;
    }

    private Node<K, V> maxOfNode(Node<K, V> node) {
        Node<K, V> actual = node;
        while (actual.right != null) {
            actual = actual.right;
        }
        return actual;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see java.util.HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedBinarySearchTree) {
            LinkedBinarySearchTree<?, ?> tree = (LinkedBinarySearchTree<?, ?>) obj;
            return equals(this.root, tree.root);
        }
        return false;
    }

    private boolean equals(Node<K, V> node1, Node<?, ?> node2) {
        if (node1 != null && node2 != null) {
            return Objects.equals(node1.key, node2.key)
                    && Objects.equals(node1.value, node2.value)
                    && equals(node1.left, node2.left)
                    && equals(node1.right, node2.right);
        } else {
            return node1 == null && node2 == null;
        }
    }

    /**
     * Returns a string representation of all elements in a inorder order.
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (Pair<K, V> kvPair : this) {
            st.append(kvPair).append(", ");
        }
        return st.toString();
    }


}

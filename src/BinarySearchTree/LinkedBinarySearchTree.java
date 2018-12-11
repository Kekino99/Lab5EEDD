package BinarySearchTree;

import Stack.LinkedStack;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

//TODO project: revise javaDocs.

/**
 * Makes a immutable binary search tree.
 *
 * @param <K> The key value, which will be compared and the natural way of order things.
 * @param <V> The value stored in the binary Tree. Both seen as a SQL parameters k would be the primary key and v would
 *            be the values associated with that key.
 */
public class LinkedBinarySearchTree<K, V> implements BinarySearchTree<K, V>, BinaryTree<Pair<K, V>>, Iterable<Pair<K, V>> {

    private final Node<K, V> root;
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

    private class Iter implements Iterator<Pair<K, V>> {
        /* Self notes of the implementation:
         * There is no need of a modCount as the class is not mutable
         * Remove method is tricky, as the tree is immutable but it can be done
         * with auxiliar methods -I think- , so it will be implemented.*/
        //TODO final last check this comment to make sense with the code.
        LinkedStack<Pair<LinkedBinarySearchTree<K, V>, Boolean>> stack;
        Pair<K, V> lastReturned = null;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) { //TODO study if the while does anything
                if (stack.top().first().isEmpty()) {
                    stack.pop();
                } else {
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Pair<K, V> next() throws NoSuchElementException {
            while (hasNext()) {
                Pair<LinkedBinarySearchTree<K, V>, Boolean> actual = stack.top();
                stack.pop();
                if (!actual.first().isEmpty()) {
                    if (actual.second()) {
                        return actual.first().root();
                    } else {
                        stack.push(new Pair<>(actual.first().right(), false));
                        stack.push(new Pair<>(actual.first(), true));
                        stack.push(new Pair<>(actual.first().left(), false));
                    }
                }
            }
            throw new NoSuchElementException();
        }

        /**
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
            //TODO remove operation
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

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node<K, V> node = (Node<K, V>) obj; //TODO solve as the equals of LBST.
                return this.key == node.key && this.value == node.value
                        && Objects.equals(this.left, node.left)
                        && Objects.equals(this.right, node.right);
            }
            return false;
        }

    }

    /**
     * Returns a void LinkedBinaryTree
     *
     * @param comparator comparator that will be used to store and search elements.
     */
    public LinkedBinarySearchTree(Comparator<K> comparator) {
        this.comparator = comparator;
        this.root = new Node<>(null, null, null, null);
    }

    private LinkedBinarySearchTree(Comparator<K> comparator, Node<K, V> root) {
        this.comparator = comparator;
        this.root = new Node<>(null, null, root, null);
        //The first node is to represent the void tree, s the left is the root of the actual tree.
    }


    /**
     * @return True if the LinkedBinarySearchTree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root.left == null;
    }

    /**
     * @return the element "root" of the BinaryTree.
     */
    @Override
    public Pair<K, V> root() {
        return toPair(root.left);
    }

    /**
     * If searched a left child that doesn't exist casts a NullPointerException
     *
     * @return the left child
     */
    @Override
    public LinkedBinarySearchTree<K, V> left() {
        return new LinkedBinarySearchTree<>(this.comparator, root.left.left);
    }

    /**
     * If searched a right child that doesn't exist casts a NullPointerException
     *
     * @return the right child
     */
    @Override
    public LinkedBinarySearchTree<K, V> right() {
        return new LinkedBinarySearchTree<>(this.comparator, root.left.right);
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
        return new LinkedBinarySearchTree<>(this.comparator, putting(root.left, key, value));
    }

    /**
     * @param key the key value that will be searched of later on to get the
     *            value.
     * @return returns the BinarySearchTree  with the node removed. If it
     * hadn't had the value it should return the same BinarySearchTree
     */
    @Override
    public BinarySearchTree<K, V> remove(K key) {
        if (containsKey(key)) {
            return new LinkedBinarySearchTree<>(comparator, removing(root.left, key));
        } else {
            return this;
        }
    }

    private Node<K, V> putting(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value, null, null);
        } else if (node.key == key) {
            return new Node<>(key, value, node.left, node.right);
        } else if (comparator.compare(node.key, key) < 0) {
            return new Node<>(node.key, node.value, putting(node.left, key, value), node.right);
        } else {
            return new Node<>(node.key, node.value, node.left, putting(node.right, key, value));
        }
    }

    // Node exists, as it checks before calling the function.
    private Node<K, V> removing(Node<K, V> node, K key) {
        if (node.key == key) {
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

        while (actual != null && actual.key != key) {
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
        return (comparator.compare(node.key, key) < 0) ? root.left : root.right;
    }

    private Node<K, V> maxOfNode(Node<K, V> node) {
        Node<K, V> actual = node.right;
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
            LinkedBinarySearchTree<K, V> tree = (LinkedBinarySearchTree<K, V>) obj;
            //TODO solve upper line.
            return this.isEmpty() && tree.isEmpty() || Objects.equals(this.root.left, tree.root.left);
        }
        return false;
    }

    private Pair<K, V> toPair(Node<K, V> node) {
        return new Pair<>(node.key, node.value);
    }


}

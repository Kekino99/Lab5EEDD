package BinarySearchTree;

public interface BinaryTree<E> {

    /**
     *
     * @return true if and only it's a void tree.
     */
    boolean isEmpty();

    /**
     *
     * @return the element "root" of the BinaryTree.
     */
    E root();

    /**
     *
     * @return the left child
     */
    BinaryTree<E> left();

    /**
     *
     * @return the right child
     */
    BinaryTree<E> right();

}

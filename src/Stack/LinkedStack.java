package Stack;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> head;

    private static class Node<T> {
        T elem;
        Node<T> next;

        private Node(T elem, Node<T> next) {
            this.elem = elem;
            this.next = next;
        }
    }

    /**
     * Makes an empty stack.
     */
    public LinkedStack() {
        head = null;
    }

    /**
     * @return true if the stack doesn't contain any element
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * @return the element of the top of the stack.
     */
    @Override
    public E top() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Empty Stack");
        } else {
            return head.elem;
        }
    }

    /**
     * Deletes the element of the top of the stack.
     *
     * @throws NoSuchElementException if the stack is Empty
     */
    @Override
    public void pop() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Empty Stack");
        } else {
            head = head.next;
        }
    }

    /**
     * @param e puts element 'e' in the top of the stack.
     */
    @Override
    public void push(E e) {
        head = new Node<>(e, head);
    }

    @Override
    public String toString() {
        Node<E> node = this.head;
        StringBuilder st = new StringBuilder();
        while (node != null) {
            st.append(node.elem.toString()).append(", ");
            node = node.next;
        }
        return st.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedStack) {
            LinkedStack<?> stack2 = (LinkedStack<?>) obj;
            Node<?> node2 = stack2.head;
            Node<E> node1 = this.head;
            while (node1 != null && node2 != null) {
                if (!Objects.equals(node1.elem, node2.elem)) {
                    return false;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
            return node1 == null && node2 == null;
        }
        return false;
    }
}

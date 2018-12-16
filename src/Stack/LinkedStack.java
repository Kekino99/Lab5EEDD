package Stack;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedStack<E> implements Stack<E>, Cloneable {
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
    public LinkedStack<E> clone() { //TODO ask professor about super.clone() method.
        LinkedStack<E> clone = new LinkedStack<E>();
        clone.head = new Node<>(head.elem, this.head.next);
        Node<E> first = clone.head;
        while (clone.head.next != null) {
            Node<E> temp = new Node<>(clone.head.next.elem, clone.head.next.next);
            clone.head.next = temp;
            clone.head = temp;
        }
        clone.head = first;
        return clone;
    }

    @Override
    public String toString() {
        LinkedStack<E> stack = this.clone();
        StringBuilder st = new StringBuilder();
        while (!stack.isEmpty()) {
            st.append(stack.top().toString()).append(", ");
            stack.pop();
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

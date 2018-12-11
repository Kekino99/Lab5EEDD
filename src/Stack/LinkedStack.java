package Stack;

import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> head;

    private class Node<E> {
        E elem;
        Node<E> next;

        private Node(E elem, Node<E> next) {
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
}

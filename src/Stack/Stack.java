package Stack;

import java.util.NoSuchElementException;

public interface Stack<E> {

    /**
     * @return true if the stack doesn't contain any element
     */
    boolean isEmpty();

    /**
     * @return the element of the top of the stack.
     * @throws NoSuchElementException if the stack is Empty
     */
    E top() throws NoSuchElementException;

    /**
     * Deletes the element of the top of the stack.
     *
     * @throws NoSuchElementException if the stack is Empty
     */
    void pop() throws NoSuchElementException;

    /**
     * @param e puts element 'e' in the top of the stack.
     */
    void push(E e);

}

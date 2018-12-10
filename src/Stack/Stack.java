package Stack;

public interface Stack<E> {
    boolean isEmpty();
    E top();
    void pop();
    void push(E e);

}

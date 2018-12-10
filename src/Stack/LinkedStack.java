package Stack;

public class LinkedStack<E> implements Stack<E> {
//TODO throws NullPointerException when not casted corerctly
    private Node<E> head;
    private class Node<E> {
        E elem;
        Node<E> next;

        private Node(E elem, Node<E> next){
            this.elem = elem;
            this.next = next;
        }
    }

    public LinkedStack(){
        head = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E top() {
        return head.elem;
    }

    @Override
    public void pop() {
        head = head.next;
    }

    @Override
    public void push(E e) {
        head = new Node<>(e, head);
    }
}

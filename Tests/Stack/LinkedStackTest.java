package Stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedStackTest {

    //TODO add @Before method with stack (all methods use it)
    @Test
    public void isEmpty() {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        assertTrue(stack.isEmpty());
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            assertFalse(stack.isEmpty());
            stack.pop();
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    public void topPop() {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        for (int i = 9; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), stack.top());
            stack.pop();
        }
    }

    @Test
    public void push() {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        LinkedStack<Integer> stack1 = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack1.push(i);
        }
        assertEquals(stack, stack1);
        stack1.pop();
        assertNotEquals(stack, stack1);
        stack1.push(125);
        assertNotEquals(stack, stack1);

    }

    @Test
    public void toStringTest() {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertEquals("9, 8, 7, 6, 5, 4, 3, 2, 1, 0, ", stack.toString());
    }

    @Test
    public void equalsTest() { //Tests if compared with another object fails and doesn't break
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertNotEquals(stack, "Patata");
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void stackErrorUnexpectedPopTest() throws NoSuchElementException {
        LinkedStack<Integer> stack1 = new LinkedStack<Integer>();
        stack1.pop();
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void stackErrorTopTest() throws NoSuchElementException {
        LinkedStack<Integer> stack1 = new LinkedStack<Integer>();
        stack1.top();
    }

}
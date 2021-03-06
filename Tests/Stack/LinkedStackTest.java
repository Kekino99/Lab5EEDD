package Stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedStackTest {
    LinkedStack<Integer> stack = new LinkedStack<Integer>();

    @Before
    public void initStack() {
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
    }

    @Test
    public void isEmpty() {
        for (int i = 0; i < 10; i++) {
            assertFalse(stack.isEmpty());
            stack.pop();
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    public void topPop() {
        for (int i = 9; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), stack.top());
            stack.pop();
        }
    }

    @Test
    public void push() {
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
        assertEquals("9, 8, 7, 6, 5, 4, 3, 2, 1, 0, ", stack.toString());
    }

    @Test
    public void equalsTest() { //Tests if compared with another object fails and doesn't breakº
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
package BinarySearchTree;

import java.util.Objects;

/**
 * Makes a two paired elements objects. It's immutable
 *
 * @param <S> Can be anything
 * @param <T> Can be anything
 */
public class Pair<S, T> {
    private final S first;
    private final T second;

    /**
     * @param first  Element stored "first". Will be returned when first() method
     * @param second Element stored "second". Will be returned when second() method
     */
    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return Element stored "first"
     */
    public S first() {
        return first;
    }

    /**
     * @return Element stored "second"
     */
    public T second() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ", " +second.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pair) {
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return Objects.equals(this.first, pair.first)
                    && Objects.equals(this.second, pair.second);
        } else {
            return false;
        }
    }
}

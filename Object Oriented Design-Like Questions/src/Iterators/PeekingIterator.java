package Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekingIterator implements Iterator<Integer> {
    private Integer next = null;
    private final Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        Integer res = next;
        next = null;
        return res;
    }

    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        }
        if (!iterator.hasNext()) {
            return false;
        }
        next = iterator.next();
        return true;
    }
}

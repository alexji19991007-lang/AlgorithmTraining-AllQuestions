package Iterators;

import java.util.NoSuchElementException;

class Vector2DIterator {
    private final int[][] v;
    private int i = 0;
    private int j = -1;
    private boolean foundNext = false;

    public Vector2DIterator(int[][] v) {
        this.v = v;
    }

    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        int res = v[i][j];
        foundNext = false;
        return res;
    }

    public boolean hasNext() {
        if (foundNext) { // We do like this to ensure that calling hasNext() multiple times is fine
            return true;
        }
        foundNext = findNext();
        return foundNext;
    }

    private boolean findNext() {
        if (v == null || i >= v.length) {
            return false;
        }
        j++;
        while (i < v.length && j >= v[i].length) {
            i++;
            j = 0;
        }
        return i < v.length;
    }
}
package Iterators;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class ZigzagIterator {
    private final Queue<Iterator<Integer>> queue = new ArrayDeque<>(2);

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        if (v1 != null && !v1.isEmpty()) {
            queue.offer(v1.iterator());
        }
        if (v2 != null && !v2.isEmpty()) {
            queue.offer(v2.iterator());
        }
    }

    public int next() {
        Iterator<Integer> iter = queue.poll();
        int res = iter.next();
        if (iter.hasNext()) {
            queue.offer(iter);
        }
        return res;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

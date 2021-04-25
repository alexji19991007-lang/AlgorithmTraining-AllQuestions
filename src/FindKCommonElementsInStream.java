import java.util.*;

// There are n integer streams of sorted numbers. We are given a list of iterators,
// all of which are at the start of the corresponding streams.
// # of streams >= 1
// We are also given an integer k, where 1 <= k <= # of steams.
// We want to know which numbers exist in at least k streams.
// Notice that duplicating numbers from the same stream is counted once.

// Stream:
/*
stream 1 | 0, 3, 5, 9

stream 2 | 0, 2, 5, 10, 998

stream 3 | 0, 1, 1, 3, 11

…
*/
// If we only look at the above 3 stream and k = 2, the result list should be [0, 3, 5],
// all of which exist in at least 2 streams.


public class FindKCommonElementsInStream {
    public List<Integer> findKCommonElements(List<StreamIterator> iters, int k) {
        List<Integer> res = new ArrayList<>();
        if (iters.size() < k) {
            return res;
        }
        Queue<StreamIterator> minHeap = new PriorityQueue<>((iter1, iter2) -> {
            if (iter1.peek() == iter2.peek()) {
                return 0;
            }
            return iter1.peek() < iter2.peek() ? -1 : 1;
        });
        Map<Integer, Integer> count = new HashMap<>();
        initializeHeap(iters, minHeap, count);
        int lastPrinted = Integer.MIN_VALUE;
        while (minHeap.size() >= k) {
            StreamIterator iter = minHeap.poll();
            int curVal = iter.peek();
            // curVal is in the map for sure.
            int curCount = count.get(curVal);
            // we add it to the res list if:
            // 1. the count is greater than k, i.e. the element occurs in more than or equal to k lists.
            // 2. we have not added anything to the list yet
            //    or
            //    the last value added to the list != the current value
            if (curCount >= k && (res.size() == 0 && lastPrinted != curVal)) {
                res.add(curVal);
                lastPrinted = curVal;
            }
            count.put(curVal, curCount - 1);
            if (curCount == 1) {
                count.remove(curVal);
            }
            while (iter.hasNext() && iter.peek() == curVal) {
                iter.next();
            }
            if (iter.hasNext()) {
                count.put(iter.peek(), count.getOrDefault(iter.peek(), 0) + 1);
                minHeap.offer(iter);
            }
        }
        return res;
    }

    public void initializeHeap(List<StreamIterator> iters, Queue<StreamIterator> minHeap, Map<Integer, Integer> count) {
        for (StreamIterator iter : iters) {
            if (!iter.hasNext()) {
                continue;
            }
            minHeap.offer(iter);
            count.put(iter.peek(), count.getOrDefault(iter.peek(), 0) + 1);
        }
    }
}

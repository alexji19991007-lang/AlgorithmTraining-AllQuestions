package RobinhoodOnsite;

import java.util.*;

public class MediumSlidingWindow {
    public static void main(String[] args) {
        MediumSlidingWindow test = new MediumSlidingWindow();
        int[] nums = {1, 3, 1, 5, 1};
        for (int[] medians : test.findMedians(nums)) {
            System.out.println(Arrays.toString(medians));
        }
    }

    public List<int[]> findMedians(int[] nums) {
        List<int[]> res = new ArrayList<>();
        for (int i = 1; i <= nums.length; i += 2) {
            res.add(medianSlidingWindowBetter(nums, i));
        }
        return res;
    }

    // eager removal
    public int[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int m = n - k + 1; // size of the result
        int[] res = new int[m];

        Queue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        Queue<Integer> minHeap = new PriorityQueue<>(k);
        for (int i = 0; i < n; ++i) {
            int num = nums[i];
            // maxHeap will keep the smaller half
            // minHeap will keep the larger half
            if (maxHeap.size() == 0 || maxHeap.peek() >= num) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            // Maintain two heaps such that:
            // Even # of elements: same size;
            // Odd # of elements: maxHeap has one more element
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
            // If need output
            if (i - k + 1 >= 0) {
                // Only consider odd window cases
                res[i - k + 1] = maxHeap.peek();
                int toRemove = nums[i - k + 1];
                if (toRemove <= maxHeap.peek()) {
                    maxHeap.remove(toRemove);
                } else {
                    minHeap.remove(toRemove);
                }
            }
            // Maintain two heaps
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
        }
        return res;
    }

    // lazy removal
    // TC: O(n + (n - k))logk + O(n - k) = O(nlogk)
    //     - Either (or sometimes both) of the heaps gets every element inserted into it at least once.
    //     Collectively each of those takes about O(logk) time. That is n such insertions.
    //     - About (n−k) removals from the top of the heaps take place (the number of sliding
    //     window instances). Each of those takes about O(logk) time.
    //     - Hash table operations are assumed to take O(1) time each. This happens roughly the
    //     same number of times as removals from heaps take place.
    // SC: O(k) + O(n) = O(n) extra linear space
    //     - Heap collectively requires O(k).
    //     - Hash map requires at most O(n - k) space.
    public int[] medianSlidingWindowBetter(int[] nums, int k) {
        int n = nums.length;
        int m = n - k + 1; // size of the result
        int[] res = new int[m];
        Map<Integer, Integer> invalidCount = new HashMap<>();

        Queue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        Queue<Integer> minHeap = new PriorityQueue<>(k);

        // initialize the sliding window
        int i = 0;
        while (i < k) {
            maxHeap.offer(nums[i++]);
        }
        for (int j = 0; j < k / 2; ++j) {
            minHeap.offer(maxHeap.poll());
        }

        int idx = 0;
        while (i < nums.length) {
            res[idx++] = maxHeap.peek();
            // res[idx++] = k % 2 == 0 ? （maxHeap.peek() + minHeap.peek()) / 2.0 : maxHeap.peek();
            int outNum = nums[i - k], inNum = nums[i++];
            // balance = 0: balanced
            // balance < 0: maxHeap needs more elements, get from minHeap
            // balance > 0: minHeap needs more elements, get from maxHeap
            // If outNum is present in maxHeap, then invalidating this occurrence will unbalance
            // the maxHeap itself. Hence balance must be decremented and vice versa.
            int balance = outNum <= maxHeap.peek() ? -1 : 1;
            // increment the count of this element in the hash map
            invalidCount.put(outNum, invalidCount.getOrDefault(outNum, 0) + 1);

            // number enters the current window
            if (!maxHeap.isEmpty() && inNum <= maxHeap.peek()) {
                balance++;
                maxHeap.offer(inNum);
            } else {
                balance--;
                minHeap.offer(inNum);
            }

            // re-balance heap
            if (balance < 0) {
                maxHeap.offer(minHeap.poll());
                balance++;
            }
            if (balance > 0) {
                minHeap.offer(maxHeap.poll());
                balance--;
            }

            // Once an invalid element reaches either of the heap tops, we remove them and decrement
            // their counts in the hash map
            while (!maxHeap.isEmpty() && invalidCount.getOrDefault(maxHeap.peek(), 0) > 0) {
                int invalidNum = maxHeap.poll();
                invalidCount.put(invalidNum, invalidCount.get(invalidNum) - 1);
            }
            while (!minHeap.isEmpty() && invalidCount.getOrDefault(minHeap.peek(), 0) > 0) {
                int invalidNum = minHeap.poll();
                invalidCount.put(invalidNum, invalidCount.get(invalidNum) - 1);
            }
        }
        // remember to add the last median into the result array
        res[idx] = maxHeap.peek();
        return res;
    }
}

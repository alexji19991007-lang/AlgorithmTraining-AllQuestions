package Codility;

import java.util.PriorityQueue;

// LeetCode 1167
public class MinimumTimeToMergeLists {
    // TC: O(nlogn)
    // SC: O(n)
    public int mergeLists(int[] A) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int minCost = 0;
        for (int listLength : A) {
            minHeap.add(listLength);
        }
        while (minHeap.size() > 1) {
            int s1 = minHeap.poll();
            int s2 = minHeap.poll();
            int cost = s1 + s2;
            minHeap.add(cost);
            minCost = minCost + cost;
        }
        return minCost;
    }
}

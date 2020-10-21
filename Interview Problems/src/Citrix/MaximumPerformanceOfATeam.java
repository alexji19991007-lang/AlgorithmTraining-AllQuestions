package Citrix;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// LeetCode 1383
// TC: O(nlogn + nlogk)
// SC: O(n)
public class MaximumPerformanceOfATeam {
    public static void main(String[] args) {
        MaximumPerformanceOfATeam test = new MaximumPerformanceOfATeam();
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};
        System.out.println(test.maxPerformance(6, speed, efficiency, 3));
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] ess = new int[n][2];
        for (int i = 0; i < n; ++i) {
            ess[i] = new int[]{efficiency[i], speed[i]};
        }
        // 根据Efficiency从高到低
        Arrays.sort(ess, (a, b) -> b[0] - a[0]);
        // Create a min heap of size k to hold the speed.
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        long res = 0, sumS = 0;
        for (int[] es : ess) {
            // add the current worker's speed the min heap
            pq.add(es[1]);
            // get the running sum of speed
            sumS = (sumS + es[1]);
            // if we exceed the maximum number of workers, get rid of the one with the lowest speed.
            // This is because the efficiency is determined by our current worker (remember we ordered
            // ess in descending order of efficiency, so to maximize res, we only need to maximize
            // our current sum of speed.
            if (pq.size() > k) sumS -= pq.poll();
            res = Math.max(res, (sumS * es[0]));
        }
        return (int) (res % (long) (1e9 + 7));
    }
}

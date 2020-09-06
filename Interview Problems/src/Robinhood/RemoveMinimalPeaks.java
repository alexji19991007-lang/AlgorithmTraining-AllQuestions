package Robinhood;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class RemoveMinimalPeaks {
    public static void main(String[] args) {
        RemoveMinimalPeaks test = new RemoveMinimalPeaks();
        int[] nums = {2, 7, 8, 5, 1, 6, 3, 9, 4};
        System.out.println(Arrays.toString(test.removePeaks(nums)));
    }

    public int[] removePeaks(int[] nums) {
        Queue<Number> minHeap = new PriorityQueue<>((i1, i2) -> {
            if (i1.val == i2.val) {
                return 0;
            }
            return i1.val < i2.val ? -1 : 1;
        });
        Number[] numbers = new Number[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            numbers[i] = new Number(nums[i], i - 1, i + 1);
            if ((i - 1 < 0 || nums[i] > nums[i - 1]) && (i + 1 >= nums.length || nums[i] > nums[i + 1])) {
                minHeap.offer(numbers[i]);
            }
        }
        int[] res = new int[nums.length];
        int i = 0;
        while (i < res.length) {
            Number curMinPeak = minHeap.poll();
            res[i++] = curMinPeak.val;
            int curLeftNeighbor = curMinPeak.left;
            int curRightNeighbor = curMinPeak.right;
            if (curLeftNeighbor >= 0) {
                numbers[curLeftNeighbor].right = curRightNeighbor;
            }
            if (curRightNeighbor < numbers.length) {
                numbers[curRightNeighbor].left = curLeftNeighbor;
            }
            if (curLeftNeighbor >= 0 && isPeak(numbers, curLeftNeighbor, numbers[curLeftNeighbor].left, numbers[curLeftNeighbor].right)) {
                minHeap.offer(numbers[curLeftNeighbor]);
            } else if (curRightNeighbor < numbers.length && isPeak(numbers, curRightNeighbor, numbers[curRightNeighbor].left, numbers[curRightNeighbor].right)) {
                minHeap.offer(numbers[curRightNeighbor]);
            }
        }
        return res;
    }

    private boolean isPeak(Number[] numbers, int midIdx, int leftIdx, int rightIdx) {
        return (leftIdx < 0 || numbers[midIdx].val > numbers[leftIdx].val) &&
                (rightIdx >= numbers.length || numbers[midIdx].val > numbers[rightIdx].val);
    }

    static class Number {
        int val;
        int left;
        int right;

        public Number(int val, int left, int right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

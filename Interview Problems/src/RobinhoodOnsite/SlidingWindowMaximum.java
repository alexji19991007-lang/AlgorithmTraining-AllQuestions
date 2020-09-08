package RobinhoodOnsite;

import java.util.*;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        SlidingWindowMaximum test = new SlidingWindowMaximum();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        ResultArrays res = test.maxMinMeanLastSlidingWindow(nums, 4);
        System.out.println(Arrays.toString(res.max));
        System.out.println(Arrays.toString(res.min));
        System.out.println(Arrays.toString(res.mean));
        System.out.println(Arrays.toString(res.last));
        System.out.println();
        ResultArrays[] allRes = test.findAllInfo(nums);
        int i = 1;
        for (ResultArrays resArr : allRes) {
            System.out.println("Window Size: " + i++);
            System.out.println(Arrays.toString(resArr.max));
            System.out.println(Arrays.toString(resArr.min));
            System.out.println(Arrays.toString(resArr.mean));
            System.out.println(Arrays.toString(resArr.last));
            System.out.println();
        }
    }

    public ResultArrays[] findAllInfo(int[] nums) {
        ResultArrays[] res = new ResultArrays[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            res[i] = maxMinMeanLastSlidingWindow(nums, i + 1);
        }
        return res;
    }

    // TC: O(n);
    // SC: O(k);
    public ResultArrays maxMinMeanLastSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new ResultArrays();
        }
        if (k == 1) {
            double[] meanRes = new double[nums.length];
            for (int i = 0; i < nums.length; ++i) {
                meanRes[i] = nums[i];
            }
            return new ResultArrays(nums, nums, meanRes, nums);
        }
        int n = nums.length;
        int idx = 0;

        // data structures to hold info for max and min
        int[] maxRes = new int[n - k + 1];
        int[] minRes = new int[n - k + 1];
        Deque<Integer> maxDq = new ArrayDeque<>();
        Deque<Integer> minDq = new ArrayDeque<>();

        // data structures to hold info for mean
        double[] meanRes = new double[n - k + 1];
        int curSum = 0, left = 0;

        // data structures to hold info for last
        int[] lastRes = new int[n - k + 1];

        for (int i = 0; i < nums.length; ++i) {
            // Deal with maxDq
            // remove elements that are out of our current sliding window
            while (!maxDq.isEmpty() && maxDq.peekFirst() < i - k + 1) {
                maxDq.pollFirst();
            }
            // remove elements that are impossible to be the largest in the current window
            while (!maxDq.isEmpty() && nums[maxDq.peekLast()] < nums[i]) {
                maxDq.pollLast();
            }
            maxDq.offerLast(i);

            // Deal with minDq
            // remove elements that are out of our current sliding window
            while (!minDq.isEmpty() && minDq.peekFirst() < i - k + 1) {
                minDq.pollFirst();
            }
            // remove elements that are impossible to be the smallest in the current window
            while (!minDq.isEmpty() && nums[minDq.peekLast()] > nums[i]) {
                minDq.pollLast();
            }
            minDq.offerLast(i);

            // Deal with mean
            curSum += nums[i];

            // output result
            if (i >= k - 1) {
                maxRes[idx] = nums[maxDq.peekFirst()];
                minRes[idx] = nums[minDq.peekFirst()];
                meanRes[idx] = curSum * 1.0 / k;
                lastRes[idx++] = nums[i];
                curSum -= nums[left++];
            }
        }
        return new ResultArrays(maxRes, minRes, meanRes, lastRes);
    }

    static class ResultArrays {
        int[] max;
        int[] min;
        double[] mean;
        int[] last;

        public ResultArrays() {
            this.max = new int[0];
            this.min = new int[0];
            this.mean = new double[0];
            this.last = new int[0];
        }

        public ResultArrays(int[] max, int[] min, double[] mean, int[] last) {
            this.max = max;
            this.min = min;
            this.mean = mean;
            this.last = last;
        }
    }
}

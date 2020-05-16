public class LargestSubarraySum {
    public int largestSum(int[] array) {
        int prevSum = array[0];
        int globalMax = array[0];
        for (int i = 1; i < array.length; ++i) {
            // Discard prevSum and start over again if prevSum becomes negative
            prevSum = Math.max(prevSum + array[i], array[i]);
            globalMax = Math.max(prevSum, globalMax);
        }
        return globalMax;
    }

    // Follow up, what if we want to know the start and end index of the sub-array with largest sum.
    public int[] largestSum2(int[] array) {
        int prevSum = array[0];
        int globalMax = array[0];
        // curLeft and curRight represents the start and end index of the largest sub-array sum we have so far.
        int curLeft = 0, curRight = 0;
        for (int i = 1; i < array.length; ++i) {
            if (prevSum > 0) {
                prevSum += array[i];
                if (prevSum > globalMax) {
                    globalMax = prevSum;
                    // We inherited prevSum, so we only have to move the right border.
                    curRight = i;
                }
            } else {
                prevSum = array[i];
                if (prevSum > globalMax) {
                    globalMax = prevSum;
                    // We discarded prevSum, so we have to reset left and right border.
                    curLeft = i;
                    curRight = i;
                }
            }
        }
        return new int[] {globalMax, curLeft, curRight};
    }
}

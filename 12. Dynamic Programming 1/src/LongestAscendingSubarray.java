public class LongestAscendingSubarray {
    public int longest(int[] array) {
        int maxLen = 0;
        int curLen = 0;
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > prev) {
                curLen++;
                maxLen = Math.max(curLen, maxLen);
            } else {
                curLen = 1;
            }
            prev = array[i];
        }
        return maxLen;
    }
}

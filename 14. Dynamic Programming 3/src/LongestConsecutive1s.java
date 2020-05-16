public class LongestConsecutive1s {
    public int longest(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int curLen = 0;
        int maxLen = 0;
        for (int value : array) {
            if (value == 1) {
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            } else {
                curLen = 0;
            }
        }
        return maxLen;
    }
}

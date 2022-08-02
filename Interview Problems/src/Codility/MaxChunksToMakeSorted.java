package Codility;

// LeetCode 769
public class MaxChunksToMakeSorted {
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        int curMax = 0;
        for (int i = 0; i < arr.length; ++i) {
            curMax = Math.max(curMax, arr[i]);
            if (curMax == i) {
                res++;
            }
        }
        return res;
    }
}

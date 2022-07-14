// LeetCode 1423
public class MaximumPointsYouCanObtainFromCards {
    // TC: O(2k) = O(k)
    // SC: O(1)
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int prefixSumFront = 0;
        for (int i = 0; i < k; ++i) {
            prefixSumFront += cardPoints[i];
        }
        // This is when we take all k cards from beginning
        int max = prefixSumFront;
        int prefixSumBack = 0;
        // If we take i from front, then we will take k - i from back
        for (int i = k - 1; i >= 0; --i) {
            prefixSumBack += cardPoints[n - (k - i)];
            prefixSumFront -= cardPoints[i];
            max = Math.max(max, prefixSumBack + prefixSumFront);
        }
        return max;
    }
}

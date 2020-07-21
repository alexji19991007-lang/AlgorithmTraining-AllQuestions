import java.util.HashMap;
import java.util.Map;

// LeetCode 560
public class F004_SubarraySumEqualsK {
    // k = 7
    // index   0   1   2   3    4   5   6   7    8
    // nums    3   4   7   2   -3   1   4   2   -6
    // preSum  3   7   14  16  13   14  18  20  14

    // TC: O(n)
    // SC: O(n), hash map of size n in the worst case
    public static int subarraySum(int[] nums, int k) {
        int count = 0, preSum = 0;
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1);
        for (int i = 0; i < nums.length; ++i) {
            // calculate prefix sum
            preSum += nums[i];
            // If we have met this (preSum - k) before, then it means from the last occurrence of
            // this (preSum - k) to this occurrence, the sum of the subarrary equals k.
            if (sumCount.containsKey(preSum - k)) {
                // 0 ... a ... b ... c
                // preSum(0 to b) = x, preSum(0 to c) = y
                // y - x = k --> preSum(b + 1 to v) = k --> we get one of the desired subarrays
                // Suppose sumCount[i] = 2, since preSum(0 to a) = x as well
                // Then preSum(a + 1 to c) = k as well --> another desired subarray
                // Therefore, upon discover c, we need to add 2 (i.e. sumCount[preSum - k]) to our final count.
                count += sumCount.get(preSum - k);
            }
            // Or simply: count += map.getOrDefault(sum - k, 0);
            sumCount.put(preSum, sumCount.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}

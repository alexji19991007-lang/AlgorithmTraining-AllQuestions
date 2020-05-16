import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            // calculate the running sum of the array
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            // Suppose the mod value at index i is x.
            // If we encounter the same mode value at index j. Then the subarray from index i + 1
            // to j is a multiple of k
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }
}

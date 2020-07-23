import java.util.HashMap;
import java.util.Map;

public class F015_ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> modValue = new HashMap<>();
        modValue.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            int mod = k == 0 ? sum : sum % k;
            // Suppose the mod value at index i is x.
            // If we encounter the same mode value at index j. Then the subarray from index i + 1
            // to j is a multiple of k
            if (!modValue.containsKey(mod)) {
                modValue.put(mod, i);
            } else {
                if (i - modValue.get(mod) > 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

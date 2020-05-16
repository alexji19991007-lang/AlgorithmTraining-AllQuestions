import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = {3, 4, 7, 2, -3, 1, 4, 2, -6, 0};
        System.out.println(subarraySum(nums, 7));
        System.out.println(subarraySumPrintAll(nums, 7).toString());
    }

    // 0   1   2   3    4   5   6   7    8
    // 3   4   7   2   -3   1   4   2   -6
    // 3   7   14  16  13   14  18  20  14     --prefix sum

    public static int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1);
        for (int i = 0; i < nums.length; ++i) {
            // calculate prefix sum
            sum += nums[i];
            // If we have met this (sum - k) before, then it means from the last occurrence of
            // this (sum - k) to this occurrence, the sum of the subarrary equals k.
            if (sumCount.containsKey(sum - k)) {
                // We have to increment the count by sumCount.get(sum - k) because if we already
                // have two ways to reach (sum -k), then we will have two numbers from which to the
                // current number have a subarray sum equal to k
                count += sumCount.get(sum - k);
            }
            // Or simply: count += map.getOrDefault(sum - k, 0);
            sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static List<List<Integer>> subarraySumPrintAll(int[] nums, int k) {
        int sum = 0;
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> start = new ArrayList<>();
        start.add(-1);
        map.put(0, start);
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                List<Integer> indexList = map.get(sum - k);
                for (int x : indexList) {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(x + 1);
                    ans.add(i);
                    res.add(ans);
                }
            }
            List<Integer> newIndexList = map.getOrDefault(sum, new ArrayList<>());
            newIndexList.add(i);
            map.put(sum, newIndexList);
        }
        return res;
    }
}

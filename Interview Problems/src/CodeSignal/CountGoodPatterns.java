package CodeSignal;

import java.util.HashMap;
import java.util.Map;

public class CountGoodPatterns {
    public static void main(String[] args) {
        CountGoodPatterns test = new CountGoodPatterns();

        int[] nums = {1, 2, 1, 2, 3};

        System.out.println(test.goodPattern(nums, 3, 2));
    }

    public int goodPattern(int[] nums, int n, int k) {
        int slow = 0;
        int fast = 0;
        int res = 0;

        Map<Integer, Integer> map = new HashMap<>();

        while (fast < nums.length) {
            map.put(nums[fast], map.getOrDefault(nums[fast], 0) + 1);

            // shrink window if size > n
            if (fast - slow + 1 > n) {
                int prevCount = map.get(nums[slow]);

                if (prevCount == 1) {
                    map.remove(nums[slow]);
                } else {
                    map.put(nums[slow], prevCount - 1);
                }

                slow++;
            }

            // check valid window
            if (fast - slow + 1 == n) {
                if (map.size() == k) {
                    res++;
                }
            }

            fast++;
        }

        return res;
    }
}
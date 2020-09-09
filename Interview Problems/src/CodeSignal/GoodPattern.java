package CodeSignal;

import java.util.HashMap;
import java.util.Map;

public class GoodPattern {
    public static void main(String[] args) {
        GoodPattern test = new GoodPattern();
        int[] nums = {1, 2, 3, 4, 4, 4, 4, 6};
        System.out.println(test.goodPattern(nums));
    }

    public int goodPattern(int[] nums) {
        int slow = 0, fast = 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (fast < nums.length) {
            map.put(nums[fast], map.getOrDefault(nums[fast], 0) + 1);
            if (fast >= 3) {
                int prevCount = map.get(nums[slow]);
                if (prevCount == 1) {
                    map.remove(nums[slow]);
                } else {
                    map.put(nums[slow], map.get(nums[slow]) - 1);
                }
                slow++;
            }
            if (fast - slow + 1 == 3) {
                res += map.size() == 2 ? 1 : 0;
            }
            fast++;
        }
        return res;
    }
}

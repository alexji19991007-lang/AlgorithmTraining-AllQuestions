package CitadelInterview;

import java.util.ArrayList;
import java.util.List;

// LeetCode 442
public class FIndAllDuplicatesInAnArray {
    // Since the number only ranges from 1 to n, where n is the length of array, we know that for an integer x in the
    // array, the index abs(x) - 1 must be a valid index. Therefore, we can use the original array itself to keep track
    // whether the number has occurred before or not:
    // abs(nums[i]) is the original number; the sign of nums[i] shows whether it has occurred (+ means first occur, - means
    // occurred once).
    // 1. Everytime we encounter an integer x, we negate the value at index nums[abs(x) - 1].
    // 2. If the number at index i is negative, the number i + 1 has occurred once in the previous array, so we put it
    //    into our solution.
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            // The reason for abs() here is because this number may have already been negated before.
            if (nums[Math.abs(num) - 1] < 0) {
                result.add(Math.abs(num));
            }
            nums[Math.abs(num) - 1] *= -1;
        }
        return result;
    }
}

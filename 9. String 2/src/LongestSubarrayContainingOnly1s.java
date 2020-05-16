import java.util.*;

public class LongestSubarrayContainingOnly1s {
    public int longestConsecutiveOnes(int[] nums, int k) {
        int slow = 0, fast = 0;
        int count = 0;
        int longest = 0;
        while (fast < nums.length) {
            if (nums[fast] == 1) {
                fast++;
                longest = Math.max(longest, fast - slow);
            } else if (count < k) {
                fast++;
                count++;
                longest = Math.max(longest, fast - slow);
            } else if (nums[slow] == 0) {
                slow++;
                count--;
            } else {
                slow++;
            }
        }
        return longest;
    }

}

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        // Put all the numbers in the array into a hash set to enable O(1) look-up
        Set<Integer> mSet = new HashSet<>();
        for (int i : nums) {
            mSet.add(i);
        }
        for (int i : nums) {
            // We always start counting from the beginning of a streak.
            // For example, if the sequence is 6 5 4 3 2 1
            // We will not go inside the for loop until we hit 1. Once we hit 1 and we discover
            // that it has no preceding elements, we will start counting the length of the streak
            // starting from 1
            if (!mSet.contains(i - 1)) {
                // Found the start of a sequence
                int cur = i;
                // Start counting
                while (mSet.contains(cur + 1)) {
                    cur++;
                }
                // cur -i + 1 is the length of the current streak starting with number i
                res = Math.max(res, cur - i + 1);
            }
        }
        return res;
    }
}

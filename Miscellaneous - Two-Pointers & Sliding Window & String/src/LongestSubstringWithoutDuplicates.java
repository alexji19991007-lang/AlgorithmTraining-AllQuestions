import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutDuplicates {
    public int longest(String input) {
        int n = input.length();
        if (n <= 1) {
            return n;
        }
        int globalMax = 0;
        int curLen = 0;
        // Use hash map to record the count of each unique character
        Set<Character> count = new HashSet<>();
        // the substring from index slow to index fast is our current sliding window with unique characters
        int slow = 0;
        int fast = 0;
        while (fast < n) {
            // if the character is not currently in our hash map
            if (!count.contains(input.charAt(fast))) {
                // valid letter, extend the right border (increment fast)
                count.add(input.charAt(fast));
                fast++;
                curLen++;
                globalMax = Math.max(curLen, globalMax);
            } else {
                // if the character is currently in our hash map
                count.remove(input.charAt(slow));
                slow++;
                curLen--;
            }
        }
        return globalMax;
    }
}

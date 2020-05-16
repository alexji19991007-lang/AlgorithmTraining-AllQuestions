import java.util.*;

public class LongestSubstringWithoutRepeatingChars {
    public int longest(String input) {
        int n = input.length();
        if (n <= 1) {
            return n;
        }
        int globalMax = 0;
        int curLen = 0;
        Set<Character> occur = new HashSet<>();
        int slow = 0, fast = 0;
        while (fast < n) {
            char cur = input.charAt(fast);
            if (!occur.contains(cur)) {
                occur.add(cur);
                curLen++;
                fast++;
                globalMax = Math.max(globalMax, curLen);
            } else {
                occur.remove(input.charAt(slow));
                slow++;
                curLen--;
            }
        }
        return globalMax;
    }

}

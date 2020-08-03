import java.util.HashMap;
import java.util.Map;

// LeetCode 340
public class F029_LongestSubstringWithAtMostKDistinctCharacters {
    // TC: O(n)
    // SC: O(k)
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        int n = s.length();
        Map<Character, Integer> count = new HashMap<>();
        // set up the sliding window
        int start = 0;
        int maxSize = 1;
        count.put(s.charAt(0), 1);
        for (int i = 1; i < n; ++i) {
            char x = s.charAt(i);
            count.put(x, count.getOrDefault(x, 0) + 1);
            while (count.size() > k) {
                int newCount = count.get(s.charAt(start)) - 1;
                if (newCount > 0) {
                    count.put(s.charAt(start), newCount);
                } else {
                    count.remove(s.charAt(start));
                }
                start++;
            }
            // update the maxSize if necessary
            maxSize = Math.max(i - start + 1, maxSize);
        }
        return maxSize;
    }
}

import java.util.HashMap;
import java.util.Map;

public class LongestSubstrWithAtMostKUniqueChars {
    public static void main(String[] args) {
        String s = "e";
        int k = 1;
        System.out.println(lengthOfLongestSubstringKDistinct(s, k));
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        int n = s.length();
        Map<Character, Integer> count = new HashMap<>();
        // set up the sliding window
        int start = 0;
        int maxSize = 1;
        count.put(s.charAt(0), 1);
        // the i is our end pointer, starting from the second character in s
        for (int i = 1; i < n; ++i) {
            char x = s.charAt(i);
            // if the hashmap does not contain the current character, add it to the map
            count.put(x, count.getOrDefault(x, 0) + 1);
            // count.size() means the total number of unique characters we have in our current
            // string. If we have more unique characters than requested, we should move the start
            // pointer until we have exactly k unique characters
            while (count.size() > k) {
                int newCount = count.get(s.charAt(start)) - 1;
                if (newCount > 0) {
                    count.put(s.charAt(start), newCount);
                } else {
                    // if there is no more such character in our current sliding window,
                    // just delete that entry
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

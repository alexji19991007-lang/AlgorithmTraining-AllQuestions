import java.util.*;

public class MinimumWindowSubstring {
    public String minWindow(String source, String target) {
        if (source == null || source.length() == 0 || target == null || target.length() == 0) {
                return "";
        }
        char[] array = source.toCharArray();
        int slow = 0, minStart = 0;
        int minSize = Integer.MAX_VALUE;
        Map<Character, Integer> count = getCount(target);
        int numMatched = 0;
        for (int i = 0; i < array.length; ++i) {
            char right = array[i];
            if (count.containsKey(right)) {
                int remain = count.get(right);
                // If remain == 1, we finished matching a new character for all its occurrences
                if (remain == 1) {
                    numMatched++;
                }
                count.put(right, remain - 1);
            }
            while (numMatched == count.size()) {
                // If we have finished matching all characters, we start moving its left border to
                // reach the shortest possible length
                char left = array[slow];
                if (count.containsKey(left)) {
                    int curCount = count.get(left);
                    if (curCount == 0) {
                        // If curCount of the leftMost character is 0, removing this character from
                        // our sliding window will make one character unmatched, thus we have found
                        // the smallest sliding window so far
                        numMatched--;
                        // update our result
                        int curSize = i - slow + 1;
                        if (curSize < minSize) {
                            minSize = curSize;
                            minStart = slow;
                        }
                    }
                    // update the hash map
                    count.put(left, curCount + 1);
                }
                slow++;
            }
        }
        return minSize < Integer.MAX_VALUE ? new String(array, minStart, minSize) : "";
    }

    public Map<Character, Integer> getCount(String target) {
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < target.length(); ++i) {
            char cur = target.charAt(i);
            count.put(cur, count.getOrDefault(cur, 0) + 1);
        }
        return count;
    }
}

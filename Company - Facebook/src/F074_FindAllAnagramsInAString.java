import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LeetCode 438
public class F074_FindAllAnagramsInAString {
    // TC: O(n)
    // SC: O(n)
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> map = getCharCount(p);
        int numMatched = 0;
        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            if (map.containsKey(cur)) {
                int count = map.get(cur);
                if (count == 1) {
                    numMatched++;
                }
                map.put(cur, count - 1);
            }
            if (i > p.length() - 1) {
                char toRemove = s.charAt(i - p.length());
                if (map.containsKey(toRemove)) {
                    int count = map.get(toRemove);
                    if (count == 0) {
                        numMatched--;
                    }
                    map.put(toRemove, count + 1);
                }
            }
            if (numMatched == map.size()) {
                res.add(i - p.length() + 1);
            }
        }
        return res;
    }

    public Map<Character, Integer> getCharCount(String p) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); ++i) {
            char cur = p.charAt(i);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        return map;
    }
}

import java.util.*;

public class AllAnagrams {
    public List<Integer> allAnagrams(String sh, String lo) {
        List<Integer> res = new ArrayList<>();
        if (sh.length() == 0 || sh.length() > lo.length()) {
            return res;
        }
        Map<Character, Integer> charCount = getCount(sh);
        int matched = 0;
        for (int i = 0; i < lo.length(); ++i) {
            char cur = lo.charAt(i);
            if (charCount.containsKey(cur)) {
                int count = charCount.get(cur);
                if (count == 1) {
                    matched++;
                }
                charCount.put(cur, count - 1);
            }
            if (i > sh.length() - 1) {
                char toRemove = lo.charAt(i - sh.length());
                if (charCount.containsKey(toRemove)) {
                    int count = charCount.get(toRemove);
                    if (count == 0) {
                        matched--;
                    }
                    charCount.put(toRemove, count + 1);
                }
            }
            if (matched == charCount.size()) {
                res.add(i - sh.length() + 1);
            }
        }
        return res;
    }

    public Map<Character, Integer> getCount(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            count.put(cur, count.getOrDefault(cur, 0) + 1);
        }
        return count;
    }

}

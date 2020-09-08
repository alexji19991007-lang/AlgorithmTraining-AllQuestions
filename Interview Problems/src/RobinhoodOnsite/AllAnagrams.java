package RobinhoodOnsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllAnagrams {
    public static void main(String[] args) {
        AllAnagrams test = new AllAnagrams();
        System.out.println(test.allAnagrams("abcbad", "acb"));
    }

    public List<Integer> allAnagrams(String A, String B) {
        List<Integer> res = new ArrayList<>();
        if (B.length() == 0 || B.length() > A.length()) {
            return res;
        }
        Map<Character, Integer> charCount = getCount(B);
        int matched = 0;
        for (int i = 0; i < A.length(); ++i) {
            char cur = A.charAt(i);
            if (charCount.containsKey(cur)) {
                int count = charCount.get(cur);
                if (count == 1) {
                    matched++;
                }
                charCount.put(cur, count - 1);
            }
            if (i > B.length() - 1) {
                char toRemove = A.charAt(i - B.length());
                if (charCount.containsKey(toRemove)) {
                    int count = charCount.get(toRemove);
                    if (count == 0) {
                        matched--;
                    }
                    charCount.put(toRemove, count + 1);
                }
            }
            if (matched == charCount.size()) {
                res.add(i - B.length() + 1);
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

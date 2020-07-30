import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LeetCode 140
public class F064_WordBreak2 {
    // TC:
    // SC:
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<>());
    }

    // backtrack returns an array including all substrings derived from s.
    // We use a hash map here to store different ways of breaking a string s.
    // For example, if we have a string s = "catsand", then its paired value, which is a linked
    // list, will be ["cat sand", "cats and"]
    private List<String> DFS(String s, List<String> wordDict, Map<String, List<String>> map) {
        // If our map already contains 's' as a key, we immediately get its paired linked list. No
        // more DFS is needed.
        if (map.containsKey(s)) {
            return map.get(s);
        }
        // The linked list "res" will contain all possible ways of decoding a given string "s"
        List<String> res = new ArrayList<>();
        // if the current string is empty, add it and immediately return
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        // try each word in the dictionary to see if our current string starts with it
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                // if the current string starts with the current word, we do DFS on the substring
                // with the word removed, i.e. we want to know how the remaining string can be
                // broken into several parts
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                // Now the sublist will contain all possible ways of breaking the remaining string,
                // all we have to do is to add our current word in the front, plus an empty space " "
                for (String sub : sublist) {
                    res.add(word + (sub.length() == 0 ? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}

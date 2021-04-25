import java.util.*;

public class WordBreak2 {
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        wordDict.add("san");
        wordDict.add("ddog");
        wordDict.add("an");
        List<String> ans = wordBreak(s, wordDict);
        System.out.println(ans.toString());
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<>());
    }

    // backtrack returns an array including all substrings derived from s.
    // We use a hash map here to store different ways of breaking a string s.
    // For example, if we have a string s = "catsand", then its paired value, which is a linked
    // list, will be ["cat sand", "cats and"]
    private static List<String> DFS(String s, List<String> wordDict, Map<String, List<String>> map) {
        // If our map already contains 's' as a key, we immediately get its paired linked list. No
        // more DFS is needed.
        if (map.containsKey(s)) {
            return map.get(s);
        }
        // The list "res" will contain all possible ways of decoding a given string "s"
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
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}

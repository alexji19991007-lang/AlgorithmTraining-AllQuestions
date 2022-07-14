package HackerRank;

import java.util.*;

public class HowManySentences {
    public static void main(String[] args) {
        HowManySentences test = new HowManySentences();
        String[] words = {"bats","tabs","in","cat","act"};
        String[] sentences = {"cat the bats", "in the act", "act tabs in"};
        System.out.println(Arrays.toString(test.howManySentences(words, sentences)));
    }

    public int[] howManySentences(String[] wordSet, String[] sentences) {
        int[] count = new int[sentences.length];
        Map<String, List<String>> anagramGroups = groupAnagrams(wordSet);
        int i = 0;
        for (String s : sentences) {
            int c = 1;
            String[] individualWords = s.split(" ");
            for (String word : individualWords) {
                List<String> anagramsList = anagramGroups.getOrDefault(word, null);
                c *= anagramsList != null ? anagramsList.size() : 1;
            }
            count[i++] = c;
        }
        return count;
    }

    public Map<String, List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new HashMap<>();
        }
        Map<String, List<String>> groupedAnagram = new HashMap<>();
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            // initialize a new count array for each string
            int[] count = new int[26];
            // go through all the characters in the current string, update the occurrence of each character
            for (char c : s.toCharArray()) {
                count[c - 'a'] += 1;
            }
            // change the count array into a string, with a hashtag before each number
            StringBuilder sb = new StringBuilder();
            for (int i : count) {
                sb.append('#');
                sb.append(i);
            }
            String key = sb.toString();
            // check if the string is already in the hashmap
            // if so, we add our current word string into the corresponding key-value pair
            // if not, create a new key-value pair
            groupedAnagram.putIfAbsent(key, new ArrayList<>());
            groupedAnagram.get(key).add(s);
            res.put(s, groupedAnagram.get(key));
        }
        return res;
    }
}

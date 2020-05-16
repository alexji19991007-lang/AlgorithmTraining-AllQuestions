import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> sol = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            // initialize a new count array for each string
            Arrays.fill(count, 0);
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
            if (!sol.containsKey(key)) {
                sol.put(key, new ArrayList<>());
            }
            sol.get(key).add(s);
        }
        return new ArrayList<>(sol.values());
    }
}

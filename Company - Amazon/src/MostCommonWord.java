import java.util.*;

// LeetCode 819
public class MostCommonWord {
    // TC: O(n)
    // SC: O(n)
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> toBan = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> count = new HashMap<>();
        String[] words = paragraph.toLowerCase().split("\\W+");
        for(String w : words) {
            if (!toBan.contains(w)) {
                count.put(w, count.getOrDefault(w, 0) + 1);
            }
        }
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}

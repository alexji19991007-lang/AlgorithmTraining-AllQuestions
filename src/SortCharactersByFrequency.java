import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LeetCode 451
public class SortCharactersByFrequency {
    // TC: O(n);
    // SC: O(n);
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Map<Character, Integer> frequencyMap = new HashMap<>();
        int maxFreq = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            int newFreq = frequencyMap.getOrDefault(c, 0) + 1;
            maxFreq = Math.max(maxFreq, newFreq);
            frequencyMap.put(c, newFreq);
        }
        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i <= maxFreq; ++i) {
            buckets.add(new ArrayList<>());
        }
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }
        StringBuilder res = new StringBuilder();
        for (int i = buckets.size() - 1; i > 0; --i) {
            for (char c : buckets.get(i)) {
                for (int j = 0; j < i; ++j) {
                    res.append(c);
                }
            }
        }
        return res.toString();
    }
}

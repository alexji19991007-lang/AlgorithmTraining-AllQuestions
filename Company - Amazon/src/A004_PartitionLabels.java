import java.util.ArrayList;
import java.util.List;

// LeetCode 763
public class A004_PartitionLabels {
    // TC: O(n)
    // SC: O(1)
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        // Find the indices for the last occurrence of each letter
        for (int i = 0; i < S.length(); ++i) {
            last[S.charAt(i) - 'a'] = i;
        }
        int j = 0, start = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < S.length(); ++i) {
            // Check if the current letter is at its last occurrence
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                // If so, we can make a new piece, calculate the length
                res.add(i - start + 1);
                start = i + 1;
            }
        }
        return res;
    }
}

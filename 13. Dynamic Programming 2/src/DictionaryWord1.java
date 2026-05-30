import java.util.*;

// 1. What does dp[i] mean?
// dp[i] (M[i] in code) represents whether the substring input[0...i-1]
// can be segmented into a sequence of one or more dictionary words.
// If dp[i] = true, it means input[0...i-1] can be formed by concatenating words from the dictionary.

// 2. What is the base case?
// dp[0] = true
// An empty string can always be segmented (no words needed).

// 3. What is the recurrence relation?
// For each index i (1 <= i <= input.length()):
// We try all possible split points j where 0 <= j < i:
//
// If dp[j] == true (left part can be segmented)
// AND input.substring(j, i) is in the dictionary (right part is a valid word),
// then dp[i] = true
//
// dp[i] = OR over all j in [0, i):
//          (dp[j] && dict.contains(input.substring(j, i)))
//
// If no such j exists, then dp[i] = false
public class DictionaryWord1 {
    public boolean canBreak(String input, String[] dict) {
        Set<String> wordDict = new HashSet<>(Arrays.asList(dict));
        // We need one extra index for "", which is true
        boolean[] M = new boolean[input.length() + 1];
        M[0] = true;
        for (int i = 1; i <= input.length(); ++i) {
            for (int j = 0; j <= i; ++j) {
                // 左大段：左边的能不能被break？ --> 查表
                // 右小段：右边能不能被break？ --> 看字典
                if (M[j] && wordDict.contains(input.substring(j, i))) {
                    M[i] = true;
                    break;
                }
            }
        }
        return M[M.length - 1];
    }

    // O(n^3)
}

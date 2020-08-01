import java.util.HashSet;
import java.util.List;
import java.util.Set;

// LeetCode 139
public class F069_WordBreak {
    // TC: O(n ^ 3)
    // SC: O(n)
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] M = new boolean[s.length() + 1];
        M[0] = true;
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 0; j <= i; ++j) {
                if (M[j] && dict.contains(s.substring(j, i))) {
                    M[i] = true;
                    break;
                }
            }
        }
        return M[s.length()];
    }
}

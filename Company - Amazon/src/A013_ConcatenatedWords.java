import java.util.*;

// LeetCode 472
public class A013_ConcatenatedWords {
    // TC: O(klogk + k * n^3), where k is the length of the words array and n is the length of the
    //     longest word
    // SC: O(k + n)
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> preWords = new HashSet<>();
        Arrays.sort(words, (s1, s2) -> {
            if (s1.length() == s2.length()) {
                return 0;
            }
            return s1.length() < s2.length() ? -1 : 1;
        });
        for (String word : words) {
            if (canBreak(word, preWords)) {
                res.add(word);
            }
            preWords.add(word);
        }
        return res;
    }

    public boolean canBreak(String input, Set<String> wordDict) {
        if (wordDict.size() == 0) {
            return false;
        }
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
}

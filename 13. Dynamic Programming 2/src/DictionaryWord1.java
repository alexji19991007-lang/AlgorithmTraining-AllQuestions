import java.util.*;

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

import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        WordBreak test = new WordBreak();
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        wordDict.add("san");
        wordDict.add("ddog");
//        wordDict.add("an");
        System.out.println(test.wordBreakWays(s, wordDict));
    }

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

    // What if we want to know how many ways are there to break?
    public int wordBreakWays(String input, List<String> dict) {
        Set<String> wordDict = new HashSet<>(dict);
        int[] M = new int[input.length() + 1];
        M[0] = 1;
        for (int i = 1; i <= input.length(); ++i) {
            for (int j = 0; j <= i; ++j) {
                if (M[j] > 0 && wordDict.contains(input.substring(j, i))) {
                    M[i] += M[j];
                }
            }
        }
        return M[M.length - 1];
    }
}

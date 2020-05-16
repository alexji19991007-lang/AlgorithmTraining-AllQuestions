import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static boolean[][] dp;

    public static void main(String[] args) {
        String s = "abccbac";
        System.out.println(partition(s).toString());
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j <= i; ++j) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(res, new ArrayList<>(), s, 0);
        return res;
    }

    public static void helper(List<List<String>> res, List<String> path, String s, int pos) {
        if (pos == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = pos; i < s.length(); ++i) {
            if (dp[pos][i]) {
                path.add(s.substring(pos, i + 1));
                helper(res, path, s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}

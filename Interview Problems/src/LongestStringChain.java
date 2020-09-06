import java.util.Arrays;

public class LongestStringChain {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (w1, w2) -> {
            if (w1.length() == w2.length()){
                return 0;
            }
            return w1.length() < w2.length() ? -1 : 1;
        });
        int[] dp = new int[words.length];
        int res = 0;
        for (int i = 0 ; i < words.length; ++i) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0 && words[i].length() - words[j].length() <= 1; --j) {
                if (words[i].length() == words[j].length()) {
                    continue;
                }
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public boolean isPredecessor(String shorter, String longer) {
        int diff = 0;
        int i = 0, j = 0;
        while (i < shorter.length()) {
            if (shorter.charAt(i) == longer.charAt(j)) {
                i++;
            } else {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
            j++;
        }
        return true;
    }
}

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        String[] words = new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};
        System.out.println(longestStrChain(words));
    }

    public static int longestStrChain(String[] words) {
        Arrays.sort(words, new StringByLengthComparator());
        int[] dp = new int[words.length];
        int maxLen = 0;
        for (int i = 0; i < words.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0 && words[i].length() - words[j].length() <= 1; j--) {
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    private static boolean isPredecessor(String s1, String s2) {
        if (s2.length() == s1.length()) return false;
        int diff = 0;
        for (int i = 0, j = 0; i < s1.length(); ) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                diff++;
                if (diff > 1) return false;
                j++;
            }
        }
        return true;
    }

    private static class StringByLengthComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return Integer.compare(s1.length(), s2.length());
        }
    }

}

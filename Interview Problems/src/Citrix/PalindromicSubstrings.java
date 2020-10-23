package Citrix;

import java.util.HashSet;
import java.util.Set;

// LeetCode 647
public class PalindromicSubstrings {
    public static void main(String[] args) {
        PalindromicSubstrings test = new PalindromicSubstrings();
        String s = "aaaaaa";
        System.out.println(test.countSubstrings(s));
        System.out.println(test.countDistinctSubstrings(s));
    }

    // TC: O(n^2)
    // SC: O(n^2)
    public int countSubstrings(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int count = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int len = 1; len <= n; ++len) {
            for (int start = 0; start + len <= n; ++start) {
                int i = start, j = start + len - 1;
                if (len == 1) {
                    dp[i][j] = true;
                    count++;
                } else if (len == 2) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        count++;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int countDistinctSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        Set<String> palindrome = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            count += extendPalindrome(s, i, i, palindrome); // odd length
            count += extendPalindrome(s, i, i + 1, palindrome); // even length
        }
        return count;
    }

    public int extendPalindrome(String s, int left, int right, Set<String> palindrome) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            String pal = s.substring(left, right + 1);
            if (!palindrome.contains(pal)) {
                count++;
                palindrome.add(pal);
            }
            left--;
            right++;
        }
        return count;
    }
}

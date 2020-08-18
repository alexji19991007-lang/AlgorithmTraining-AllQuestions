package Karat;

import java.util.Arrays;

public class LongestCommonChain {
    public static void main(String[] args) {
        LongestCommonChain test = new LongestCommonChain();
        String[] user0 = {"abc", "ddd", "xyz", "eee"};
        String[] user1 = {"abc", "xyz", "eee"};
        System.out.println(Arrays.toString(test.longestChain(user0, user1)));
    }

    public String[] longestChain(String[] user0, String[] user1) {
        int len = 0, endingIndex = 0;
        int[][] memo = new int[user0.length + 1][user1.length + 1];
        for (int i = 0; i < user0.length; ++i) {
            for (int j = 0; j < user1.length; ++j) {
                memo[i + 1][j + 1] = user0[i].equals(user1[j]) ? memo[i][j] + 1 : 0;
                if (len < memo[i + 1][j + 1]) {
                    len = memo[i + 1][j + 1];
                    endingIndex = j;
                }
            }
        }
        int startingIndex = endingIndex - len + 1;
        String[] res = new String[len];
        for (int i = 0; i < len; ++i) {
            res[i] = user1[startingIndex + i];
        }
        return res;
    }
}

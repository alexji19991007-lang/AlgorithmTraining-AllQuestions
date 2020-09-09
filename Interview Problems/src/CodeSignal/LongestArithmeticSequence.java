package CodeSignal;

import java.util.HashMap;

public class LongestArithmeticSequence {
    public static void main(String[] args) {
        LongestArithmeticSequence test = new LongestArithmeticSequence();
        int[] A = {1, 5, 9, 13};
        int[] B = {-3, -1, 3, 7, 11, 15, 19};
        System.out.println(test.longestArithmeticSequence(A, B));
    }

    public int longestArithmeticSequence(int[] A, int[] B) {
        return 0;
    }

    public int longestInOneArray(int[] A) {
        int res = 2, n = A.length;
        // dp[i].get[d] = the length of the arithmetic sequence ending at index i with a common
        //                difference d
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < A.length; ++i) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; ++j) {
                int d = A[i] - A[j];
                dp[i].put(d, dp[j].getOrDefault(d, 1) + 1);
                res = Math.max(res, dp[i].get(d));
            }
        }
        return res;
    }

    public int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    public int gcdArray(int[] array) {
        int res = array[0];
        for (int i = 1; i < array.length; ++i) {
            res = gcd(array[i], res);
            if (res == 1) {
                return 1;
            }
        }
        return res;
    }
}

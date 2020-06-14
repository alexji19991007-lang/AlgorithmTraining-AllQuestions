//    dp[0] = 0
//            dp[1] = dp[0]+1 = 1
//            dp[2] = dp[1]+1 = 2
//            dp[3] = dp[2]+1 = 3
//            dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 }
//            = Min{ dp[3]+1, dp[0]+1 }
//            = 1
//            dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 }
//            = Min{ dp[4]+1, dp[1]+1 }
//            = 2
//            .
//            .
//            .
//            dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 }
//            = Min{ dp[12]+1, dp[9]+1, dp[4]+1 }
//            = 2
//            .
//            .
//            .
//            dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1

public class PerfectSquares {
    public static void main(String[] args) {
        System.out.println(numSquares(10));
    }

    // Base case: n = k * k, which means n is a perfect square number. numSquares(k) = 1.
    // Induction rule: numSquares(n) = min(numSquares(n - m) + 1), where m is a perfect square number
    // Analysis: Here, m is our right small chunk, which is a perfect square number by itself, so + 1.
    //           (n - m) is our left big chunk, which needs to be read from the table. All we have to
    //           do is just to adjust m such that numSquares(n - m), i.e. the value read from the table
    //           is the smallest possible one.
    public static int numSquares(int n) {
        int[] cntPerfectSquares = new int[n + 1];
        cntPerfectSquares[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            //左大段右小段；左大段通过查表(cnt[i - j * j])，右小段通过平方(j*j)。
            while (i - j * j >= 0) {
                min = Math.min(min, cntPerfectSquares[i - j * j] + 1);
                ++j;
            }
            cntPerfectSquares[i] = min;
        }
        return cntPerfectSquares[n];
    }
}

// LeetCode 29
public class F065_DivideTwoIntegers {
    // Example 1:
    // dividend = 21, divisor = 3
    // 21 = 3 * (2^2) + 3 * (2^1) + 3 * (2^0)
    //      1           1           1        --> 111 --> 7

    // Example 2:
    // dividend = 18, divisor = 3
    // 18 = 3 * (2^2) + 3 * (2^1) + (0 * 3) * (2^0)
    //      1           1              0     --> 110 --> 6

    // TC: O(logn)
    // SC: O(1)
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        // Change dividend & divisor to positive longs (to avoid overflow during shift of d)
        long c = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        int res = 0;
        while (c >= d) {
            int shift = 0;
            // d << n = d * (2^n).
            // Keep multiplying d by 2 until d is greater than c
            while (c >= (d << shift)) {
                shift++;
            }
            // (d << shift) > c, so (d << (shift - 1)) <= c
            c -= d << (shift - 1);
            res += 1 << (shift - 1);
        }
        return isNegative ? -res : res;
    }
}

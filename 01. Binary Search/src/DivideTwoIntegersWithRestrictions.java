public class DivideTwoIntegersWithRestrictions {
    // Example 1:
    // a = 21, b = 3
    // 21 = 3 * (2^2) + 3 * (2^1) + 3 * (2^0)
    //      1           1           1        --> 111 --> 7

    // Example 2:
    // a = 18, b = 3
    // 18 = 3 * (2^2) + 3 * (2^1) + (0 * 3) * (2^0)
    //      1           1              0     --> 110 --> 6
    public int divide(int a, int b) {
        if (b == 0) return Integer.MAX_VALUE;
        if (a == 0) return 0;
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        boolean isNegative = (a < 0 && b > 0) || (a > 0 && b < 0);
        // Change a & b to positive longs (to avoid overflow during shift of d)
        long c = Math.abs((long) a);
        long d = Math.abs((long) b);
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

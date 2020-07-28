// LeetCode 50
public class F050_PowXN {
    // Method 1: Recursion
    // TC: O(logn)
    // SC: O(logn)
    public double myPow(double x, int n) {
        if (n == 0) return 1; //任何数的0次方都是 1
        if (n == 1) return x; //任何数的1次方都是自己
        if (x == 1) return 1;
        if (x == -1 && n % 2 == 0) {
            return 1;
        } else if (x == -1 && n % 2 != 0) {
            return -1;
        }
        if (n == Integer.MAX_VALUE || n == Integer.MIN_VALUE) return 0;
        if (x == 0) {
            if (n < 0) {
                return Double.POSITIVE_INFINITY; //0的负数次方都是 无穷大
            } else {
                return 0; //0的正数次方都是0
            }
        }
        double half = myPow(x, Math.abs(n) >> 1);
        // 这里判断如何当前是奇数次数，还需要再乘以一个自己
        double ret = ((n & 1) == 1 ? x : 1.0) * half * half;
        // 如果这个整数是负数，取倒数
        if (n < 0) {
            ret = 1 / ret;
        }
        return ret;
    }

    // Method 2: Iterative
    // TC: O(logn)
    // SC: O(1)
    public double myPow_Recur(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }
}

public class ClimbStairs {
    public int stairs(int n) {
        if (n == 0) {
            return 1;
        }
        int[] M = new int[n + 1];
        M[0] = M[1] = 1;
        for (int i = 2; i <= n; ++i) {
            M[i] = M[i - 1] + M[i - 2];
        }
        return M[n];
    }
}

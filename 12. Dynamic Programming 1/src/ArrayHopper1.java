public class ArrayHopper1 {
    // 1. What does dp[i] mean?
    // dp[i] = Whether we can reach the last index starting from index i
    // (true means reachable, false means not reachable)
    // 2. What is the base case?
    // dp[n - 1] = true
    // Because if we are already at the last index, we have reached the goal


    // 3. What is the recurrence relations?
    // When we are at index i, we can jump to any position:
    //    i + array[i] <= j <= i + array[i]
    //
    // So we check all possible next positions j:
    //
    //    3.1 If there exists a j such that dp[j] == true,
    //        then dp[i] = true (we can reach the end through j)
    //
    //    3.2 If none of them can reach the end,
    //        then dp[i] = false
    //
    // So:
    //
    // dp[i] = OR over all j in [i, min(i + array[i], n - 1)] of dp[j]
    //
    // i.e.
    //
    // dp[i] = true,  if there exists j such that:
    //                  i <= j <= i + array[i] AND dp[j] == true
    //         false, otherwise
    public boolean canJump(int[] array) {
        // int lastPos = array.length - 1;
        // [1, 3, 2, 0, 3]
        // 假设我们现在要跳到lastPos，只要前面任意一格可以跳到lastPos，我们就把lastPos设置成那一格。
        // 这样下来，我们只要保证更前面的格子可以跳到新的lastPos就行了，一直以此类推往前推。
        // 最终我们要保证我们最后的lastPos是index 0。
        // for (int i = array.length - 2; i >= 0; --i) {
        //   if (i + array[i] >= lastPos) {
        //     lastPos = i;
        //   }
        // }
        // return lastPos == 0;

        // DP方法: 从后往前走
        int n = array.length;
        boolean[] jump = new boolean[n];
        jump[n - 1] = true;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i; j <= Math.min(array[i] + i, n - 1); ++j) {
                if (jump[j]) {
                    jump[i] = true;
                    break;
                }
            }
        }
        return jump[0];
    }
}

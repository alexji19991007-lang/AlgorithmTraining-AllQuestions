// 1. What does dp[i] mean?
// dp[i] represents the minimum number of jumps needed to reach the last index
// starting from index i.
// If dp[i] = -1, it means index i cannot reach the end.

// 2. What is the base case?
// dp[n - 1] = 0
// At the last index, no jumps are needed to reach the end.

// 3. What is the recurrence relation?
// For each index i, we can jump to any index j where:
// i < j <= i + array[i]
//
// dp[i] = min(dp[j] + 1) for all valid j where dp[j] != -1
//
// If none of those j can reach the end (all dp[j] == -1),
// then dp[i] = -1
public class ArrayHopper2 {
    public int minJump2(int[] array) {
        int n = array.length;
        int[] jump = new int[n];
        jump[n - 1] = 0;
        for (int i = n - 2; i >= 0; --i) {
            jump[i] = -1;
            for (int j = i + 1; j <= Math.min(array[i] + i, n - 1); ++j) {
                if (array[i] + i >= j && jump[j] != -1 && (jump[i] == -1 || jump[i] > 1 + jump[j])) {
                    jump[i] = jump[j] + 1;
                }
            }
        }
        return jump[0];
    }
}

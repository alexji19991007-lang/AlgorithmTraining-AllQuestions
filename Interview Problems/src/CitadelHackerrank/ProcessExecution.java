package CitadelHackerrank;

import java.util.HashMap;
import java.util.Map;

public class ProcessExecution {
    public static void main(String[] args) {
        ProcessExecution test = new ProcessExecution();
        System.out.println(test.processExecution(7, new int[] {3, 3, 3, 4, 4, 1, 8}));
    }

    public int processExecution(int n, int[] power) {
        Map<Integer, Integer> powerToSum = new HashMap<>();
        int maxPower = -1;
        for (int p : power) {
            maxPower = Math.max(maxPower, p);
            powerToSum.put(p, powerToSum.getOrDefault(p, 0) + p);
        }
        int[] dp = new int[maxPower + 1];
        dp[0] = 0;
        dp[1] = powerToSum.getOrDefault(1, 0);
        for (int i = 2; i < dp.length; ++i) {
            if (!powerToSum.containsKey(i)) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.max(powerToSum.get(i) + dp[i - 2], dp[i - 1]);
            }
        }
        return dp[dp.length - 1];
    }
}

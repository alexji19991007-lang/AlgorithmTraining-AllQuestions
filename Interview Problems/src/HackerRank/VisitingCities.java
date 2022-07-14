package HackerRank;

import java.util.ArrayList;
import java.util.List;

public class VisitingCities {
    public List<Long> minimumCost(List<Integer> red, List<Integer> blue, int blueCost) {
        int n = red.size();
        List<Long> minCost = new ArrayList<>();
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = blueCost;
        for (int i = 1; i <= n; ++i) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + red.get(i - 1);
            dp[i][1] = Math.min(dp[i - 1][0] + blueCost + blue.get(i - 1), dp[i - 1][1] + blue.get(i - 1));
        }
        for (int i = 0; i <= n; ++i) {
            minCost.add((long)Math.min(dp[i][0], dp[i][1]));
        }
        return minCost;
    }
}

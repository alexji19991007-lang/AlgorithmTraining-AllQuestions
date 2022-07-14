package HackerRank;

import java.util.List;

public class PortfolioBalances {
    public long maxValue(int n, List<List<Integer>> rounds) {
        long[] investments = new long[n];
        for (List<Integer> round : rounds) {
            investments[round.get(0) - 1] += round.get(2);
            if (round.get(1) < n) {
                investments[round.get(1)] -= round.get(2);
            }
        }
        long res = investments[0];
        for (int i = 1; i < n; ++i) {
            investments[i] += investments[i - 1];
            res = Math.max(res, investments[i]);
        }
        return res;
    }
}

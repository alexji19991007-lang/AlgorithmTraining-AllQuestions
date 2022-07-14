package HackerRank;

import java.util.HashSet;
import java.util.Set;

public class ProfitTargets {
    public int stocksProfit(int[] stocksProfit, int target) {
        Set<Integer> profitSet = new HashSet<>();
        int res = 0;
        for (int profit : stocksProfit) {
            if (!profitSet.contains(profit) && profitSet.contains(target - profit)) {
                res++;
            }
            profitSet.add(profit);
        }
        return res;
    }
}

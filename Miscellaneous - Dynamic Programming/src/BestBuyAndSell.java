import java.util.HashMap;
import java.util.Map;

public class BestBuyAndSell {
    public static void main(String[] args) {
        int[] prices = {7, 3, 8, 1, 2, 7, 9};
        int[] res = new int[2];
        int max = maxProfit(prices, res);
        System.out.println(max + " " + res[0] + " " + res[1]);
    }

    public static int maxProfitBetter(int[] array) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int curPrice : array) {
            if (curPrice < minPrice) {
                minPrice = curPrice;
            } else if (curPrice - minPrice > maxProfit) {
                maxProfit = curPrice - minPrice;
            }
        }
        return maxProfit;
    }

    public static int maxProfit(int[] prices, int[] res) {
        // We use a hashmap to check for the starting position, i.e. the best buying date
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < prices.length; ++i) {
            if (!indexMap.containsKey(prices[i])) {
                indexMap.put(prices[i], i);
            }
        }
        int curMax = 0;
        int globalMax = 0;
        int start = 0;
        int end = 1;
        for (int i = 1; i < prices.length; ++i) {
            int curProfit = curMax + prices[i] - prices[i - 1];
            // if curProfit is negative, restore curMax to 0, otherwise update curMax
            curMax = Math.max(curProfit, 0);
            // if curMax is greater than globalMax, then update globalMax, and we should also
            // update start and end position
            if (globalMax < curMax) {
                globalMax = curMax;
                end = i;
                // it does not matter if we have several same start prices before because any one of
                // them will give us the same max profit
                start = indexMap.get(prices[i] - globalMax);
            }
        }
        res[0] = start;
        res[1] = end;
        return globalMax;
    }
}

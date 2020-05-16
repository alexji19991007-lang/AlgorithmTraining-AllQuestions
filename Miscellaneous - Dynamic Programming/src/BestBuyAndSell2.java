public class BestBuyAndSell2 {
    public int maxProfit(int[] prices) {
        // 找到每个相邻的valley peak， 相减
        if (prices.length == 0) {
            return 0;
        }
        int valley;
        int peak;
        int maxP = 0;
        int i = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxP += peak - valley;
        }
        return maxP;
    }

    public int maxProfitAlternative(int[] prices) {
        // 计算每一笔小交易
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}

package RobinhoodOnsite;

import java.util.Arrays;

public class PortfolioValueOptimization {
    public static void main(String[] args) {
        PortfolioValueOptimization test = new PortfolioValueOptimization();
        String[][] input1 = {
                {"P1=15", "S1=45", "A1=3", "AAPL"}, // 1
                {"P2=40", "S2=50", "A2=3", "BYND"}, // 3
                {"P3=25", "S3=35", "A3=3", "SNAP"}, // 2
                {"P4=30", "S4=25", "A4=4", "TSLA"}  // 4
        };
        String[][] input2 = {
                {"P1=15", "S1=45", "A1=3", "AAPL"}, // 1
                {"P2=50", "S2=65", "A2=3", "BYND"}, // 3
                {"P3=25", "S3=35", "A3=1", "SNAP"}  // 2
        };
        String[][] input3 = {
                {"P1=15", "S1=30", "A1=3", "AAPL"},
                {"P2=20", "S2=45", "A2=3", "TSLA"}
        };
        System.out.println(test.maxGain(30, input3));
        System.out.println(test.maxGain2(30, input3));
    }

    public double maxGain(double money, String[][] input) {
        Stock[] stocks = getStocks(input);
        double finalMoney = 0;
        for (Stock stock : stocks) {
            int priceDiff = stock.expectedPrice - stock.currentPrice;
            if (money == 0 || priceDiff <= 0) {
                break;
            }
            double maxPurchase = Math.min(stock.purchaseLimit, money / stock.currentPrice);
            money -= maxPurchase * stock.currentPrice;
            stock.purchaseLimit -= maxPurchase;
            finalMoney += maxPurchase * stock.expectedPrice;
        }
        return finalMoney + money;
    }

    public int maxGain2(int money, String[][] input) {
        Stock[] stocks = getStocks(input);
        // dp[i][j] means the maximum gain consider only stocks[0 .. i] & with money j.
        // dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - k * stocks[i].currentPrice] + k * stocks[i].expectedPrice)
        // 0 <= k <= stocks[i].purchaseLimit
        int[][] dp = new int[stocks.length + 1][money + 1];
        // One more row to avoid edge cases
        for (int i = 0; i < dp[0].length; ++i) {
            dp[0][i] = i;
        }
        for (int i = 1; i < dp.length; ++i) {
            // Since we have one more row, the induction rule becomes:
            // dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - k * stocks[i - 1].currentPrice] + k * stocks[i - 1].expectedPrice)
            // 0 <= k <= stocks[i - 1].purchaseLimit
            for (int j = 1; j < dp[0].length; ++j) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 0; k <= Math.min(j / stocks[i - 1].currentPrice, stocks[i - 1].purchaseLimit); ++k) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - k * stocks[i - 1].currentPrice] + k * stocks[i - 1].expectedPrice);
                }
            }
        }
        return dp[stocks.length][money];
    }

    public Stock[] getStocks(String[][] input) {
        Stock[] stocks = new Stock[input.length];
        int idx = 0;
        for (String[] stockInfo : input) {
            String name = stockInfo[3];
            int currentPrice = Integer.parseInt(stockInfo[0].split("=")[1]);
            int expectedPrice = Integer.parseInt(stockInfo[1].split("=")[1]);
            int purchaseLimit = Integer.parseInt(stockInfo[2].split("=")[1]);
            stocks[idx++] = new Stock(name, currentPrice, expectedPrice, purchaseLimit);
        }
        Arrays.sort(stocks, (s1, s2) -> {
            if (s1.percentageGain == s2.percentageGain) {
                if (s1.currentPrice == s2.currentPrice) {
                    return 0;
                }
                return s1.currentPrice < s2.currentPrice ? -1 : 1;
            }
            return s1.percentageGain > s2.percentageGain ? -1 : 1;
        });
        return stocks;
    }

    static class Stock {
        String name;
        int currentPrice;
        int expectedPrice;
        int purchaseLimit;
        double percentageGain;

        public Stock(String name, int currentPrice, int expectedPrice, int purchaseLimit) {
            this.name = name;
            this.currentPrice = currentPrice;
            this.expectedPrice = expectedPrice;
            this.purchaseLimit = purchaseLimit;
            this.percentageGain = (expectedPrice - currentPrice) * 1.0 / currentPrice;
        }
    }
}

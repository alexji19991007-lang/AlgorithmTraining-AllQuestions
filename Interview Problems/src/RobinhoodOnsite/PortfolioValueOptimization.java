package RobinhoodOnsite;

import java.util.Arrays;

public class PortfolioValueOptimization {
    public static void main(String[] args) {
        PortfolioValueOptimization test = new PortfolioValueOptimization();
        String[][] input = {
                {"P1=15", "S1=45", "A1=3", "AAPL"}, // 1
                {"P2=40", "S2=55", "A2=3", "BYND"}, // 3
                //{"P3=25", "S3=35", "A3=3", "SNAP"}, // 2
                {"P4=30", "S4=40", "A4=4", "TSLA"}  // 4
        };
        String[][] input2 = {
                {"P1=15", "S1=45", "A1=3", "AAPL"}, // 1
                {"P2=50", "S2=65", "A2=3", "BYND"}, // 2
                {"P3=25", "S3=35", "A3=3", "SNAP"}  // 3
        };
        System.out.println(test.maxGain(100, input));
        System.out.println(test.maxGain2(100, input));
    }

    public double maxGain(double money, String[][] input) {
        Stock[] stocks = getStocks(input);
        double gain = 0;
        for (Stock stock : stocks) {
            int priceDiff = stock.expectedPrice - stock.currentPrice;
            if (money == 0 || priceDiff <= 0) {
                break;
            }
            double maxPurchase = Math.min(stock.purchaseLimit, money / stock.currentPrice);
            money -= maxPurchase * stock.currentPrice;
            stock.purchaseLimit -= maxPurchase;
            gain += maxPurchase * priceDiff;
        }
        return gain;
    }

    public int maxGain2(int money, String[][] input) {
        Stock[] stocks = getStocks(input);
        int gain = 0;
        for (Stock stock : stocks) {
            int priceDiff = stock.expectedPrice - stock.currentPrice;
            if (money == 0 || priceDiff <= 0) {
                break;
            }
            int maxPurchase = Math.min(stock.purchaseLimit, money / stock.currentPrice);
            money -= maxPurchase * stock.currentPrice;
            stock.purchaseLimit -= maxPurchase;
            gain += maxPurchase * priceDiff;
        }
        return gain;
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
//            int diff1 = s1.expectedPrice - s1.currentPrice;
//            int diff2 = s2.expectedPrice - s2.currentPrice;
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

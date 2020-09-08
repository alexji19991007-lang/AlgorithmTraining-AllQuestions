package RobinhoodOnsite;

import java.util.*;

public class StockTransactions {
    public static void main(String[] args) {
        StockTransactions test = new StockTransactions();
        String[][] transactions = {
                {"symbol:FB", "side:buy", "quantity:1", "price:200"},
                {"symbol:AAPL", "side:buy", "quantity:1", "price:100"},
                {"symbol:FB", "side:sell", "quantity:1", "price:150"},
                {"symbol:AAPL", "side:buy", "quantity:1", "price:200"},
                {"symbol:AAPL", "side:buy", "quantity:4", "price:150"},
                {"symbol:AAPL", "side:sell", "quantity:1", "price:210"},
                {"symbol:AAPL", "side:sell", "quantity:4", "price:220"}};
        List<String[]> sellInfos = test.processTransactions(transactions);
        for (String[] sellInfo : sellInfos) {
            System.out.println(Arrays.toString(sellInfo));
        }
        System.out.println();
        List<String[]> sellInfos_pq = test.processTransactions_PriorityQueue(transactions);
        for (String[] sellInfo : sellInfos_pq) {
            System.out.println(Arrays.toString(sellInfo));
        }
    }

    public List<String[]> processTransactions(String[][] transactions) {
        List<String[]> res = new ArrayList<>();
        Map<String, Queue<Stock>> mStock = new HashMap<>();
        for (String[] transaction : transactions) {
            String stockName = transaction[0].split(":")[1];
            String action = transaction[1].split(":")[1];
            int quantity = Integer.parseInt(transaction[2].split(":")[1]);
            int price = Integer.parseInt(transaction[3].split(":")[1]);
            Queue<Stock> stockQueue = mStock.getOrDefault(stockName, new LinkedList<>());
            if (action.equals("buy")) {
                stockQueue.offer(new Stock(stockName, quantity, price));
                mStock.put(stockName, stockQueue);
            } else {
                if (stockQueue.size() == 0) {
                    continue;
                }
                while (!stockQueue.isEmpty() && quantity > 0) {
                    Stock cur = stockQueue.peek();
                    // Only look at the current buy record
                    int soldThisRound = Math.min(cur.quantity, quantity);
                    int gainThisRound = soldThisRound * (price - cur.price);

                    // Update the remaining quantity for the current buy record & the current sell record
                    int curQuantityRemain = Math.max(0, cur.quantity - quantity);
                    quantity = Math.max(0, quantity - cur.quantity);
                    cur.quantity = curQuantityRemain;
                    // If the remaining quantity for the current buy record is 0, pop it from the queue
                    if (cur.quantity <= 0) {
                        stockQueue.poll();
                    }
                    // We will add result if the we have sold some stock successfullt this round
                    if (soldThisRound > 0){
                        res.add(getSellInfo(stockName, soldThisRound, gainThisRound));
                    }
                }
            }
        }
        return res;
    }

    public List<String[]> processTransactions_PriorityQueue(String[][] transactions) {
        List<String[]> res = new ArrayList<>();
        Map<String, Queue<Stock>> mStock = new HashMap<>();
        for (String[] transaction : transactions) {
            String stockName = transaction[0].split(":")[1];
            String action = transaction[1].split(":")[1];
            int quantity = Integer.parseInt(transaction[2].split(":")[1]);
            int price = Integer.parseInt(transaction[3].split(":")[1]);
            Queue<Stock> stockQueue = mStock.getOrDefault(stockName, new PriorityQueue<>((s1, s2) -> {
                if (s1.price == s2.price) {
                    return 0;
                }
                return s1.price < s2.price ? -1 : 1;
            }));
            if (action.equals("buy")) {
                stockQueue.offer(new Stock(stockName, quantity, price));
                mStock.put(stockName, stockQueue);
            } else {
                if (stockQueue.size() == 0) {
                    continue;
                }
                while (!stockQueue.isEmpty() && quantity > 0) {
                    Stock cur = stockQueue.peek();
                    // Only look at the current buy record
                    int soldThisRound = Math.min(cur.quantity, quantity);
                    int gainThisRound = soldThisRound * (price - cur.price);

                    // Update the remaining quantity for the current buy record & the current sell record
                    int curQuantityRemain = Math.max(0, cur.quantity - quantity);
                    quantity = Math.max(0, quantity - cur.quantity);
                    cur.quantity = curQuantityRemain;
                    // If the remaining quantity for the current buy record is 0, pop it from the queue
                    if (cur.quantity <= 0) {
                        stockQueue.poll();
                    }
                    // We will add result if the we have sold some stock successfullt this round
                    if (soldThisRound > 0){
                        res.add(getSellInfo(stockName, soldThisRound, gainThisRound));
                    }
                }
            }
        }
        return res;
    }

    public String[] getSellInfo(String stockName, int sellQuantity, int capitalGain) {
        String[] sellInfo = new String[3];
        sellInfo[0] = "Symbol:" + stockName;
        sellInfo[1] = "Quantity:" + sellQuantity;
        sellInfo[2] = "Capital Gain:" + capitalGain;
        return sellInfo;
    }

    static class Stock {
        String stockName;
        int quantity;
        int price;

        public Stock(String stockName, int quantity, int price) {
            this.stockName = stockName;
            this.quantity = quantity;
            this.price = price;
        }
    }
}

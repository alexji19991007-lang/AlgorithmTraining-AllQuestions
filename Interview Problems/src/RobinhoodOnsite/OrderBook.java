package RobinhoodOnsite;

import java.util.PriorityQueue;
import java.util.Queue;

public class OrderBook {
    public static void main(String[] args) {
        OrderBook test = new OrderBook();
        String[][] orders = {{"150", "10", "buy"}, {"165", "7", "sell"}, {"168", "3", "buy"}, {"155", "5", "sell"}, {"166", "8", "buy"}};
        System.out.println(test.executedShares(orders));
    }

    public int executedShares(String[][] orders) {
        // minHeap w.r.t. sale price
        Queue<Order> sellOrders = new PriorityQueue<>((o1, o2) -> {
            if (o1.price < o2.price) {
                return -1;
            } else if (o1.price > o2.price) {
                return 1;
            } else if (o1.quantity == o2.quantity) {
                return 0;
            } else {
                return o1.quantity > o2.quantity ? -1 : 1;
            }
        });
        // maxHeap w.r.t buy price
        Queue<Order> buyOrders = new PriorityQueue<>((o1, o2) -> {
            if (o1.price > o2.price) {
                return -1;
            } else if (o1.price < o2.price) {
                return 1;
            } else if (o1.quantity == o2.quantity) {
                return 0;
            } else {
                return o1.quantity > o2.quantity ? -1 : 1;
            }
        });
        int res = 0;
        for (String[] order : orders) {
            Order ord = new Order(Integer.parseInt(order[0]), Integer.parseInt(order[1]));
            if (order[2].equals("buy")) {
                res += processBuyOrder(sellOrders, buyOrders, ord);
            } else {
                res += processSellOrder(sellOrders, buyOrders, ord);
            }
        }
        return res;
    }

    public int processBuyOrder(Queue<Order> sellOrders, Queue<Order> buyOrders, Order buyOrd) {
        if (sellOrders.isEmpty() || sellOrders.peek().price > buyOrd.price) {
            buyOrders.offer(buyOrd);
            return 0;
        }
        int executedShares = 0;
        while (!sellOrders.isEmpty() && buyOrd.quantity > 0) {
            if (sellOrders.peek().price > buyOrd.price) {
                buyOrders.offer(buyOrd);
                return executedShares;
            }
            Order sellOrd = sellOrders.poll();
            executedShares += Math.min(sellOrd.quantity, buyOrd.quantity);
            int newSellQuantity = Math.max(0, sellOrd.quantity - buyOrd.quantity);
            int newBuyQuantity = Math.max(0, buyOrd.quantity - sellOrd.quantity);
            sellOrd.quantity = newSellQuantity;
            buyOrd.quantity = newBuyQuantity;
            if (sellOrd.quantity > 0) {
                sellOrders.offer(sellOrd);
            }
        }
        if (sellOrders.isEmpty() && buyOrd.quantity > 0) {
            buyOrders.offer(buyOrd);
        }
        return executedShares;
    }

    public int processSellOrder(Queue<Order> sellOrders, Queue<Order> buyOrders, Order sellOrd) {
        if (buyOrders.isEmpty() || buyOrders.peek().price < sellOrd.price) {
            sellOrders.offer(sellOrd);
            return 0;
        }
        int executedShares = 0;
        while (!buyOrders.isEmpty() && sellOrd.quantity > 0) {
            if (buyOrders.peek().price < sellOrd.price) {
                sellOrders.offer(sellOrd);
                return executedShares;
            }
            Order buyOrd = buyOrders.poll();
            executedShares += Math.min(sellOrd.quantity, buyOrd.quantity);
            int newSellQuantity = Math.max(0, sellOrd.quantity - buyOrd.quantity);
            int newBuyQuantity = Math.max(0, buyOrd.quantity - sellOrd.quantity);
            sellOrd.quantity = newSellQuantity;
            buyOrd.quantity = newBuyQuantity;
            if (buyOrd.quantity > 0) {
                buyOrders.offer(buyOrd);
            }
        }
        if (buyOrders.isEmpty() && sellOrd.quantity > 0) {
            sellOrders.offer(sellOrd);
        }
        return executedShares;
    }

    static class Order {
        int price;
        int quantity;

        public Order(int price, int quantity) {
            this.price = price;
            this.quantity = quantity;
        }
    }
}

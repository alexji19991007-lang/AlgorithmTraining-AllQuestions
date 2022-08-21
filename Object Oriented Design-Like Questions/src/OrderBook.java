import com.sun.source.util.Trees;

import java.util.*;

public class OrderBook {
    public static void main(String[] args) {
        OrderBook test = new OrderBook();
        String[] a = {"1", "102.54", "131", "Buy"};
        String[] b = {"2", "101.87", "32", "Buy"};
        String[] c = {"3", "103.23", "48", "Sell"};
        String[] d = {"4", "103.98", "84", "Sell"};
        String[] e = {"5", "104.17", "38", "Sell"};
        test.placeOrder(a);
        test.placeOrder(b);
        test.placeOrder(c);
        test.placeOrder(d);
        test.placeOrder(e);

        String[] f = {"6", "102.55", "24", "Buy"};
        String[] g = {"7", "102.55", "14", "Buy"};
        test.placeOrder(f);
        test.placeOrder(g);
//        test.printAllOrders();

        test.listBuyOrdersAtPrice(102.55);
        System.out.println();

//        System.out.println();
//        System.out.println("One new sell order comes in");
//        System.out.println();

        String[] h = {"8", "102.55", "40", "Sell"};
        test.placeOrder(h);
        test.cancelOrder(4);
        test.cancelOrder(5);
        test.printAllOrders();
    }

    Map<Integer, Order> timeStampToOrder;
    Map<Double, Set<Order>> priceLevelToBuyOrders;
    Map<Double, Set<Order>> priceLevelToSellOrders;
    TreeSet<Order> buyOrders;
    TreeSet<Order> sellOrders;

    public OrderBook() {
        this.timeStampToOrder = new HashMap<>();
        // Max-Heap for buy orders but implemented using a TreeSet to ensure O(logn) time complexity for adding and removing.
        this.buyOrders = new TreeSet<>((o1, o2) -> {
            if (o1.price == o2.price) {
                if (o1.timeStamp == o2.timeStamp) {
                    return 0;
                }
                return o1.timeStamp < o2.timeStamp ? -1 : 1;
            }
            return o1.price > o2.price ? -1 : 1;
        });
        // Min-Heap for sale orders but implemented using a TreeSet to ensure O(logn) time complexity for adding and removing.
        this.sellOrders = new TreeSet<>((o1, o2) -> {
            if (o1.price == o2.price) {
                if (o1.timeStamp == o2.timeStamp) {
                    return 0;
                }
                return o1.timeStamp < o2.timeStamp ? -1 : 1;
            }
            return o1.price < o2.price ? -1 : 1;
        });
        this.priceLevelToBuyOrders = new HashMap<>();
        this.priceLevelToSellOrders = new HashMap<>();
    }

    public int placeOrder(String[] order) {
        // Create the Order object
        int timeStamp = Integer.parseInt(order[0]);
        double price = Double.parseDouble(order[1]);
        double quantity = Double.parseDouble(order[2]);
        ActionType actionType = ActionType.valueOf(order[3]);
        Order o = new Order(timeStamp, price, quantity, actionType);

        // Process the order in the system
        int res = 0;
        if (actionType == ActionType.Buy) {
            priceLevelToBuyOrders.putIfAbsent(o.price, new HashSet<>());
            res += processBuyOrder(o);
        } else {
            priceLevelToSellOrders.putIfAbsent(o.price, new HashSet<>());
            res += processSellOrder(o);
        }
        return res;
    }

//    public void amendOrder(int id, int newPrice) {
//        Order o = timeStampToOrder.get(id);
//        if (o.actionType == ActionType.Buy) {
//            buyOrders.remove(o);
//        } else {
//            sellOrders.remove(o);
//        }
//        o.price = newPrice;
//        if (o.actionType == ActionType.Buy) {
//            buyOrders.add(o);
//        } else {
//            sellOrders.add(o);
//        }
//    }

    public void cancelOrder(int timeStamp) {
        Order o = timeStampToOrder.get(timeStamp);
        if (o.actionType == ActionType.Buy) {
            removeBuyOrder(o);
        } else {
            removeSellOrder(o);
        }
    }

    public void printAllOrders() {
        System.out.println("Buy Orders: ");
        for (Order o : buyOrders) {
            System.out.println(o.toString());
        }
        System.out.println();
        System.out.println("Sell Orders");
        for (Order o : sellOrders) {
            System.out.println(o.toString());
        }
    }

    public List<Order> listSellOrdersAtPrice(double price) {
        Set<Order> orders = priceLevelToSellOrders.getOrDefault(price, new HashSet<>());
        for (Order o : orders) {
            System.out.println(o.toString());
        }
        return new ArrayList<>(orders);
    }

    public List<Order> listBuyOrdersAtPrice(double price) {
        Set<Order> orders = priceLevelToBuyOrders.getOrDefault(price, new HashSet<>());
//        for (Order o : orders) {
//            System.out.println(o.toString());
//        }
        return new ArrayList<>(orders);
    }

    private int processBuyOrder(Order buyOrd) {
        // If we do not have any sell orders available or the lowest selling price is higher than the current buy order's price,
        // we just add the sell buy order to our records.
        if (sellOrders.isEmpty() || sellOrders.first().price > buyOrd.price) {
            addBuyOrder(buyOrd);
            return 0;
        }
        int executedShares = 0;
        // Continue matching until we have no more sell orders or the current buy order is completely fulfilled.
        while (!sellOrders.isEmpty() && buyOrd.quantity > 0) {
            // Stop matching if the lowest selling price is higher than the current buy order's price.
            if (sellOrders.first().price > buyOrd.price) {
                // Add the buy order to our records.
                addBuyOrder(buyOrd);
                return executedShares;
            }

            // Get the sell order and remove it from our records first.
            Order sellOrd = sellOrders.first();
            removeSellOrder(sellOrd);

            // Executing transaction
            executedShares += Math.min(sellOrd.quantity, buyOrd.quantity);
            double newSellQuantity = Math.max(0, sellOrd.quantity - buyOrd.quantity);
            double newBuyQuantity = Math.max(0, buyOrd.quantity - sellOrd.quantity);
            sellOrd.quantity = newSellQuantity;
            buyOrd.quantity = newBuyQuantity;

            // If the current sell order is not completely fulfilled, add it back to our records.
            if (sellOrd.quantity > 0) {
                addSellOrder(sellOrd);
            }
        }
        // If we have no more sell orders yet the current buy order is not completely fulfilled, add the buy order to our records.
        if (sellOrders.isEmpty() && buyOrd.quantity > 0) {
            addBuyOrder(buyOrd);
        }
        return executedShares;
    }

    private int processSellOrder(Order sellOrd) {
        // If we do not have any buy orders available or the highest buying price is lower than the current sell order's price,
        // we just add the sell buy order to our records.
        if (buyOrders.isEmpty() || buyOrders.first().price < sellOrd.price) {
            addSellOrder(sellOrd);
            return 0;
        }
        int executedShares = 0;
        // Continue matching until we have no more buy orders or the current sell order is completely fulfilled.
        while (!buyOrders.isEmpty() && sellOrd.quantity > 0) {
            // Stop matching if the highest buying price is lower than the current sell order's price.
            if (buyOrders.first().price < sellOrd.price) {
                // Add the sell order to our records.
                addSellOrder(sellOrd);
                return executedShares;
            }

            // Get the buy order and remove it from our records first.
            Order buyOrd = buyOrders.first();
            removeBuyOrder(buyOrd);

            // Executing transaction
            executedShares += Math.min(sellOrd.quantity, buyOrd.quantity);
            double newSellQuantity = Math.max(0, sellOrd.quantity - buyOrd.quantity);
            double newBuyQuantity = Math.max(0, buyOrd.quantity - sellOrd.quantity);
            sellOrd.quantity = newSellQuantity;
            buyOrd.quantity = newBuyQuantity;

            // If the current buy order is not completely fulfilled, add it back to our records.
            if (buyOrd.quantity > 0) {
                addBuyOrder(buyOrd);
            }
        }

        // If we have no more buy orders yet the current sell order is not completely fulfilled, add the sell order to our records.
        if (buyOrders.isEmpty() && sellOrd.quantity > 0) {
            addSellOrder(sellOrd);
        }
        return executedShares;
    }

    private void addBuyOrder(Order buyOrd) {
        buyOrders.add(buyOrd);
        timeStampToOrder.put(buyOrd.timeStamp, buyOrd);
        priceLevelToBuyOrders.get(buyOrd.price).add(buyOrd);
    }

    private void addSellOrder(Order sellOrd) {
        sellOrders.add(sellOrd);
        timeStampToOrder.put(sellOrd.timeStamp, sellOrd);
        priceLevelToSellOrders.get(sellOrd.price).add(sellOrd);
    }

    private void removeBuyOrder(Order buyOrd) {
        buyOrders.remove(buyOrd);
        timeStampToOrder.remove(buyOrd.timeStamp);
        priceLevelToBuyOrders.get(buyOrd.price).remove(buyOrd);
    }

    private void removeSellOrder(Order sellOrd) {
        sellOrders.remove(sellOrd);
        timeStampToOrder.remove(sellOrd.timeStamp);
        priceLevelToSellOrders.get(sellOrd.price).remove(sellOrd);
    }

    static class Order {
        int timeStamp;
        double price;
        double quantity;
        ActionType actionType;

        public Order(int timeStamp, double price, double quantity, ActionType actionType) {
            this.timeStamp = timeStamp;
            this.price = price;
            this.quantity = quantity;
            this.actionType = actionType;
        }

        @Override
        public String toString() {
            return timeStamp + " " + price + " " + quantity;
        }
    }

    enum ActionType {
        Buy,
        Sell
    }
}

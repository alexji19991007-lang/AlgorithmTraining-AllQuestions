import java.util.*;

public class OrderBook {
    Map<Integer, Order> idToOrder;
    TreeSet<Order> buyOrder;
    TreeSet<Order> sellOrder;

    public OrderBook() {
        this.idToOrder = new HashMap<>();
        this.buyOrder = new TreeSet<>((o1, o2) -> o1.price != o2.price ? o2.price - o1.price : o1.quantity - o2.quantity);
        this.sellOrder = new TreeSet<>((o1, o2) -> o1.price != o2.price ? o1.price - o2.price : o1.quantity - o2.quantity);
    }

    public void placeOrder(String[] order) {
        int id = Integer.parseInt(order[0]);
        int price = Integer.parseInt(order[1]);
        int quantity = Integer.parseInt(order[2]);
        ActionType actionType = ActionType.valueOf(order[3]);
        Order o = new Order(id, price, quantity, actionType);
        idToOrder.put(id, o);
        if (actionType == ActionType.Buy) {
            buyOrder.add(o);
        } else {
            sellOrder.add(o);
        }
    }

    public void amendOrder(int id, int newPrice) {
        Order o = idToOrder.get(id);
        if (o.actionType == ActionType.Buy) {
            buyOrder.remove(o);
        } else {
            sellOrder.remove(o);
        }
        o.price = newPrice;
        if (o.actionType == ActionType.Buy) {
            buyOrder.add(o);
        } else {
            sellOrder.add(o);
        }
    }

    public void removeOrder(int id) {
        Order o = idToOrder.get(id);
        idToOrder.remove(id);
        if (o.actionType == ActionType.Buy) {
            buyOrder.remove(o);
        } else {
            sellOrder.remove(o);
        }
    }

    public void Trade() {

    }

    static class Order {
        int id;
        int price;
        int quantity;
        ActionType actionType;

        public Order(int id, int price, int quantity, ActionType actionType) {
            this.id = id;
            this.price = price;
            this.quantity = quantity;
            this.actionType = actionType;
        }

        public Order(Order other) {
            this.id = other.id;
            this.price = other.price;
            this.quantity = other.quantity;
            this.actionType = other.actionType;
        }
    }

    enum ActionType {
        Buy,
        Sell
    }
}

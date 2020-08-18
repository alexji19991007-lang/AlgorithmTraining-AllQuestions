import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test2 {
    public static void main(String[] args) {
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
        sellOrders.offer(new Order(1, 2));
        sellOrders.offer(new Order(2, 3));
        sellOrders.offer(new Order(3, 4));
        sellOrders.offer(new Order(4, 5));
        Order x = sellOrders.peek();
        System.out.println(x.price + " " + x.quantity);
        x.quantity = 22;
        Order y = sellOrders.peek();
        System.out.println(y.price + " " + y.quantity);
        y.price = 5;
        Order z = sellOrders.peek();
        System.out.println(z.price + " " + z.quantity);

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

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test2 {
    public static void main(String[] args) {
        int i = 1;
        System.out.println(i == 0 ? 0 : 2 + 1);

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

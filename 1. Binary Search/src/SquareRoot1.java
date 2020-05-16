import java.util.*;

public class SquareRoot1 {
    public static void main(String[] args) {
        System.out.println(sqrt(744348785));
    }

    public static int sqrt(int x) {
        if (x < 2) return x;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            long num = (long) pivot * pivot; // We need to take care when pivot * pivot > Integer.MAX_VALUE;
            if (num > x) {
                right = pivot - 1;
            } else if (num < x) {
                left = pivot + 1;
            } else {
                return pivot;
            }
        }
        return right;
    }
}

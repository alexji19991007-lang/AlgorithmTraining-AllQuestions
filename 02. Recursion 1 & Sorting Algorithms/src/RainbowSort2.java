import java.util.*;

public class RainbowSort2 {
    public static void main(String[] args) {
        int[] array = {2, 3, 3, 1, 2, 3, 0, 1, 3, 0};
        System.out.println(Arrays.toString(rainbowSortII(array)));
    }

    public static int[] rainbowSortII(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int a = 0, b = 0, c = 0, d = array.length - 1;
        while (c <= d) {
            if (array[c] == 3) {
                swap(array, c, d);
                d--;
            } else if (array[c] == 0) {
                swap(array, c, b);
                swap(array, b, a);
                a++;
                c++;
                b++;
            } else if (array[c] == 1) {
                swap(array, b, c);
                b++;
                c++;
            } else {
                c++;
            }
        }
        return array;
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

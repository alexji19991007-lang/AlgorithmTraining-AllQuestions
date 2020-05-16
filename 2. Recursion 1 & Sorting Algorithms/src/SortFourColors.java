import java.util.*;

public class SortFourColors {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 4, 1, 3, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2, 3, 1, 4};
        sortFour(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sortFour(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int a = 0, b = 0, c = 0, d = array.length - 1;
        while (c <= d) {
            if (array[c] == 4) {
                swap(array, c, d);
                d--;
            } else if (array[c] == 1) {
                swap(array, a, c);
                a++;
                if (array[c] == 2) {
                    swap(array, c, b);
                }
                c++;
                b++;
            } else if (array[c] == 2) {
                swap(array, b, c);
                b++;
                c++;
            } else {
                c++;
            }
        }
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

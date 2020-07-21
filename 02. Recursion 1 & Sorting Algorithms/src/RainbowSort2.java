import java.util.*;

public class RainbowSort2 {
    public static void main(String[] args) {
        int[] array = {2, 3, 3, 1, 2, 3, 0, 1, 3, 0};
        rainbowSortII(array);
        System.out.println(Arrays.toString(array));
    }

    public static void rainbowSortII(int[] array) {
        // Physical meaning of ix:
        // All numbers to the left of ix are all sorted and smaller than x.
        int i0 = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        while(i3 < array.length) {
            if(array[i3] == 3) {
                i3++;
            } else if(array[i3] == 2) {
                swap(array, i2, i3);
                i2++;
                i3++;
            } else if(array[i3] == 1) {
                swap(array, i2, i3);
                swap(array, i1, i2);
                i1++;
                i2++;
                i3++;
            } else {
                swap(array, i2, i3);
                swap(array, i1, i2);
                swap(array, i0, i1);
                i0++;
                i1++;
                i2++;
                i3++;
            }
        }
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

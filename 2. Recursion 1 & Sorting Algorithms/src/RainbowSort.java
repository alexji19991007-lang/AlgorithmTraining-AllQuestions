import java.util.Arrays;

public class RainbowSort {
    public static void main(String[] args) {
        int[] array = {1, 1, 0, 0, -1, -1, 0, 1};
        System.out.println(Arrays.toString(rainbowSort(array)));
    }

    public static int[] rainbowSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        // 三个pointer.
        // j: the rightmost boundary of -1s. k: the leftmost boundary of 1s
        // i: the current element under consideration
        // j and k will move if and only if the number they point to changes
        int i = 0, j = 0, k = array.length - 1;
        while (i <= k) {
            // 若当前数字(i)为1，将i的数字与k的数字交换
            if (array[i] == 1) {
                swap(array, i, k);
                // 注意，在此情况下我们只需向前移动k，因为k当前位置是1，以后不会再动了
                // 对于i来说，它换回来的东西仍有可能发生改变，所以不能动i
                k--;
            } else if (array[i] == -1) { // 若当前数字为-1，将i的数字与j的数字交换
                swap(array, i , j);
                // 在此情况下，i可以直接移动，因为它换来的东西不会是1
                i++;
                j++;
            } else {
                // 0不需要移动
                i++;
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

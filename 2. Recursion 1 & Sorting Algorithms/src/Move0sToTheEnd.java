import java.util.Arrays;

public class Move0sToTheEnd {
    public static void main(String[] args) {
        int[] array = {5, 2, 0, 3, 0, 1, 0, 1, 0, 0};
        System.out.println(Arrays.toString(moveZero(array)));
    }


    public static int[] moveZero(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int i = 0, j = array.length - 1;
        while (i <= j) {
            if (array[i] != 0) {
                i++;
            } else if (array[j] == 0) {
                j--;
            } else {
                swap(array, i++, j--);
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

import java.util.*;

public class ReorderArray {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.toString(reorder(array)));
    }


    public static int[] reorder(int[] array) {
        if (array.length % 2 == 0) {
            shuffleHelper(array, 0, array.length - 1);
        } else {
            shuffleHelper(array, 0, array.length - 2);
        }
        return array;
    }

    public static void shuffleHelper(int[] array, int left, int right) {
        // Recursion terminates when left neighbors right.
        if (left + 1 >= right) {
            return;
        }
        int size = right - left + 1;
        int mid = left + size / 2;
        int leftMid = left + size / 4;
        int rightMid = left + size * 3 / 4;
        reverseHelper(array, leftMid, mid - 1);
        reverseHelper(array, mid, rightMid - 1);
        reverseHelper(array, leftMid, rightMid - 1);
        // We ensure that the length from left to leftMid the same as that from right to rightMid
        // So the new right position should be "left + 2 * (leftMid - left) - 1"
        shuffleHelper(array, left, left + 2 * (leftMid - left) - 1);
        shuffleHelper(array, left + 2 * (leftMid - left), right);
    }

    public static void reverseHelper(int[] input, int left, int right) {
        if (left >= right) {
            return;
        }
        while (left < right) {
            swap(input, left++, right--);
        }
    }

    public static void swap(int[] input, int left, int right) {
        int temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }
}

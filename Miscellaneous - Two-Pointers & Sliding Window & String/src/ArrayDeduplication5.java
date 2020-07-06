import java.util.Arrays;

// Assumption: Remove all adjacent duplicate characters, leaving only two continuous occurrence left. The array is not
//             guaranteed to be sorted.
// Example: {1, 2, 2, 3, 3, 3} --> {1, 2, 2, 3, 3}
public class ArrayDeduplication5 {
    public static void main(String[] args) {
        int[] array = {1, 2, 2, 3, 2, 2, 2, 3, 3, 3, 3};
        ArrayDeduplication5 test = new ArrayDeduplication5();
        System.out.println(Arrays.toString(test.dedup(array)));
    }

    public int[] dedup(int[] array) {
        if (array.length <= 2) {
            return array;
        }
        int slow = 2;
        for (int i = 2; i < array.length; ++i) {
            if (array[i] != array[slow - 2] || array[i] != array[i - 1]) {
                array[slow++] = array[i];
            }
        }
        return Arrays.copyOf(array, slow);
    }
}

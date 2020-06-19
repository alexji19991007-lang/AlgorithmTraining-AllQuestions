import java.util.Arrays;

public class ArrayDeduplication1 {
    // [1, 2, 2, 3, 3, 4] → [1, 2, 3, 4] (sorted array)
    public int[] dedup(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int end = 1;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] != array[i - 1]) {
                array[end++] = array[i];
            }
        }
        return Arrays.copyOf(array, end);
    }
}

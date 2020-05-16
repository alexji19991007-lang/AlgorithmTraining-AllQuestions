import java.util.Arrays;

// Assumption: Remove all adjacent duplicate characters, leaving only one occurrence left.
// Example: {1, 2, 2, 3, 3, 3} → {1, 2, 3}
public class ArrayDeduplication1 {
    public int[] dedup(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int end = 1;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] != array[end - 1]) {
                array[end++] = array[i];
            }
        }
        return Arrays.copyOf(array, end);
    }
}

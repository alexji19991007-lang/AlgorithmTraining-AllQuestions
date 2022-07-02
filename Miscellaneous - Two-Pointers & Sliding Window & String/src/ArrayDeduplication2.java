import java.util.Arrays;

// Assumption: Remove all adjacent duplicate characters, leaving only two continuous occurrence left.
// Example: {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
public class ArrayDeduplication2 {
    public int[] dedup(int[] array) {
        if (array.length <= 2) {
            return array;
        }
        int slow = 2;
        for (int i = 2; i < array.length; ++i) {
            if (array[i] != array[slow - 2] || array[i] != array[slow - 1]) {
                array[slow++] = array[i];
            }
        }
        return Arrays.copyOf(array, slow);
    }
}

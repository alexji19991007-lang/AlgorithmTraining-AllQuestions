import java.util.Arrays;

public class ArrayDeduplication2 {
    // [1, 2, 2, 2, 3, 3, 3, 4] → [1, 2, 2, 3, 3, 4]
    public int[] dedup(int[] array) {
        if (array.length <= 2) {
            return array;
        }
        int end = 2;
        for (int i = 2; i < array.length; ++i) {
            if (array[i] != array[end - 2]) {
                array[end++] = array[i];
            }
        }
        return Arrays.copyOf(array, end);
    }
}

import java.util.Arrays;

// Assumption: Remove all adjacent duplicate characters and do it repeatedly.
// Example: {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}
public class ArrayDeduplication4 {
    public int[] dedup(int[] array) {
        int slow = 1;
        int fast = 1;
        while (fast < array.length) {
            if (slow == 0 || array[slow - 1] != array[fast]) {
                array[slow++] = array[fast];
            } else {
                slow--;
                while (fast + 1 < array.length && array[fast] == array[fast + 1]) {
                    fast++;
                }
            }
            fast++;
        }
        return Arrays.copyOf(array, slow);
    }
}

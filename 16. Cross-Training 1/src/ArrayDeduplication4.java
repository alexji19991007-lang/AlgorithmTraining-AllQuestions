import java.util.Arrays;

public class ArrayDeduplication4 {
    // [1, 2, 2, 2, 3, 3, 2, 4] → [1, 4]
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

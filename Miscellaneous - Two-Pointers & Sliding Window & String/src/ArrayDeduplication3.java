import java.util.Arrays;

// Assumption: Remove all adjacent duplicate characters.
// Example: {1, 2, 2, 3, 3, 3} → {1}
public class ArrayDeduplication3 {
    public int[] dedup(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int end = 0;
        boolean hasRepeat = false;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] == array[end]) {
                hasRepeat = true;
            } else if (hasRepeat) {
                // We encounter a new character, but the previous characters are repeating, so we should
                // put the current character to the front, ignoring previous repeating characters.
                array[end] = array[i];
                hasRepeat = false;
            } else {
                // We encounter a new character and the previous characters are not repeating, just add
                // the new character
                array[++end] = array[i];
            }
        }
        return Arrays.copyOf(array, hasRepeat ? end : end + 1);
    }
}

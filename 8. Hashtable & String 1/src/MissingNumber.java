import java.util.*;

public class MissingNumber {
    public int missing(int[] array) {
        // Write your solution here
        if (array.length == 0) {
            return 1;
        }
        int res = array[0];
        for (int i = 1; i < array.length; ++i) {
            res ^= array[i];
        }
        for (int i = 1; i <= array.length + 1; ++i) {
            res ^= i;
        }
        return res;
    }
}

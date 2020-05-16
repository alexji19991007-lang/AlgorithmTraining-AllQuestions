import java.util.*;

public class MissingNumber {
    public int missing(int[] array) {
        if (array.length == 0) {
            return 1;
        }
        int res = array[0];
        // Important rules: x ^ x = 0; x ^ 0 = x; x ^ 1 = ~x
        // x ^ y ^ z ^ x ^ y = (x ^ x) ^ (y ^ y) ^ z = 0 ^ 0 ^ z = z
        for (int i = 1; i < array.length; ++i) {
            res ^= array[i];
        }
        for (int i = 1; i <= array.length + 1; ++i) {
            res ^= i;
        }
        return res;
    }
}

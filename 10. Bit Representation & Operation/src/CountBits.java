import java.util.*;

public class CountBits {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        // look at the relation between x and x' = x / 2
        // x = (1001011101)_2 = 605
        // x' = (100101110)_2 = 302
        // They only differ by the LSB, thus P(x) = P(x / 2) + (x % 2)
        // Now all we care about is the LSB of x. We want to know if it's 1 or 0.
        // x % 2: If x is odd, the LSB of its binary representation is 1, so right shift
        // (i.e. divided by 2) will make it lose a '1' which was its LSB. Thus, if x % 2 == 1
        // (i.e. odd), we need to add that 1.
        for (int i = 1; i <= num; ++i) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }
}

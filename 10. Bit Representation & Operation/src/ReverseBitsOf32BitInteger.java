import java.util.*;

public class ReverseBitsOf32BitInteger {
    public long reverseBits(long n) {
        int left = 31;
        int right = 0;
        while (left > right) {
            if (((n >> left) & 1L) != ((n >> right) & 1L)){
                n ^= (1L << left);
                n ^= (1L << right);
            }
            left--;
            right++;
        }
        return n;
    }
}

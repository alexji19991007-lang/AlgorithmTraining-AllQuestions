import java.util.*;

public class ReverseBitsOf32BitInteger {
    public long reverseBitsFaster(long n) {
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

    private int reverseAllBitsSlow(int x) {
        int i = 0;
        int j = 31;
        while (i < j) {
            x = swap(x, i, j);
            i++;
            j--;
        }
        return x;
    }

    int swap(int x, int i, int j) {
        int iBit = (x >> i) & 1;
        int jBit = (x >> j) & 1;
        if (iBit == jBit) { return x; }
        // 如果原本数字是1，1 xor 1会变成0
        // 如果原本数字是0，0 xor 1会变成1
        return x ^ ((1 << i) + (1 << j));
    }
}

import java.util.*;

public class NumberOfDifferentBits {
    public int diffBits(int a, int b) {
        int xor = a ^ b;
        int count = 0;
        for (int i = 0; i < 32; ++i) {
            if (((xor >> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }
}

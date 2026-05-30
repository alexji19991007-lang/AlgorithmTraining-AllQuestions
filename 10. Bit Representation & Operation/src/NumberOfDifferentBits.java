import java.util.*;

// a = 5 --> 0b0000101
// b = 6 --> 0b0000110
// a ^ b --> 0b0000011 --> count how many 1s
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


    // 注意这个for loop其实可以early termination。
    public int countDifferentBitsFaster(int a, int b) {
        int count = 0;
        for (int c = a ^ b; // initialization
             c != 0; // termination condition
             c = c >> 1) { // increment
            count += (c & 1);
        }
        return count;
    }
}

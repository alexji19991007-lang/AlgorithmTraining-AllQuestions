//x is a power of 2 if and only if
//    (1) x's binary representation has exactly one bit set
//    (2) x > 0
public class PowerOfTwo {
    // Method1: count how many bits are 1s. → if number of bits is 1 == 1 return true;
    public boolean isPowerOfTwoByCounting1s(int n) {
        int res = 0; // count the number of ones
        while (n > 0) {
            // check n's least significant bit is 1 or not
            res += (n & 1);
            n >>= 1; // n = (n >> 1)
        }
        return n > 0 && res == 1;
    }

    // Method 2: x is a power of 2: 0b 0 0 1 0 0 0 0 0 0
    //                         x-1: 0b 0 0 0 1 1 1 1 1 1
    // (x & (x - 1)) == 0
    // An important assumption here is that x > 0;
    public boolean isPowerOfTwo(int number) {
        return (number & (number - 1)) == 0 && number > 0;
    }
}

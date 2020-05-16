import java.util.*;

public class BitwiseANDOfNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        // We can reformulate the problem as "given two integer numbers,
        // we are asked to find the common prefix of their binary strings."
        int shift = 0;
        while (m < n) {
            // When m == n, the remaining bits are their common prefix
            m = m >> 1;
            n = n >> 1;
            shift++;
        }
        // So the result is just m shifted left.
        return m << shift;
    }

}

package HackerRank;

import java.util.ArrayList;
import java.util.List;

// For a number N, a goodArray is the smallest possible array that consists of powers of two (2^0, 2^1, 2^2, ..., 2^k)
// such that the sum of all the numbers in the array is equal to N
// E.g: if N = 26, good array when sorted is [2, 8, 16]
public class GoodArrayQuery {
    public static void main(String[] args) {
        GoodArrayQuery test = new GoodArrayQuery();
        long x = Long.parseLong("406544087953391");
        System.out.println(test.getSingleQueryResult(x, 5, 24, 66414));
    }

    public long getSingleQueryResult(long n, int l, int r, int m) {
        List<Long> goodArray = getGoodArray(n);
        System.out.println(goodArray);
        List<Long> modulusList = new ArrayList<>();
        for (int i = l; i <= r; ++i) {
            long modulus = goodArray.get(goodArray.size() - i) % m;
            modulusList.add(modulus);
        }
        int i = 0;
        int curProduct = 1;
        while (i < modulusList.size()) {
            curProduct *= modulusList.get(i++);
            if (curProduct > m) {
                curProduct %= m;
            }
        }
        return curProduct % m;
    }

    public List<Long> getGoodArray(long n) {
        int largestPower = 0;
        long twoPower = 1;
        while (twoPower * 2 < n) {
            twoPower *= 2;
            largestPower++;
        }
        List<Long> goodArray = new ArrayList<>();
        while (n > 0 && largestPower >= 0) {
            long curTwoPower = getTwoPower(largestPower--);
            if (n - curTwoPower >= 0) {
                goodArray.add(curTwoPower);
                n -= curTwoPower;
            }
        }
        return goodArray;
    }

    public long getTwoPower(int power) {
        long res = 1;
        for (int i = 0; i < power; ++i) {
            res *= 2;
        }
        return res;
    }
}

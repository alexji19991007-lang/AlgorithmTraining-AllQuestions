import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TestCodility {
    public static void main(String[] args) {
        TestCodility test = new TestCodility();
//        test.enablePrint(10340101);
//        test.solution(105);
        System.out.println(test.calculateXOR_1(6, 12));
        System.out.println(test.calculateXOR_2(6, 12));
    }

    public void enablePrint(int N) {
        int enable_print = N % 10;
        while (N > 0) {
            if (enable_print == 0 && N % 10 != 0) {
                enable_print = N % 10;
            }
            if (enable_print >= 1) {
                System.out.print(N % 10);
            }
            N = N / 10;
        }
    }

    public int calculateXOR_1(int M, int N) {
        // Implement your solution here
        int result = M;
        for (int i = M + 1; i <= N; ++i) {
            result ^= i;
        }
        return result;
    }

    public int calculateXOR_2(int M, int N) {
        // using the property:
        // 0 xor A = A
        // A xor A = 0
        // We derive:
        // (1 xor 2 xor 3 ... xor N) xor (1 xor 2 xor 3 ... xor M - 1)
        // = 0 xor 0 xor 0 xor 0 ... xor M xor (M + 1) xor (M + 2) ... xor N
        // = M xor (M + 1) xor (M + 2) .... xor N
        return xorOneToN(N) ^ xorOneToN(M - 1);
    }

    // There is a periodic property of 1 xor 2 xor ... xor n as shown below
    public int xorOneToN(int n) {
        return switch (n % 4) {
            case 0 -> n;
            case 1 -> 1;
            case 2 -> n + 1;
            default -> 0;
        };
    }
}

public class FibonacciNumber {
    public long fibonacci(int K) {
        if (K <= 0) {
            return 0;
        }
        if (K <= 2) {
            return 1;
        }
        long[] fibo = new long[K + 1];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i <= K; ++i) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }
        return fibo[K];
    }
}

// LeetCode 829
public class ConsecutiveNumbersSum {
    // N = (x + 1) + (x + 2) + ... + (x + k)
    // N = xk + k(k + 1) / 2
    // x = N / k - (k + 1) / 2 --> x >= 0 && x should be an integer
    // N / k >= (k + 1) / 2 --> k <= sqrt(2N + 1 / 2) - 1 / 2
    public int consecutiveNumbersSum(int N) {
        int count = 0;
        int upperLimit = (int)(Math.sqrt(2 * N + 0.25) - 0.5);
        for (int k = 1; k <= upperLimit; ++k) {
            if ((N - k * (k + 1) / 2) % k == 0) {
                count++;
            }
        }
        return count;
    }
}

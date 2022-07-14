package HackerRank;

public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        int[] seconds = new int[60];
        for (int t : time) {
            seconds[t % 60] += 1;
        }
        count += combination(seconds[30], 2);
        count += combination(seconds[0], 2);
        int i = 1, j = 59;
        while (i < j) {
            count += seconds[i++] * seconds[j--];
        }
        return count;
    }

    // 求组合数
    public int combination(int n, int k) {
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }
        return (int) result;
    }

    public int numPairsDivisibleBy60_2(int[] time) {
        int[] c = new int[60];
        int res = 0;
        for (int t : time) {
            res += c[(600 - t) % 60];
            c[t % 60]++;
        }
        return res;
    }
}

package HackerRank;

import java.util.Arrays;

public class Triplets {
    public int triplets(int t, int[] d) {
        Arrays.sort(d);
        int res = 0;
        for (int i = 0; i < d.length - 2; ++i) {
            int j = i + 1, k = d.length - 1;
            while (j < k) {
                if (d[i] + d[j] + d[k] <= t) {
                    res += k - j;
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}

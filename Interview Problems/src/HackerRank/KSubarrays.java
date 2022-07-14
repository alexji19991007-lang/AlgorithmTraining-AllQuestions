package HackerRank;

import java.util.List;

public class KSubarrays {
    public long kSub(int k, List<Integer> integers) {
        int[] modCount = new int[k];
        modCount[0] = 1;
        int res = 0;
        int prefixSum = 0;
        for (int i : integers) {
            prefixSum = (prefixSum + i) % k;
            if (prefixSum < 0) {
                prefixSum += k;
            }
            res += modCount[prefixSum];
            modCount[prefixSum]++;
        }
        return res;
    }
}

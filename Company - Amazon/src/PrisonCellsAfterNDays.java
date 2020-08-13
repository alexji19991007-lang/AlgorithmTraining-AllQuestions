import java.util.HashMap;
import java.util.Map;

// LeetCode 957
public class PrisonCellsAfterNDays {
    // TC: O(min(N, 2^k)), without fast forward, we may encounter 2^k cases
    // SC: O(2^k)
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> seen = new HashMap<>();
        boolean isFastForwarded = false;

        int stateBitMap = 0;
        for (int cell : cells) {
            stateBitMap <<= 1;
            stateBitMap |= cell;
        }

        while (N > 0) {
            if (!isFastForwarded) {
                if (seen.containsKey(stateBitMap)) {
                    N %= seen.get(stateBitMap) - N;
                    isFastForwarded = true;
                } else {
                    seen.put(stateBitMap, N);
                }
            }
            if (N > 0) {
                N--;
                stateBitMap = nextDay(stateBitMap);
            }
        }

        int[] res = new int[cells.length];
        for (int i = cells.length - 1; i >= 0; --i) {
            res[i] = (stateBitMap & 1);
            stateBitMap >>= 1;
        }
        return res;
    }

    private int nextDay(int stateBitMap) {
        stateBitMap = ~((stateBitMap << 1) ^ (stateBitMap >> 1));
        stateBitMap &= 0x7e;
        return stateBitMap;
    }
}

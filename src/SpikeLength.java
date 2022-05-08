import java.util.HashMap;
import java.util.Map;

public class SpikeLength {
    public static void main(String[] args) {
        SpikeLength test = new SpikeLength();
        int[] A = {1, 3, 3, 2, 2, 2, 4, 4, 5, 5, 5, 5, 6, 7, 8, 1};
        System.out.println(test.getLength(A));
    }

    public int getLength(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int globalMax = Integer.MIN_VALUE;
        Map<Integer, Integer> occurrence = new HashMap<>();
        for (int i : A) {
            int curCount = occurrence.getOrDefault(i, 0);
            occurrence.put(i, Math.min(2, curCount + 1));
            globalMax = Math.max(i, globalMax);
        }
        // 无论如何global max都只能使用一次
        occurrence.put(globalMax, 1);
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : occurrence.entrySet()) {
            res += entry.getValue();
        }
        return res;
    }
}

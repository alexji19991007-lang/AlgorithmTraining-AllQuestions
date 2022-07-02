import java.util.TreeMap;

// LeetCode 2158
public class AmountOfNewAreaPaintedEachDay {
    // TC: O(N * log(N))
    // SC: O(N)
    public int[] amountPainted(int[][] paint) {
        TreeMap<Integer, Integer> intervals = new TreeMap<>();
        int[] res = new int[paint.length];
        for (int i = 0; i < paint.length; ++i) {
            int start = paint[i][0], end = paint[i][1];
            int area = end - start;
            while (intervals.floorKey(start) != null) {
                int prevStart = intervals.floorKey(start);
                int prevEnd = intervals.get(prevStart);
                if (prevEnd <= start) {
                    break;
                }
                area -= Math.min(prevEnd, end) - Math.max(prevStart, start);
                start = Math.min(prevStart, start);
                end = Math.max(prevEnd, end);
                intervals.remove(prevStart);
            }
            while (intervals.floorKey(end) != null) {
                int prevStart = intervals.floorKey(end);
                int prevEnd = intervals.get(prevStart);
                if (prevEnd <= start) {
                    break;
                }
                area -= Math.min(prevEnd, end) - Math.max(prevStart, start);
                start = Math.min(prevStart, start);
                end = Math.max(prevEnd, end);
                intervals.remove(prevStart);
            }
            res[i] = area;
            intervals.put(start, end);
        }
        return res;
    }
}

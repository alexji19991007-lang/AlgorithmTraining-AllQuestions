import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// LeetCode 56
public class F111_MergeIntervals {
    // TC: O(nlogn)
    // SC: O(n)
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        // sort by first number
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        /* Suppose we have two intervals [n1, n2], [m1, m2]
         *                              newInterval Interval
         *  Case 1: if m1 > n2, no overlap and append current interval to result
         *  Case 2: else, they do overlap, so we merge them by updating the end of the previous
         *          interval if it is less than m2.
         */
        for (int[] interval : intervals) {
            if (interval[0] > newInterval[1]) {
                newInterval = interval;
                result.add(newInterval);
            } else {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}

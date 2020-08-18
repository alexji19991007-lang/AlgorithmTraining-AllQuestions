package Karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MeetingRoom {
    public static void main(String[] args) {
        MeetingRoom test = new MeetingRoom();
        int[][] input = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(test.spareTimeAfterMerge(input)));
    }

    public boolean canBeScheduled(int[][] meetings, int start, int end) {
        for (int[] meeting : meetings) {
            if ((meeting[0] <= start && meeting[1] > start) || (meeting[0] < end && meeting[1] >= end)) {
                return false;
            }
        }
        return true;
    }

    public int[][] spareTimeAfterMerge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        // sort by first number
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> afterMerge = new ArrayList<>();
        int[] newInterval = intervals[0];
        afterMerge.add(newInterval);
        /* Suppose we have two intervals [n1, n2], [m1, m2]
         *                              newInterval Interval
         *  Case 1: if m1 > n2, no overlap and append current interval to result
         *  Case 2: else, they do overlap, so we merge them by updating the end of the previous
         *          interval if it is less than m2.
         */
        for (int[] interval : intervals) {
            if (interval[0] > newInterval[1]) {
                newInterval = interval;
                afterMerge.add(newInterval);
            } else {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        boolean hasSpareTimeBefore = afterMerge.get(0)[0] > 0;
        int resSize = hasSpareTimeBefore ? afterMerge.size() : afterMerge.size() - 1;
        int[][] res = new int[resSize][2];
        int index = 0;
        for (int i = 0; i < afterMerge.size() - 1; ++i) {
            int[] cur = afterMerge.get(i);
            int[] next = afterMerge.get(i + 1);
            if (i == 0 && hasSpareTimeBefore) {
                res[index][0] = 0;
                res[index][1] = cur[0];
                index++;
            }
            res[index][0] = cur[1];
            res[index][1] = next[0];
            index++;
        }
        return res;
    }
}

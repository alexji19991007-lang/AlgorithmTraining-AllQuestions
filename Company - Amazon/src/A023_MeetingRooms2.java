import java.util.Arrays;

public class A023_MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {
        // Suppose we have two meetings, Ma and Mb.
        // A new room is needed only when the starting time of Mb
        // is earlier than the finishing time of Ma.
        if (intervals.length < 1) {
            return 0;
        }
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; ++i) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int numRooms = 0;
        int endPtr = 0;
        for (int i = 0; i < n; ++i) {
            if (start[i] < end[endPtr]) {
                numRooms++;
            } else {
                endPtr++;
            }
        }
        return numRooms;
    }
}

import java.util.Arrays;

public class MeetingRooms {
    public int minMeetingRooms(int[][] intervals) {
        // Suppose we have two meetings, Ma and Mb.
        // A new room is needed only when the starting time of Mb
        // is earlier than the finishing time of Ma.

        // When we encounter an ending event, that means that some
        // meeting that started earlier has ended now. We are not really
        // concerned with which meeting has ended. All we need is that
        // some meeting ended thus making a room available.
        if (intervals.length < 1) {
            return 0;
        }
        int n = intervals.length;
        int[] start = new int[n]; // an array to store start time
        int[] end = new int[n]; // an array to store end time
        for (int i = 0; i < n; ++i) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int numRooms = 0;
        int endPtr = 0;
        for (int i = 0; i < n; ++i) {
            // if one meeting starts before a meeting ends, increment count by 1
            // (I don't care which meeting starts and which meeting ends)
            if (start[i] < end[endPtr]) {
                numRooms++;
            } else {
                // The start time of the current meeting is after the end time of one other meeting,
                // so no more extra meeting room is need.
                endPtr++;
            }
        }
        return numRooms;
    }
}

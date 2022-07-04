import java.util.List;

// LeetCode 539
public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        boolean[] timeOccurrence = new boolean[24 * 60];
        for (String time : timePoints) {
            int hour = 10 * (time.charAt(0) - '0') + (time.charAt(1) - '0');
            int minute = 10 * (time.charAt(3) - '0') + (time.charAt(4) - '0');
            int minuteOfTheDay = hour * 60 + minute;
            if (timeOccurrence[minuteOfTheDay]) {
                return 0;
            }
            timeOccurrence[minuteOfTheDay] = true;
        }
        int res = Integer.MAX_VALUE;
        int prev = 0, first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for (int i = 0; i < 24 * 60; ++i) {
            if (timeOccurrence[i]) {
                if (first != Integer.MAX_VALUE) {
                    res = Math.min(res, i - prev);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }
        return Math.min(res, first + 24 * 60 - last);
    }
}

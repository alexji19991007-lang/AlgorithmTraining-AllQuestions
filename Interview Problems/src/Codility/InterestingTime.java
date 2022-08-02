package Codility;

import java.util.HashSet;
import java.util.Set;

public class InterestingTime {
    public static void main(String[] args) {
        InterestingTime test = new InterestingTime();
        System.out.println(test.countInterestingTime("00:00:00", "00:00:20"));
    }

    public int countInterestingTime(String s, String t) {
        int res = 0;
        Time start = new Time(s);
        Time end = new Time(t);
        if (start.equals(end)) {
            return isInteresting(start) ? 1 : 0;
        }
        Time endOfDay = new Time(23, 59, 59);
        while (start.isLessThanOrEqual(end)) {
            if (start.equals(endOfDay)) {
                break;
            }
            if (isInteresting(start)) {
                res++;
            }
            start.increment();
        }
        return res;
    }


    public boolean isInteresting(Time time) {
        Set<Integer> distinctNums = new HashSet<>();
        for (int i = 0; i < 3; ++i) {
            int num = time.time[i];
            int index = 0;
            while (num > 0) {
                distinctNums.add(num % 10);
                index++;
                num /= 10;
            }
            if (index <= 1) {
                distinctNums.add(0);
            }
        }
        return distinctNums.size() <= 2;
    }

    static class Time {
        int[] time;

        public Time(String timeString) {
            String[] arr = timeString.split(":");
            time = new int[3];
            time[0] = Integer.parseInt(arr[0]);
            time[1] = Integer.parseInt(arr[1]);
            time[2] = Integer.parseInt(arr[2]);
        }

        public Time(int h, int m, int s) {
            time = new int[3];
            time[0] = h;
            time[1] = m;
            time[2] = s;
        }

        public void increment() {
            time[2]++;
            if (time[2] == 60) {
                time[2] = 0;
                time[1]++;
            }
            if (time[1] == 60) {
                time[1] = 0;
                time[0]++;
            }
            if (time[0] == 24) {
                time[0] = 0;
            }
        }

        public boolean isLessThanOrEqual(Time other) {
            if (time[0] > other.time[0]) {
                return false;
            } else if (time[0] == other.time[0]) {
                if (time[1] > other.time[1]) {
                    return false;
                } else {
                    return time[2] <= other.time[2];
                }
            }
            return true;
        }

        public boolean equals(Time other) {
            return time[0] == other.time[0] && time[1] == other.time[1] && time[2] == other.time[2];
        }
    }
}

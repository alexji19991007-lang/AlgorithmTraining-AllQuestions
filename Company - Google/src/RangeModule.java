import java.util.TreeMap;

// LeetCode 715
public class RangeModule {
    // Key is the starting index and value is the ending index of the interval
    TreeMap<Integer, Integer> intervals;

    public RangeModule() {
        this.intervals = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        if (left >= right) {
            return;
        }
        // start          intervals.get(start)
        //   |-----------------x
        //          left                  right
        //            |---------------------|
        //                         end               intervals.get(end)
        //                           |-----------------x
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);
        if (start != null && intervals.get(start) >= left) {
            left = start;
        }
        if (end != null && intervals.get(end) > right) {
            right = intervals.get(end);
        }
        intervals.put(left, right);
        // Basically we now have an interval from start to intervals.get(end), and we want to remove any extra intervals in between
        intervals.subMap(left, false, right, true).clear();
    }

    public boolean queryRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        if (start == null) {
            return false;
        }
        return intervals.get(start) >= right;
    }

    public void removeRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);
        // We must do end removal first:
        // start = end                intervals.get(start)
        //     |-----------------------------x
        //         left        right
        //           |-----------x
        // The final intervals should be [start, left) and [right, intervals.get(start))
        // If we change the start first, then intervals.get(start) will be left, which is wrong
        if (end != null && intervals.get(end) > right) {
            intervals.put(right, intervals.get(end));
        }
        if (start != null && intervals.get(start) > left) {
            intervals.put(start, left);
        }
        intervals.subMap(left, true, right , false).clear();
    }
}

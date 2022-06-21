import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class LargestSetOfPointsWithPositiveSlope {
    public int largest(Point[] points) {
        if (points.length <= 1) {
            return points.length;
        }
        Arrays.sort(points, new MyComparator());
        int[] smallestEnding = new int[points.length + 1];
        smallestEnding[1] = points[0].y;
        int res = 1;
        for (int i = 1; i < points.length; ++i) {
            int index = find(smallestEnding, 0, res, points[i].y);
            if (index == res) {
                smallestEnding[++res] = points[i].y;
            } else {
                smallestEnding[index + 1] = points[i].y;
            }
        }
        return res > 1 ? res : 0;
    }

    public int find(int[] smallestEnding, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (smallestEnding[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (smallestEnding[right] < target) {
            return right;
        }
        if (smallestEnding[left] < target) {
            return left;
        }
        return 0;
    }

    static class MyComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            if (p1.x == p2.x) {
                return p1.y > p2.y ? -1 : 1;
            }
            return p1.x < p2.x ? -1 : 1;
        }
    }
}

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class LargestSetOfPointsWithPositiveSlope {
    public int largest(Point[] points) {
        if (points.length <= 1) {
            return 0;
        }
        // 根据x大小排序，之后我们要做的就是找到最长的increasing subsequence of y
        Arrays.sort(points, new MyComparator());
        int res = 0;
        int[] longest = new int[points.length];
        for (int i = 0; i < longest.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (points[j].y < points[i].y) {
                    longest[i] = Math.max(longest[i], longest[j]);
                }
            }
            longest[i]++;
            res = Math.max(res, longest[i]);
        }
        return res > 1 ? res : 0;
    }

    static class MyComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            // x不同，谁x小谁排在前面
            // x相同，谁y大谁排在前面(为了eliminate slope = infinity的情况，这种情况不算increasing)
            return p1.x != p2.x ? p1.x - p2.x : p2.y - p1.y;
        }
    }
}

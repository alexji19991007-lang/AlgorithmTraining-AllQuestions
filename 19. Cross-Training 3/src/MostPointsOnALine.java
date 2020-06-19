import java.util.HashMap;
import java.util.Map;

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class MostPointsOnALine {
    public int most(Point[] points) {
        int res = 0;
        for (int i = 0; i < points.length; ++i) {
            Point seed = points[i];
            int same = 1;
            int sameX = 0;
            int most = 0;
            Map<Double, Integer> count = new HashMap<>();
            for (int j = 0; j < points.length; ++j) {
                if (i == j) {
                    continue;
                }
                Point temp = points[j];
                if (temp.x == seed.x && temp.y == seed.y) {
                    // Handle points that overlap.
                    same++;
                } else if (temp.x == seed.x) {
                    // We cannot handle points with the same x with the logic in the else branch
                    // since denominator cannot be 0.
                    sameX++;
                } else {
                    // We can handle points with the same y using slope.
                    double slope = ((temp.y - seed.y) + 0.0) / (temp.x - seed.x);
                    count.put(slope, count.getOrDefault(slope, 0) + 1);
                    most = Math.max(most, count.get(slope));
                }
            }
            most = Math.max(most, sameX) + same;
            res = Math.max(most, res);
        }
        return res;
    }
}

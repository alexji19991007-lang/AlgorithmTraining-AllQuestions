import java.util.ArrayList;
import java.util.List;

// LeetCode 2013
public class DetectSquares {
    private int[][] pointCount;
    List<int[]> points;

    public DetectSquares() {
        this.pointCount = new int[1001][1001];
        this.points = new ArrayList<>();
    }

    public void add(int[] point) {
        pointCount[point[0]][point[1]] += 1;
        points.add(point);
    }

    public int count(int[] point) {
        int x1 = point[0], y1 = point[1], res = 0;
        for (int[] p3 : points) {
            int x3 = p3[0], y3 = p3[1];
            if (x1 != x3 && y1 != y3 && Math.abs(x1 - x3) == Math.abs(y1 - y3)) {
                res += pointCount[x1][y3] * pointCount[x3][y1];
            }
        }
        return res;
    }
}

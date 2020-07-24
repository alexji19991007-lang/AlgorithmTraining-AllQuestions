import java.util.ArrayList;
import java.util.List;

// LeetCode 986
public class F025_IntervalListIntersections {
    // TC: O(m + n)
    // SC: O(m + n)
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int maxLeft = Math.max(A[i][0], B[j][0]);
            int minRight = Math.min(A[i][1], B[j][1]);
            if (maxLeft <= minRight) {
                res.add(new int[]{maxLeft, minRight});
            }
            // remove the interval with a smaller right bound
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}

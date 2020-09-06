package RobinhoodOnsite;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix_Rectangle {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int col = c1; col <= c2; ++col) {
                res.add(matrix[r1][col]);
            }
            for (int row = r1 + 1; row <= r2; ++row) {
                res.add(matrix[row][c2]);
            }
            // if r1 == r2 && c1 == c2, the above two for loops would already finished the work
            // so don't make any repetitions
            if (r1 < r2 && c1 < c2) {
                for (int col = c2 - 1; col > c1; --col) {
                    res.add(matrix[r2][col]);
                }
                for (int row = r2; row > r1; --row) {
                    res.add(matrix[row][c1]);
                }
            }
            r1++;
            c1++;
            r2--;
            c2--;
        }
        return res;
    }
}

// LeetCode 498
public class F060_DiagonalTraverse {
    // TC: O(n * m)
    // SC: O(1)
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int n = matrix.length;
        int m = matrix[0].length;
        // The head would be the node directly below the tail of the previous diagonal. Unless the
        // tail lies in the last row of the matrix in which case the head would be the node right
        // next to the tail.

        // The head would be the node to the right of the tail of the previous diagonal. Unless the
        // tail lies in the last column of the matrix in which case the head would be the node
        // directly below the tail.
        int row = 0, col = 0;
        boolean up = true;
        int[] res = new int[m * n];
        int r = 0;
        while (row < n && col < m) {
            res[r++] = matrix[row][col];
            int newRow = row + (up ? -1 : 1);
            int newCol = col + (up ? 1 : -1);
            if (newRow < 0  || newRow == n || newCol < 0 || newCol == m) {
                if (up) {
                    row += (col == m - 1 ? 1 : 0);
                    col += (col < m - 1 ? 1 : 0);
                } else {
                    col += (row == n - 1 ? 1 : 0);
                    row += (row < n - 1 ? 1 : 0);
                }
                up = !up;
            } else {
                row = newRow;
                col = newCol;
            }
        }
        return res;
    }
}

// LeetCode 766
public class F062_ToeplitzMatrix {
    // TC: O(n * m)
    // SC: O(1)
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int r = 1; r < matrix.length; ++r) {
            for (int c = 1; c < matrix[0].length; ++c) {
                if (matrix[r][c] != matrix[r - 1][c - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

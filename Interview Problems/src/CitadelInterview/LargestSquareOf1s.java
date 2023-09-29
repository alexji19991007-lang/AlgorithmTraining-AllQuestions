package CitadelInterview;

public class LargestSquareOf1s {
    public int largest(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] largest = new int[row][col];
        int res = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (i == 0 || j == 0) {
                    largest[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    largest[i][j] = 1 + Math.min(largest[i - 1][j], Math.min(largest[i][j - 1], largest[i - 1][j - 1]));
                }
                res = Math.max(res, largest[i][j]);
            }
        }
        return res;
    }
}

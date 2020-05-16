public class Search2DMatrix_2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Start from bottom left corner
        int curRow = matrix.length - 1;
        int curCol = 0;
        while (curRow >= 0 && curCol < matrix[0].length) {
            if (matrix[curRow][curCol] == target) {
                return true;
            } else if (matrix[curRow][curCol] > target) {
                // if current number is greater than the target, no need to check the current row
                // and any row below
                curRow--;
            } else {
                // go right if current number is smaller than the target
                curCol++;
            }
        }
        return false;
    }
}

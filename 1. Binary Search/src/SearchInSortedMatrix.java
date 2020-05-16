public class SearchInSortedMatrix {
    public int[] search(int[][] matrix, int target) {
        int[] res = {-1, -1};
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int i = 0, j = row * col - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            // Mapping back
            int rowNum = mid / col;
            int colNum = mid % col;
            if (matrix[rowNum][colNum] == target) {
                res[0] = rowNum;
                res[1] = colNum;
                return res;
            } else if (matrix[rowNum][colNum] > target) {
                j = mid - 1; // Must + or - 1
            } else {
                i = mid + 1;
            }
        }
        return res;
    }
}

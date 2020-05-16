public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{}};
        System.out.println(miniSum(grid));
    }

    public static int miniSum(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        // We use one dimensional array to do the dp process
        int[] pathSum = new int[numCols];
        if (pathSum.length == 0) {
            return 0;
        }
        // pathSum[i] means the # of ways to get to this position in a particular row
        // which row is determined by the outer for-loop below
        pathSum[0] = grid[0][0];
        // initialize the first row
        for (int i = 1; i < numCols; ++i) {
            pathSum[i] = pathSum[i - 1] + grid[0][i];
        }
        // do the dp
        for (int i = 1; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                // if we are at the first column, just add grid[i][j] to pathSum[j], since there is
                // only one way to get here
                if (j == 0) {
                    pathSum[j] += grid[i][j];
                } else {
                    // if we are not at the first column, then for each position, we can either come
                    // from the top or come from the left, so we have to decide which way costs less.
                    pathSum[j] = grid[i][j] + Math.min(pathSum[j], pathSum[j - 1]);
                }
            }
        }
        // after doing the dp, the answer we want is the last element of pathSum array
        return pathSum[numCols - 1];
    }
}

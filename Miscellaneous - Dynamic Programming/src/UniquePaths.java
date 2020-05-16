public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] pathCount = new int[m][n];
        for (int i = 0; i < m; ++i) {
            pathCount[i][0] = 1;
        }
        for (int i = 0; i < n; ++i) {
            pathCount[0][i] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                pathCount[i][j] = pathCount[i - 1][j] + pathCount[i][j - 1];
            }
        }
        return pathCount[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 如果起点是obstacle，直接结束
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] path = new int[m][n];
        // Initialize the first row and first column
        for (int i = 0; i < m; ++i) {
            // if we encounter one obstacle in the first row or column, then the places after that
            // can never be reached.
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            path[i][0] = 1;
        }
        for (int i = 0; i < n; ++i) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            path[0][i] = 1;
        }
        // dp here
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                // if there is no obstacle in this place, calculate how many ways we can come here
                if (obstacleGrid[i][j] == 0) {
                    path[i][j] = path[i-1][j] + path[i][j-1];
                } else {
                    // otherwise, we cannot come here
                    path[i][j] = 0;
                }
            }
        }
        return path[m-1][n-1];
    }
}

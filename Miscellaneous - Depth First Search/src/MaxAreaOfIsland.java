public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int ans = 0;
        // Do depth first search
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                ans = Math.max(ans, islandArea(grid, visited, row, col));
            }
        }
        return ans;
    }

    public int islandArea(int[][] grid, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || visited[r][c] || grid[r][c] == 0) {
            return 0;
        }
        // every time we find a new piece of land that has not been discovered before, mark it as visited
        visited[r][c] = true;
        // add its area to our running answer and go for other DFS.
        return 1 + islandArea(grid, visited, r, c + 1) + islandArea(grid, visited, r + 1, c)
                + islandArea(grid, visited, r, c - 1) + islandArea(grid, visited, r - 1, c);
    }
}

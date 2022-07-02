// LeetCode 2128
public class RemoveAllOnesWithRowAndColumnFlips {
    // TC: O(n * m)
    // SC: O(1)
    public boolean removeOnes(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        // Firstly, look at row 0. If any cell has 1, flip that entire column.
        for (int c = 0; c < cols; ++c) {
            if (grid[0][c] == 1) {
                for (int r = 0; r < rows; ++r) {
                    grid[r][c] = 1 - grid[r][c];
                }
            }
        }
        // Secondly, checking the remaining rows to see if all the cells in one row have the same value
        for (int r = 1; r < rows; ++r) {
            for (int c = 1; c < cols; ++c) {
                if (grid[r][c] != grid[r][c - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

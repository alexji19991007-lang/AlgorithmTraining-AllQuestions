public class GameOfLife {
    public void gameOfLife(int[][] board) {
        // Neighbors array to find 8 neighboring cells for a given cell
        int[] neighbors = {0, 1, -1};
        int numRow = board.length;
        int numCol = board[0].length;
        for (int curRow = 0; curRow < numRow; ++curRow) {
            for (int curCol = 0; curCol < numCol; ++curCol) {
                int liveNeighbor = 0;
                // iterate through the 8 cells
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (neighbors[i] != 0 || neighbors[j] != 0) {
                            int r = curRow + neighbors[i];
                            int c = curCol + neighbors[j];
                            // 1. live to death == 1 to -1
                            // 2. death to live == 0 to 2
                            // 3. live to live == 1 to 1
                            // We have to increment liveNeighbor by 1 if case 1 & 3 occurs. i.e. abs(val) == 1
                            if (r < numRow && c < numCol && r >= 0 && c >= 0 && Math.abs(board[r][c]) == 1) {
                                liveNeighbor++;
                            }
                        }
                    }
                }
                // update the current position
                // 1. live to death == 1 to -1
                if (board[curRow][curCol] == 1 && (liveNeighbor < 2 || liveNeighbor > 3)) {
                    board[curRow][curCol] = -1;
                }
                // 2. death to live == 0 to 2
                if (board[curRow][curCol] == 0 && liveNeighbor == 3) {
                    board[curRow][curCol] = 2;
                }
            }
        }
        // Get the final representation for the newly updated board.
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }
}

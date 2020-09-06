package RobinhoodOnsite;

public class CandyCrush {
    public int[][] candyCrush(int[][] board) {
        boolean stable = false;
        while (!stable) {
            stable = doCrush(board);
        }
        return board;
    }

    public boolean doCrush(int[][] board) {
        int m = board.length, n = board[0].length;
        boolean stable = true;
        boolean[][] crushed = new boolean[m][n];
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c + 2 < n; ++c) {
                int v = board[r][c];
                if (v != 0 && v == board[r][c + 1] && v == board[r][c + 2]) {
                    crushed[r][c] = crushed[r][c + 1] = crushed[r][c + 2] = true;
                    stable = false;
                }
            }
        }
        for (int r = 0; r + 2 < m; ++r) {
            for (int c = 0; c < n; ++c) {
                int v = board[r][c];
                if (v != 0 && v == board[r + 1][c] && v == board[r + 2][c]) {
                    crushed[r][c] = crushed[r + 1][c] = crushed[r + 2][c] = true;
                    stable = false;
                }
            }
        }
        for (int c = 0; c < n; ++c) {
            int curRow = m - 1;
            for (int r = m - 1; r >= 0; --r) {
                if (!crushed[r][c]) {
                    board[curRow--][c] = board[r][c];
                }
            }
            while (curRow >= 0) {
                board[curRow--][c] = 0;
            }
        }
        return stable;
    }
}

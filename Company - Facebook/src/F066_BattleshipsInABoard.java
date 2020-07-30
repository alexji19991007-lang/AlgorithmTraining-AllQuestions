// LeetCode 419
public class F066_BattleshipsInABoard {
    // TC: O(n * m)
    // SC: O(1)
    public int countBattleships(char[][] board) {
        if (board == null || board[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == '.' || (i > 0 && board[i - 1][j] == 'X') || (j > 0 && board[i][j - 1] == 'X')) continue;
                count++;
            }
        }
        return count;
    }
}

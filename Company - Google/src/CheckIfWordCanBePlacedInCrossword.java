// LeetCode 2018
public class CheckIfWordCanBePlacedInCrossword {
    public static final int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // TC: O(M * N * L)
    // SC: O(1)
    public boolean placeWordInCrossword(char[][] board, String word) {
        int n = board.length, m = board[0].length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                // First check if it's a valid starting place
                if (board[i][j] == ' ' || board[i][j] == word.charAt(0)) {
                    for (int[] dir : DIRS) {
                        // Check the opposite direction (previous cell) to make sure it does not have any letter
                        if (inBound(board, i - dir[0], j - dir[1]) && board[i - dir[0]][j - dir[1]] != '#') {
                            continue;
                        }
                        int nextRow = i + dir[0], nextCol = j + dir[1];
                        int wordIndex = 1;
                        // Continue to check the remaining parts of the word
                        while (wordIndex < word.length() && inBound(board, nextRow, nextCol)) {
                            // If we encounter a '#' or the next cell already has other letters that mismatches the current letter,
                            // then this path is invalid.
                            if (board[nextRow][nextCol] == '#' || (board[nextRow][nextCol] != ' ' && board[nextRow][nextCol] != word.charAt(wordIndex))) {
                                break;
                            }
                            wordIndex++;
                            nextRow += dir[0];
                            nextCol += dir[1];
                        }
                        // If we have reached the end of the target word and the next cell is '#' or out of bound, return true
                        if (wordIndex == word.length() && (!inBound(board, nextRow, nextCol) || board[nextRow][nextCol] == '#')) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean inBound(char[][] board, int r, int c) {
        return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
    }
}

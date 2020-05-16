public class WordSearch {
    private boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (word.charAt(0) == board[i][j] && search(word, board, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(String word, char[][] board, int row, int col, int index) {
        if (index == word.length()) {
            return true;
        }
        if (row >= board.length || col >= board[0].length || row < 0 || col < 0 || board[row][col] != word.charAt(index) || visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        if (search(word, board, row - 1, col, index + 1) ||
                search(word, board, row, col + 1, index + 1) ||
                search(word, board, row + 1, col, index + 1) ||
                search(word, board, row, col - 1, index + 1)) {
            return true;
        }
        visited[row][col] = false;
        return false;
    }
}

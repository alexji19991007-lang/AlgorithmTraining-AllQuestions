public class TicTacToe {
    public static void main(String[] args) {
        TicTacToe test = new TicTacToe(3);
        test.printBoard();
        test.move(0, 0, 1);
        test.move(0, 1, 2);
        test.move(1, 1, 1);
        test.move(1, 2, 2);
        test.move(2, 2, 1);
    }

    // This is a visual representation of the board
    private final char[][] board;
    // This calculates the current status of a particular row / column
    private final int[] row;
    private final int[] col;
    // This calculates the current status of a particular diagonal
    private int diagonal;
    private int revDiagonal;
    private int totalSteps;

    public TicTacToe(int n) {
        this.board = new char[n][n];
        this.row = new int[n];
        this.col = new int[n];
        this.diagonal = 0;
        this.revDiagonal = 0;
        this.totalSteps = 0;
    }

    // First Player: X & 1
    // Second Player: O and -1
    /** Player {player} makes a move at ({row}, {col}).
     @param r The row of the board.
     @param c The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     -1: Invalid Move.
     0: No one wins (game not finished).
     1: Player 1 wins.
     2: Player 2 wins.
     3: Draw (game finished).
     */
    public int move(int r, int c, int player) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != '\u0000') {
            return -1;
        }
        System.out.println("Step " + totalSteps + " by player " + player + " at {" + r + ", " + c + "}");
        totalSteps++;
        int toAdd = player == 1 ? 1 : -1;
        char toPut = player == 1 ? 'X' : 'O';
        board[r][c] = toPut;
        row[r] += toAdd;
        col[c] += toAdd;
        if (r == c) {
            diagonal += toAdd;
        }
        if (c == (col.length - r - 1)) {
            revDiagonal += toAdd;
        }
        printBoard();
        int size = row.length;
        if (Math.abs(row[r]) == size || Math.abs(col[c]) == size || Math.abs(diagonal) == size || Math.abs(revDiagonal) == size) {
            System.out.println("The winner is player " + player);
            return player;
        }
        return totalSteps == size * size ? 3 : 0;
    }

    private void printBoard() {
        for (char[] array : board) {
            for (int i = 0; i < array.length - 1; ++i) {
                String str = array[i] == '\u0000' ? "[ ]" : "[" + array[i] + "]";
                System.out.print(str);
            }
            String last = array[array.length - 1] == '\u0000' ? "[ ]" : "[" + array[array.length - 1] + "]";
            System.out.println(last);
        }
        System.out.println();
    }
}

import java.util.*;

public class NQueensWithObstacles {
    // Note: Obstacle = integer 1.
    //       Empty Spot = integer 0.
    //       Queen = integer 9
    private final int[][] board;

    public NQueensWithObstacles(int size, List<Coordinate> obstacles) {
        this.board = new int[size][size];
        for (Coordinate obstacle : obstacles) {
            board[obstacle.row][obstacle.col] = 1;
        }
    }

    // Give the user a view of the board.
    public void printBoard() {
        for (int i = 0; i < board.length; ++i) {
            System.out.print(board[i][0]);
            for (int j = 1; j < board[i].length; ++j) {
                System.out.print(" " + board[i][j]);
            }
            System.out.print("\n");
        }
    }

    public List<List<Coordinate>> findAllSolutions() {
        List<Range> map = convertToRanges();
        // For Debug, print out all the ranges.
//        for (Range r : map) {
//            System.out.println(r.startRow + " " + r.startCol + " " + r.endCol);
//        }
        List<Coordinate> solution = new ArrayList<>();
        List<List<Coordinate>> res = new ArrayList<>();
        solveNQueens(map, 0, solution, res);
        return res;
    }

    private List<Range> convertToRanges() {
        List<Range> map = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            int startCol = 0;
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 1) {
                    map.add(new Range(i, startCol, j - 1));
                    startCol = j + 1;
                } else if (j == board.length - 1) {
                    map.add(new Range(i, startCol, j));
                }
            }
        }
        return map;
    }

    private void solveNQueens(List<Range> map, int index, List<Coordinate> solution, List<List<Coordinate>> res) {
        if (index == map.size()) {
            res.add(new ArrayList<>(solution));
            return;
        }
        Range curRange = map.get(index);
        int curRow = curRange.startRow;
        for (int i = curRange.startCol; i <= curRange.endCol; ++i) {
            if (isValid(curRow, i)) {
                // Place the queen.
                board[curRow][i] = 9;
                solution.add(new Coordinate(curRow, i));
                // Go to next recursion level, i.e. start putting queens at the next range.
                solveNQueens(map, index + 1, solution, res);
                // Remove the queen
                board[curRow][i] = 0;
                solution.remove(solution.size() - 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        // Since we are putting in only one queen at each range, no need to check for isValidRow.
        return isValidCol(row, col) && isValidDiagonal(row, col) && isValidRevDiagonal(row, col);
    }

    private boolean isValidCol(int r, int c) {
        // Check upward, we have 3 cases:
        // If we encounter an obstacle before a queen, stop checking, good for now.
        // If we encounter a queen before and obstacle, immediately return false.
        // If we reach the start of the column, good for now.
        for (int curRow = r - 1;  curRow >= 0; --curRow) {
            if (board[curRow][c] == 1) {
                break;
            } else if (board[curRow][c] == 9) {
                return false;
            }
        }
        // Check downward, same as check upward
        for (int curRow = r + 1; curRow < board.length; ++curRow) {
            if (board[curRow][c] == 1) {
                break;
            } else if (board[curRow][c] == 9) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidDiagonal(int r, int c) {
        // Check toward top-right corner, we have 3 cases:
        // If we encounter an obstacle before a queen, stop checking, good for now.
        // If we encounter a queen before and obstacle, immediately return false.
        // If we reach the start of the column, good for now.
        for (int offset = 1; r - offset >= 0 && c + offset < board[0].length; ++offset) {
            if (board[r - offset][c + offset] == 1) {
                break;
            } else if (board[r - offset][c + offset] == 9) {
                return false;
            }
        }
        // Check toward bottom-left corner, same as above:
        for (int offset = 1; r + offset < board.length && c - offset >= 0; ++offset) {
            if (board[r + offset][c - offset] == 1) {
                break;
            } else if (board[r + offset][c - offset] == 9) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidRevDiagonal(int r, int c) {
        // Check toward top-left corner, we have 3 cases:
        // If we encounter an obstacle before a queen, stop checking, good for now.
        // If we encounter a queen before and obstacle, immediately return false.
        // If we reach the start of the column, good for now.
        for (int offset = 1; r - offset >= 0 && c - offset >= 0; ++offset) {
            if (board[r - offset][c - offset] == 1) {
                break;
            } else if (board[r - offset][c - offset] == 9) {
                return false;
            }
        }
        // Check toward bottom-right corner, same as above:
        for (int offset = 1; r + offset < board.length && c + offset < board[0].length; ++offset) {
            if (board[r + offset][c + offset] == 1) {
                break;
            } else if (board[r + offset][c + offset] == 9) {
                return false;
            }
        }
        return true;
    }
}

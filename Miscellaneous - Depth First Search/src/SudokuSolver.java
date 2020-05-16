import java.util.Arrays;

// Name: Alex Ji
// VUnetID: jiy3
// Section: 001
// Email: yu.ji@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Date: , 2018
// Honor statement: I attest that I understand the honor code for this class and have neither given 
//                  nor received any unauthorized aid on this assignment.
// Program description: 
public class SudokuSolver {

    private static  final int ROW = 9;
    private static final int COL = 9;

    public static void main(String[] args) {
        char[][] input = {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'}, {'.','9','8','.','.','.','.','6','.'}
                , {'8','.','.','.','6','.','.','.','3'}, {'4','.','.','8','.','3','.','.','1'}
    , {'7','.','.','.','2','.','.','.','6'}, {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'}, {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(input);
        System.out.println(Arrays.asList(input).toString());
    }

    public static void solveSudoku(char[][] board) {
        int curRow = 0;
        int curCol = 0;
        solveHelper(board, curRow, curCol);
    }

    public static boolean solveHelper(char[][] mBoard, int curRow, int curCol) {
        if (curCol == 9) {
            curCol = 0;
            ++curRow;
        }
        if (curRow == 9) {
            return true;
        }
        if (mBoard[curRow][curCol] != '.') {
            return solveHelper(mBoard, curRow, curCol + 1);
        }
        for (int numToPut = 1; numToPut <= 9; ++numToPut) {
            char num = (char) (numToPut + '0');
            if (suitableForNow(mBoard, curRow, curCol, num)) {
                mBoard[curRow][curCol] = num;
                if (solveHelper(mBoard, curRow, curCol + 1)) {
                    return true;
                }
            }
        }
        mBoard[curRow][curCol] = '.';
        return false;
    }

    public static boolean existInRow(char[][] mBoard, int curRow, char num) {
        for (int curCol = 0; curCol < COL; ++curCol) {
            if (mBoard[curRow][curCol] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean existInCol(char[][] mBoard, int curCol, char num) {
        for (int curRow = 0; curRow < ROW; ++curRow) {
            if (mBoard[curRow][curCol] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean existInGrid(char[][] mBoard, int startRow, int startCol, char num) {
        for (int numRow = 0; numRow < 3; ++numRow) {
            for (int numCol = 0; numCol < 3; ++numCol) {
                int curRow = startRow + numRow;
                int curCol = startCol + numCol;
                if (mBoard[curRow][curCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean suitableForNow(char[][] mBoard, int curRow, int curCol, char num) {
        return !(existInRow(mBoard, curRow, num) || existInCol(mBoard, curCol, num)
                || existInGrid(mBoard, curRow - curRow % 3, curCol - curCol % 3, num));
    }
}

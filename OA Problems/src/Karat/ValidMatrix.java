package Karat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidMatrix {
    public static void main(String[] args) {
        ValidMatrix test = new ValidMatrix();
        int[][] matrix = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
                          {2, 3, 4, 5, 6, 7, 8, 9, 1},
                          {3, 4, 5, 6, 7, 8, 9, 1, 2},
                          {4, 5, 6, 7, 8, 9, 1, 2, 3},
                          {5, 6, 7, 8, 9, 1, 2, 3, 4},
                          {6, 7, 8, 9, 1, 2, 3, 4, 5},
                          {7, 8, 9, 1, 2, 3, 4, 5, 6},
                          {8, 9, 1, 2, 3, 4, 5, 6, 7},
                          {9, 1, 2, 3, 4, 5, 6, 7, 8}};
        int[][] matrix1 = {{1, 1, 1, 1},
                           {0, 1, 1, 1},
                           {0, 1, 0, 0},
                           {1, 1, 0, 1},
                           {0, 0, 1, 1}};
        int[][] rowIns = {{}, {1}, {1, 2}, {1}, {2}};
        int[][] colIns = {{2, 1}, {1}, {2}, {1}};
        System.out.println(test.isValidMatrix(matrix));
        System.out.println(test.isValidNonogram(matrix1, rowIns, colIns));
    }

    public boolean isValidMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        Map<Integer, Set<Integer>> colToColSets = new HashMap<>();
        Map<Integer, int[]> colMaxMin = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            Set<Integer> rowSet = new HashSet<>();
            int rowMin = Integer.MAX_VALUE, rowMax = Integer.MIN_VALUE;
            for (int j = 0; j < n; ++j) {
                if (rowSet.add(matrix[i][j])) {
                    rowMax = Math.max(rowMax, matrix[i][j]);
                    rowMin = Math.min(rowMin, matrix[i][j]);
                } else {
                    return false;
                }
                Set<Integer> colSet = colToColSets.getOrDefault(j, new HashSet<>());
                int[] maxAndMin = colMaxMin.getOrDefault(j, new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE});
                if (colSet.add(matrix[i][j])) {
                    maxAndMin[0] = Math.max(maxAndMin[0], matrix[i][j]);
                    maxAndMin[1] = Math.min(maxAndMin[1], matrix[i][j]);
                } else {
                    return false;
                }
                colToColSets.put(j, colSet);
                colMaxMin.put(j, maxAndMin);
                if (i == n - 1 && (maxAndMin[0] != n || maxAndMin[1] != 1)) {
                    return false;
                }
            }
            if (rowMin != 1 || rowMax != n) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidNonogram(int[][] matrix, int[][] rowIns, int[][] colIns) {
        int m = matrix.length, n = matrix[0].length;
        // ColInfo: colInfo[0] = # consecutive zeros, colInfo[1] = colInsPtr
        Map<Integer, int[]> colToColInfo = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            int numConsecutiveZeros = 0;
            int rowInsPtr = 0;
            for (int j = 0; j < n; ++j) {
                int[] colInfo = colToColInfo.getOrDefault(j, new int[2]);
                if (matrix[i][j] == 0) {
                    numConsecutiveZeros++;
                    colInfo[0]++;
                    colToColInfo.put(j, colInfo);
                    continue;
                }
                // matrix[i][j] == 1
                // check row
                if (j == n - 1 || numConsecutiveZeros > 0) {
                    if (j == n - 1 && rowInsPtr < rowIns[i].length - 1) {
                        return false;
                    }
                    if (numConsecutiveZeros > 0 && (rowInsPtr == rowIns[i].length || numConsecutiveZeros != rowIns[i][rowInsPtr])) {
                        return false;
                    }
                    if (numConsecutiveZeros > 0) {
                        rowInsPtr++;
                        numConsecutiveZeros = 0;
                    }
                }
                // check col
                if (i == m - 1 || colInfo[0] > 0) {
                    if (i == m - 1 && colInfo[1] < colIns[j].length - 1) {
                        return false;
                    }
                    if (colInfo[0] > 0 && (colInfo[1] == colIns[j].length || colInfo[0] != colIns[j][colInfo[1]])) {
                        return false;
                    }
                    if (colInfo[0] > 0) {
                        colInfo[1]++;
                        colInfo[0] = 0;
                    }
                }
                colToColInfo.put(j, colInfo);
            }
        }
        return true;
    }
}


//"""
//A nonogram is a logic puzzle, similar to a crossword, in which the player is given a blank grid and has to color it according to some instructions. Specifically, each cell can be either black or white, which we will represent as 0 for black and 1 for white.
//
//+------------+
//| 1  1  1  1 |
//| 0  1  1  1 |
//| 0  1  0  0 |
//| 1  1  0  1 |
//| 0  0  1  1 |
//+------------+
//
//For each row and column, the instructions give the lengths of contiguous runs of black (0) cells. For example, the instructions for one row of [ 2, 1 ] indicate that there must be a run of two black cells, followed later by another run of one black cell, and the rest of the row filled with white cells.
//
//These are valid solutions: [ 1, 0, 0, 1, 0 ] and [ 0, 0, 1, 1, 0 ] and also [ 0, 0, 1, 0, 1 ]
//This is not valid: [ 1, 0, 1, 0, 0 ] since the runs are not in the correct order.
//This is not valid: [ 1, 0, 0, 0, 1 ] since the two runs of 0s are not separated by 1s.
//
//Your job is to write a function to validate a possible solution against a set of instructions. Given a 2D matrix representing a player's solution; and instructions for each row along with additional instructions for each column; return True or False according to whether both sets of instructions match.
//
//Example instructions #1
//
//matrix1 = [[1,1,1,1],
//           [0,1,1,1],
//           [0,1,0,0],
//           [1,1,0,1],
//           [0,0,1,1]]
//rows1_1    =  [], [1], [1,2], [1], [2]
//columns1_1 =  [2,1], [1], [2], [1]
//validateNonogram(matrix1, rows1_1, columns1_1) => True
//
//Example solution matrix:
//matrix1 ->
//                                   row
//                +------------+     instructions
//                | 1  1  1  1 | <-- []
//                | 0  1  1  1 | <-- [1]
//                | 0  1  0  0 | <-- [1,2]
//                | 1  1  0  1 | <-- [1]
//                | 0  0  1  1 | <-- [2]
//                +------------+
//                  ^  ^  ^  ^
//                  |  |  |  |
//  column       [2,1] | [2] |
//  instructions      [1]   [1]
//
//
//Example instructions #2
//
//(same matrix as above)
//rows1_2    =  [], [], [1], [1], [1,1]
//columns1_2 =  [2], [1], [2], [1]
//validateNonogram(matrix1, rows1_2, columns1_2) => False
//
//The second and third rows and the first column do not match their respective instructions.
//
//Example instructions #3
//
//matrix2 = [
//[ 1, 1 ],
//[ 0, 0 ],
//[ 0, 0 ],
//[ 1, 0 ]
//]
//rows2_1    = [], [2], [2], [1]
//columns2_1 = [1, 1], [3]
//validateNonogram(matrix2, rows2_1, columns2_1) => False
//
//The black cells in the first column are not separated by white cells.
//
//n: number of rows in the matrix
//m: number of columns in the matrix
//"""
//
//        matrix1 = [
//        [1,1,1,1], # []
//        [0,1,1,1], # [1] -> a single run of _1_ zero (i.e.: "0")
//        [0,1,0,0], # [1, 2] -> first a run of _1_ zero, then a run of _2_ zeroes
//        [1,1,0,1], # [1]
//        [0,0,1,1], # [2]
//        ]
//
//        # True
//        rows1_1 = [[],[1],[1,2],[1],[2]]
//        columns1_1 = [[2,1],[1],[2],[1]]
//        # False
//        rows1_2 = [[],[],[1],[1],[1,1]]
//        columns1_2 = [[2],[1],[2],[1]]
//
//        matrix2 = [
//        [1,1],
//        [0,0],
//        [0,0],
//        [1,0]
//        ]
//        # False
//        rows2_1 = [[],[2],[2],[1]]
//        columns2_1 = [[1,1],[3]]

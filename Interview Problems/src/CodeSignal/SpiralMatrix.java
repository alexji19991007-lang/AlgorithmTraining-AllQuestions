package CodeSignal;

import java.util.Arrays;

public class SpiralMatrix {
    public static void main(String[] args) {
        SpiralMatrix test = new SpiralMatrix();
        int[][] matrix = test.spiralMatrix(5);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[][] spiralMatrix(int size) {
        int[][] res = new int[size][size];
        recursiveFill(res, 0, size, 1);
        return res;
    }

    public void recursiveFill(int[][] matrix, int offset, int size, int cur) {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            matrix[offset][offset] = cur;
            return;
        }
        for (int i = 0; i < size - 1; ++i) {
            matrix[offset][i + offset] = cur++;
        }
        for (int i = 0; i < size - 1; ++i) {
            matrix[i + offset][matrix.length - offset - 1] = cur++;
        }
        for (int i = size - 1; i > 0; --i) {
            matrix[matrix.length - offset - 1][i + offset] = cur++;
        }
        for (int i = size - 1; i > 0; --i) {
            matrix[i + offset][offset] = cur++;
        }
        recursiveFill(matrix, offset + 1, size - 2, cur);
    }
}

import java.util.Arrays;
import java.util.List;

public class Matrix implements BinaryMatrix {
    private final int[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int get(int row, int col) {
        return matrix[row][col];
    }

    @Override
    public List<Integer> dimensions() {
        return Arrays.asList(matrix.length, matrix[0].length);
    }
}

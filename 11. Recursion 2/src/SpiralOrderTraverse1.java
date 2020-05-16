import java.util.*;

public class SpiralOrderTraverse1 {
    public static void main(String[] args) {
        int[][] matrix = {{-85,56,37,48}, {-25,-78,-29,62}, {18,-60,-74,-84}, {90,44,5,1}};
        System.out.println(spiral(matrix).toString());
    }

    public static List<Integer> spiral(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        recursiveTraverse(matrix, 0, matrix.length, res);
        return res;
    }

    public static void recursiveTraverse(int[][] matrix, int offset, int size, List<Integer> res) {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            res.add(matrix[offset][offset]);
            return;
        }
        for (int i = 0; i < size - 1; ++i) {
            res.add(matrix[offset][i + offset]);
        }
        for (int i = 0; i < size - 1; ++i) {
            res.add(matrix[i + offset][matrix.length - offset - 1]);
        }
        for (int i = size - 1; i > 0; --i) {
            res.add(matrix[matrix.length - offset - 1][i + offset]);
        }
        for (int i = size - 1; i > 0; --i) {
            res.add(matrix[i + offset][offset]);
        }
        recursiveTraverse(matrix, offset + 1, size - 2, res);
    }
}

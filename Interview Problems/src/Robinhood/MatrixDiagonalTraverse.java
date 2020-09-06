package Robinhood;

import java.util.ArrayList;
import java.util.List;

public class MatrixDiagonalTraverse {
    public static void main(String[] args) {
        String[][] matrix = {{"d", "g", "i"},
                             {"b", "e", "h"},
                             {"a", "c", "f"}};
        List<String> list = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;
        int i = n - 1, j = 0;
        while (i >= 0 && j < m) {
            StringBuilder sb = new StringBuilder();
            int curRow = i, curCol = j;
            while (curRow < n && curCol < m) {
                sb.append(matrix[curRow++][curCol++]);
            }
            list.add(sb.toString());
            if (i == 0) {
                j++;
            } else {
                i--;
            }
        }
        System.out.println(list.toString());

        List<String> list1 = new ArrayList<>();
        i = n - 1;
        j = m - 1;
        while (i >= 0 && j >= 0) {
            StringBuilder sb = new StringBuilder();
            int curRow = i, curCol = j;
            while (curRow < n && curCol >= 0) {
                sb.append(matrix[curRow++][curCol--]);
            }
            list1.add(sb.toString());
            if (i == 0) {
                j--;
            } else {
                i--;
            }
        }
        System.out.println(list1.toString());
    }
}

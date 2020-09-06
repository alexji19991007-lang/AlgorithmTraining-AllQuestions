package Robinhood;

import java.util.*;

public class SortMatrixByOccurrences {
    public static void main(String[] args) {
        SortMatrixByOccurrences test = new SortMatrixByOccurrences();
        int[][] m = {{1, 4, -2}, {-2, 3, 4}, {3, 1, 3}};
        m = test.sortMatrixByOccurrences(m);
        for (int[] row : m) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[][] sortMatrixByOccurrences(int[][] m) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> helperList = new ArrayList<>();
        for (int[] row : m) {
            for (int i : row) {
                if (!map.containsKey(i)) {
                    map.put(i, 1);
                    helperList.add(i);
                } else {
                    map.put(i, map.get(i) + 1);
                }
            }
        }
        helperList.sort((i1, i2) -> {
            int count1 = map.get(i1);
            int count2 = map.get(i2);
            if (count1 == count2) {
                return i1 < i2 ? -1 : 1;
            }
            return count1 < count2 ? -1 : 1;
        });
        int numRow = m.length, numCol = m[0].length;
        int y = numRow - 1, x = numCol - 1;
        // row means current starting row, col means current starting col
        int row = numRow - 1, col = numCol - 1;
        int index = 0;
        while (x >= 0 && y >= 0) {
            int cur = helperList.get(index);
            int curCount = map.get(cur);
            m[y][x] = cur;
            map.put(cur, --curCount);
            index += curCount == 0 ? 1 : 0;

            if (y - 1 >= 0 && x + 1 < numCol) {
                y--;
                x++;
            } else if (col == 0) {
                // touched left border, move up
                row--;
                x = 0;
                y = row;
            } else {
                // haven't touched left border, move left
                col--;
                y = numRow - 1;
                x = col;
            }
        }
        return m;
    }
}

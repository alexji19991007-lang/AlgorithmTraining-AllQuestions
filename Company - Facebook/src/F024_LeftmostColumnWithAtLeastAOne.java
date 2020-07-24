import java.util.List;

// LeetCode 1428
public class F024_LeftmostColumnWithAtLeastAOne {
    public static void main(String[] args) {
        int[][] m = new int[][]{{0, 0}, {1, 1}};
        BinaryMatrix matrix = new Matrix(m);
        F024_LeftmostColumnWithAtLeastAOne test = new F024_LeftmostColumnWithAtLeastAOne();
        System.out.println(test.leftMostColumnWithOne(matrix));
    }

    // TC: O(nlogm) in the worst case, n is # rows, m is # cols
    // SC: O(1)
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int numRows = dimension.get(0);
        int numCols = dimension.get(1);

        int curRow = 0, curCol = numCols - 1;
        while (curRow < numRows && curCol >= 0) {
            if (binaryMatrix.get(curRow, curCol) != 0) {
                int left = 0;
                while (left < curCol - 1) {
                    int mid = left + (curCol - left) / 2;
                    if (binaryMatrix.get(curRow, mid) == 1) {
                        curCol = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                // 这里要变成left - 1 / curCol - 1的原因是，我们最后return时需要通过curCol是否仍为numCols - 1
                // 来判断答案是否存在。
                curCol = binaryMatrix.get(curRow, left) == 1 ? left - 1 : curCol - 1;
            }
            curRow++;
        }
        return curCol == numCols - 1 ? -1 : curCol + 1;
    }

    // Method 2: This works better if we are dealing with a stair-case like matrix
    // TC: O(m + n)
    // SC: O(1)
    public int leftMostColumnWithOne2(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int numRows = dimension.get(0);
        int numCols = dimension.get(1);

        int curRow = 0, curCol = numCols - 1;
        while (curRow < numRows && curCol >= 0) {
            if (binaryMatrix.get(curRow, curCol) == 0) {
                curRow++;
            } else {
                curCol--;
            }
        }
        return curCol == numCols - 1 ? -1 : curCol + 1;
    }
}

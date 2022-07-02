import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleOfOnes {
    public int largest(int[][] matrix) {
        int[] temp = new int[matrix[0].length];
        int maxArea = 0;
        for (int[] array : matrix) {
            for (int j = 0; j < array.length; ++j) {
                if (array[j] == 0) {
                    temp[j] = 0;
                } else {
                    temp[j] += array[j];
                }
            }
            maxArea = Math.max(maxArea, getMaxArea(temp));
        }
        return maxArea;
    }

    public int getMaxArea(int[] array) {
        int res = 0;
        Deque<Integer> mStack = new ArrayDeque<>();
        for (int i = 0; i <= array.length; ++i) {
            int curHeight = i == array.length ? 0 : array[i];
            // We will calculate all rectangles before index i that are higher than the current height;
            while (!mStack.isEmpty() && array[mStack.peekFirst()] >= curHeight) {
                int prevHeight = array[mStack.pollFirst()];
                int left = mStack.isEmpty() ? 0 : (mStack.peekFirst() + 1);
                res = Math.max(res, (i - left) * prevHeight);
            }
            mStack.offerFirst(i);
        }
        return res;
    }
}

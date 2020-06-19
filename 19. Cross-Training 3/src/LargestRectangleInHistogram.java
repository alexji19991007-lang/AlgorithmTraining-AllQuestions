import java.util.*;

public class LargestRectangleInHistogram {
    public int largest(int[] array) {
        int res = 0;
        // Stack保存的是当前的ascending subarray的indices
        Deque<Integer> mStack = new ArrayDeque<>();
        for (int i = 0; i <= array.length; ++i) {
            int cur = i == array.length ? 0 : array[i];
            while (!mStack.isEmpty() && array[mStack.peekFirst()] >= cur) {
                int height = array[mStack.pollFirst()];
                int left = mStack.isEmpty() ? 0 : mStack.peekFirst() + 1;
                res = Math.max(res, height * (i - left));
            }
            mStack.offerFirst(i);
        }
        return res;
    }
}

import java.util.*;

public class LargestRectangleInHistogram {
    public int largest(int[] array) {
        int res = 0;
        // Stack保存的是当前的ascending subarray的indices
        Deque<Integer> mStack = new ArrayDeque<>();
        for (int i = 0; i <= array.length; ++i) {
            // 当前bar的高度 -- 如果我们已经看完了array，还需要多看一次，最后的高度为0
            int cur = i == array.length ? 0 : array[i];
            // 计算每个在此之前和自己高度相同或者比自己高的长方形面积
            while (!mStack.isEmpty() && array[mStack.peekFirst()] >= cur) {
                // 因为这是一个ascending array，所以当前计算的长方形高度为stack top所对应的array element高度
                int height = array[mStack.pollFirst()];
                // 当前计算长方形的左边界为上一步poll完以后stack top的index + 1
                int left = mStack.isEmpty() ? 0 : mStack.peekFirst() + 1;
                res = Math.max(res, height * (i - left));
            }
            mStack.offerFirst(i);
        }
        return res;
    }
}

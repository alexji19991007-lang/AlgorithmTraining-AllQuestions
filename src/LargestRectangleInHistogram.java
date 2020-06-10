import java.util.*;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] array = {2, 1, 3, 4, 5, 2, 6};
        System.out.println(largest(array));
    }

    // index:   0, 1, 2, 3, 4, 5, 6, '7'
    // height:  2, 1, 3, 4, 5, 2, 6, '0'
    // stack:   0
    //          1
    //          1
    //          1 2
    //          1 2 3
    //          1 2 3 4 --> 从index 2到4，element都比index 5的2大所以一直pop，同时尝试更新res
    //          1 5
    //          1 5 6
    //          1 5 6 7 --> 虚拟的index 7，height是0，一直pop到空
    // 1和5的关系：从1后面的index一直到5之前的index，height is guaranteed to be larger than 2
    public static int largest(int[] array) {
        int res = 0;
        // Stack保存的是当前的ascending sub-array的indices
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

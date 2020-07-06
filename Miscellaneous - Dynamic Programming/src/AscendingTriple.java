// Determine if the given integer array has three indices such that i < j < k and a[i] < a[j] < a[k].
public class AscendingTriple {
    public boolean existIJK(int[] array) {
        if (array == null || array.length <= 2) {
            return false;
        }
        // 第一次遍历，我们确认每个数字左边是否有比他更小的数字
        boolean[] hasSmallerLeft = new boolean[array.length];
        int minLeft = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > minLeft) {
                hasSmallerLeft[i] = true;
            } else {
                minLeft = array[i];
            }
        }
        // 第二次遍历，我们确认每个数字右边是否有比他更大的数字
        int maxRight = array[array.length - 1];
        for (int i = array.length - 2; i >= 0; --i) {
            if (array[i] < maxRight) {
                // 如果这个数字右边有比他更大的数字，并且左边有比他更小的数字，return true
                if (hasSmallerLeft[i]) {
                    return true;
                }
            } else {
                maxRight = array[i];
            }
        }
        return false;
    }
}

public class MaxWaterTrapped1 {
    public int maxTrapped(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0, right = array.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(array[left], leftMax);
            rightMax = Math.max(array[right], rightMax);
            // If leftMax < rightMax, we know water can be safely stored in the current index. The highest wall on the right
            // will help us hold the water in the current index.
            // Notice that the water stored at the current index is >= 0 (because the current index could be leftMax).
            // So the actual amount of water that can be stored at the current index is determined by leftMax.
            if (leftMax < rightMax) {
                res += (leftMax - array[left]);
                left++;
            } else {
                res += (rightMax - array[right]);
                right--;
            }
        }
        return res;
    }
}

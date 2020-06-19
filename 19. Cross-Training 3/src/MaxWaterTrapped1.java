public class MaxWaterTrapped1 {
    public int maxTrapped(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0, right = array.length - 1;
        int leftMax = array[left], rightMax = array[right];
        while (left < right) {
            if (array[left] <= array[right]) {
                res += Math.max(0, leftMax - array[left]);
                leftMax = Math.max(leftMax, array[left]);
                left++;
            } else {
                res += Math.max(0, rightMax - array[right]);
                rightMax = Math.max(rightMax, array[right]);
                right--;
            }
        }
        return res;
    }
}

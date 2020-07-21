public class SmallerElementLargerThanTarget {
    public int smallestElementLargerThanTarget(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                // If the mid element is smaller than or equal to the target, we know this cannot
                // be the smallest element larger than the target. Thus, directly exclude
                left = mid + 1;
            } else {
                // Otherwise, the current mid could still be the correct answer
                right = mid;
            }
        }
        // Check left before right
        if (array[left] > target) {
            return left;
        } else if (array[right] > target) {
            return right;
        }
        return -1;
    }
}

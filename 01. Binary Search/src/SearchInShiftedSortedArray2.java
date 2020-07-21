public class SearchInShiftedSortedArray2 {
    public static int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) return mid;
            if (array[left] == target) return left;
            if (array[right] == target) return right;
            // Two cases for left = mid + 1: we can guarantee that target is not in the left range
            // Case 1: array[mid] < array[right] --> 从mid到right，array elements升序排列
            //         within(target, array[mid], array[right]) --> target以大小看右侧区间中
            // Case 2: array[left] < array[mid] --> 从left到mid，array elements升序排列
            //         !within(target, array[left], array[mid]) --> target以大小看不在左侧区间中
            if ((array[mid] < array[right] && within(target, array[mid], array[right]))
                    || (array[left] < array[mid] && !within(target, array[left], array[mid]))) {
                left = mid + 1;
            }
            // Two cases for right = mid - 1: we can guarantee that target is not in the right range
            // Two cases are exactly opposite to those for left = mid + 1;
            else if ((array[mid] < array[right] && !within(target, array[mid], array[right]))
                    || (array[left] < array[mid] && within(target, array[left], array[mid]))) {
                right = mid - 1;
            } else { // 3..3....3 --> 无法判断到底在左边还是在右边
                left = left + 1;
                right = right - 1;
            }
        }
        if (array[left] == target) return left;
        if (array[right] == target) return right;
        return -1;
    }

    public static boolean within(int x, int left, int right) {
        return x >= left && x <= right;
    }
}

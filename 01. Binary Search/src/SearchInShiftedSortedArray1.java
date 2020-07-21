public class SearchInShiftedSortedArray1 {
    public static void main(String[] args) {
        int[] array = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        System.out.println(search(array, 8));
        int[] arr = {2, 1};
        System.out.println(search(arr, 1));
        int[] arr2 = {1};
        System.out.println(search2(arr2, 0));
    }

    public static int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            }
            // Two cases for left = mid + 1:
            // Case 1: array[mid] < array[right] --> 从mid到right，array elements升序排列
            //         within(target, array[mid], array[right]) --> target以大小看右侧区间中
            // Case 2: array[left] < array[mid] --> 从left到mid，array elements升序排列
            //         !within(target, array[left], array[mid]) --> target以大小看不在左侧区间中
            if ((array[mid] < array[right] && within(target, array[mid], array[right]))
            || (array[left] < array[mid] && !within(target, array[left], array[mid]))) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (array[left] == target) return left;
        if (array[right] == target) return right;
        return -1;
    }

    public static boolean within(int x, int left, int right) {
        return x >= left && x <= right;
    }

    // ********************************************************************************************

    public static int search2(int[] array, int target) {
        int pivot = findPivot(array, 0, array.length - 1);
        if (pivot == -1) {
            return binarySearch(array, 0, array.length - 1, target);
        } else if (array[pivot] == target) {
            return pivot;
        } else if (array[0] > target) {
            return binarySearch(array, pivot + 1, array.length -1, target);
        } else {
            return binarySearch(array, 0, pivot - 1, target);
        }
    }

    public static int findPivot(int[] array, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid > left && array[mid] < array[mid - 1]) {
                return mid - 1;
            } else if (mid < right && array[mid] > array[mid + 1]) {
                return mid;
            } else if (array[left] >= array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] array, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1; // must + or - 1
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

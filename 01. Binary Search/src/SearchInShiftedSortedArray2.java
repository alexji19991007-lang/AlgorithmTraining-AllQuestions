public class SearchInShiftedSortedArray2 {
    public static void main(String[] args) {
//        int[] array = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] array = {3, 1, 1, 1, 1, 3};
        System.out.println(findPivot(array, 0, 5));
        System.out.println(search(array, 3));
    }

    public static int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int pivot = findPivot(array, 0, array.length - 1);
        if (pivot == -1) {
            return firstOccur(array, 0, array.length - 1, target);
        } else if (target < array[0]) {
            return firstOccur(array, pivot + 1, array.length - 1, target);
        }
        return firstOccur(array, 0, pivot, target);
    }

    public static int findPivot(int[] array, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid < right && array[mid] > array[mid + 1]) {
                return mid;
            } else if (mid > left && array[mid] < array[mid - 1]) {
                return mid - 1;
            } else if (array[left] == array[mid]) {
                left++;
            } else if (array[right] == array[mid]) {
                right--;
            } else if (array[left] >= array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int firstOccur(int[] array, int left, int right, int target) {
        while (left < right - 1) { // Terminate when left neighbors right
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                right = mid; // the current mid may still be the correct solution
            } else if (array[mid] < target) {
                left = mid + 1; // does not equal, directly exclude (we can also not + or - 1, which is less aggressive)
            } else {
                right = mid - 1;
            }
        }
        // Since we want to get first occurrence, check left first and then right
        if (array[left] == target) {
            return left;
        }
        if (array[right] == target) {
            return right;
        }
        return -1;
    }
}

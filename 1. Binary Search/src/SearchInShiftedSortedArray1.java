public class SearchInShiftedSortedArray1 {
    public static void main(String[] args) {
        int[] array = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        System.out.println(search(array, 8));
        int[] arr = {2, 1};
        System.out.println(search(arr, 1));
        int[] arr2 = {1};
        System.out.println(search(arr2, 0));
    }

    public static int search(int[] array, int target) {
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

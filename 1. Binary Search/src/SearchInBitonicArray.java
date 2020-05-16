import java.util.*;

public class SearchInBitonicArray {
    public static void main(String[] args) {
        int[] array = {9, 5, 3, 2, -4, -5};
        System.out.println(findPeak(array, 0, array.length - 1) + " " + search(array, -4));
        int[] array1 = {1, 2, 3, 4, 5, 6};
        System.out.println(findPeak(array1, 0, array1.length - 1) + " " + search(array1, 1));
        int[] array2 = {1, 2, 3, 4, 5, 4, 3, 2};
        System.out.println(findPeak(array2, 0, array2.length - 1) + " " + search(array2, 4));
        int[] array3 = {1, 2, 3, 4, 5, -1, -2, -3, -4};
        System.out.println(findPeak(array3, 0, array3.length - 1) + " " + search(array3, -2));
    }

    public static int search(int[] array, int target) {
        int peak = findPeak(array, 0, array.length - 1);
        if (target > array[peak]) {
            return -1;
        } else if (target == array[peak]) {
            return peak;
        } else {
            int temp = ascendingBinarySearch(array, 0, peak - 1, target);
            return temp != -1 ? temp : descendingBinarySearch(array, peak + 1, array.length - 1, target);
        }
    }

    public static int findPeak(int[] array, int left, int right) {
        // Terminate when left neighbors right
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            // If mid element is greater than its two neighboring elements, return mid index
            if (array[mid] > array[mid - 1] && array[mid] > array[mid + 1]) {
                return mid;
            } else if (array[mid] < array[mid - 1]) {
                right = mid;
                // It is possible that the array is monotonically increasing or decreasing,
                // so we don't exclude the element immediately
            } else {
                left = mid;
            }
        }
        return array[left] > array[right] ? left : right;
    }

    public static int ascendingBinarySearch(int[] array, int left, int right, int target) {
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

    public static int descendingBinarySearch(int[] array, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                right = mid - 1; // must + or - 1
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

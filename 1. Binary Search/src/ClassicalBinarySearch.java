public class ClassicalBinarySearch {
    // TC: O(logn)
    // SC: O(1)
    public int binarySearch(int[] array, int target) {
        if (array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        // Cannot be <
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

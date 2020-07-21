public class FirstAndLastOccurrence {
    public int firstOccur(int[] array, int target) {
        if (array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
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

    public int lastOccur(int[] array, int target) {
        if (array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                left = mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // Since we want to get last occurrence, check right and then left
        if (array[right] == target) {
            return right;
        }
        if (array[left] == target) {
            return left;
        }
        return -1;
    }
}

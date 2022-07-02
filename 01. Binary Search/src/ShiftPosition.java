public class ShiftPosition {
    public int shiftPosition(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid > left && array[mid] < array[mid - 1]) {
                // Here mid - 1 is the pivot, but the actual shifted position is mid;
                return mid;
            } else if (mid < right && array[mid] > array[mid + 1]) {
                // Here mid is the pivot, but the actual shifted position is mid + 1;
                return mid + 1;
            } else if (array[left] >= array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // We have no pivot, so it's not shifted
        return 0;
    }
}

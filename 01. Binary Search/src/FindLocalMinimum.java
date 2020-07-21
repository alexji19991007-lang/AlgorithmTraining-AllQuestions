import java.util.*;

public class FindLocalMinimum {
    public int localMinimum(int[] array) {
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid - 1] > array[mid] && array[mid] < array[mid + 1]) {
                return mid;
            } else if (array[mid - 1] < array[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return array[left] < array[right] ? left : right;
    }
}

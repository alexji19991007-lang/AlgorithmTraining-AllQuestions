import java.util.*;

public class SearchInsertPosition {
    public int searchInsert(int[] input, int target) {
        if (input == null || input.length == 0 || target <= input[0]) {
            return 0;
        }
        if (target > input[input.length - 1]) {
            return input.length;
        }
        int left = 0, right = input.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (input[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}

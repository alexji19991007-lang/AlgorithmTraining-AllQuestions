import java.util.ArrayList;
import java.util.List;

// LeetCode 658
public class F072_FindKClosestElements {
    // TC: O(logn + k)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr.length == 0 || k == 0) {
            return new ArrayList<>();
        }
        int closest = findClosest(arr, x);
        int left = closest - 1, right = closest + 1;
        int i = 1, n = arr.length;
        while (i < k && (left >= 0 || right < n)) {
            if (right < n && (left < 0 || Math.abs(arr[left] - x) > Math.abs(arr[right] - x))) {
                right++;
            } else {
                left--;
            }
            i++;
        }
        List<Integer> res = new ArrayList<>();
        for (int j = left + 1; j < right; ++j) {
            res.add(arr[j]);
        }
        return res;
    }

    public int findClosest(int[] array, int target) {
        if (array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (Math.abs(array[left] - target) <= Math.abs(array[right] - target)) {
            return left;
        }
        return right;
    }
}

import java.util.Arrays;

public class KClosestInSortedArray {
    public static void main(String[] args) {
        int[] arr = {1};
        int target = 0;
        int k = 1;
        System.out.println(Arrays.toString(kClosest(arr, target, k)));
    }

    public static int[] kClosest(int[] array, int target, int k) {
        int[] res = new int[k];
        if (array.length == 0 || k == 0) {
            return res;
        }
        int closest = findClosest(array, target);
        res[0] = array[closest];
        int left = closest - 1;
        int right = closest + 1;
        int n = array.length;
        int i = 1;
        while (i < k && (left >= 0 || right < n)) {
            if (right < n && (left < 0 || Math.abs(array[left] - target) > Math.abs(array[right] - target))) {
                res[i++] = array[right++];
            } else {
                res[i++] = array[left--];
            }
        }
        return res;
    }

    public static int findClosest(int[] array, int target) {
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
        if (Math.abs(array[left] - target) < Math.abs(array[right] - target)) {
            return left;
        }
        return right;
    }
}

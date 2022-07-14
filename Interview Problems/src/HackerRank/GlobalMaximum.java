package HackerRank;

import java.util.Arrays;

public class GlobalMaximum {
    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 9};
        GlobalMaximum test = new GlobalMaximum();
        System.out.println(test.findMaximum(arr, 3));
    }

    public int findMaximum(int[] arr, int m) {
        int res = 0;
        Arrays.sort(arr);
        // Left is the minimum possible difference.
        // Right is the maximum possible difference.
        int left = 0, right = arr[arr.length - 1] - arr[0];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (subsequenceExists(arr, mid, m)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public boolean subsequenceExists(int[] arr, int min, int m) {
        int size = 1, cur = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] - cur >= min) {
                size++;
                cur = arr[i];
                if (size == m) {
                    return true;
                }
            }
        }
        return false;
    }
}

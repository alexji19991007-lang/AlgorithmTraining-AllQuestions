package CodeSignal;

import java.util.Arrays;

public class CutTheRibbons {
    public static void main(String[] args) {
        CutTheRibbons test = new CutTheRibbons();
        int[] a = {1, 2, 3, 4, 9};
        System.out.println(test.cutTheRibbons(a, 5));
    }

    public int cutTheRibbons(int[] a, int k) {
        int totalLen = getSum(a);
        if (totalLen < k) {
            return -1;
        }
        Arrays.sort(a);
        int left = 0, right = getSum(a) / k;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (possible(a, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return possible(a, k, right) ? right : left;
    }

    private boolean possible(int[] a, int k, int len) {
        int pieces = 0;
        for (int i = a.length - 1; i >= 0; --i) {
            pieces += a[i] / len;
            if (pieces >= k) {
                break;
            }
        }
        return pieces >= k;
    }

    private int getSum(int[] a) {
        if (a == null || a.length == 0) return 0;
        int res = 0;
        for (int i : a) {
            res += i;
        }
        return res;
    }
}

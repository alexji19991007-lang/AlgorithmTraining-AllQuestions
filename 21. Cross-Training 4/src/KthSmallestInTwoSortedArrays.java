public class KthSmallestInTwoSortedArrays {
    public int kth(int[] a, int[] b, int k) {
        return kthHelper(a, 0, b, 0, k);
    }

    public int kthHelper(int[] a, int aLeft, int[] b, int bLeft, int k) {
        if (aLeft >= a.length) {
            return b[bLeft + k - 1];
        }
        if (bLeft >= b.length) {
            return a[aLeft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aLeft], b[bLeft]);
        }
        int aMid = aLeft + k / 2 - 1;
        int bMid = bLeft + k / 2 - 1;
        int aVal = aMid >= a.length ? Integer.MAX_VALUE : a[aMid];
        int bVal = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];
        if (aVal <= bVal) {
            return kthHelper(a, aMid + 1, b, bLeft, k - k / 2);
        }
        return kthHelper(a, aLeft, b, bMid + 1, k - k / 2);
    }
}

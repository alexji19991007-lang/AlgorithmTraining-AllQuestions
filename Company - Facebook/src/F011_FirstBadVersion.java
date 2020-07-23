// LeetCode 278
public class F011_FirstBadVersion {
    // TC: O(logn)
    // SC: O(1)
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isBadVersion(int n) {
        return n == 1;
    }
}

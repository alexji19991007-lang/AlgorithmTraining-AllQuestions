// LeetCode 896
public class F035_MonotonicArray {
    // TC: O(n)
    // SC: O(1)
    public boolean isMonotonic(int[] A) {
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 1; i < A.length; ++i) {
            if (A[i] > A[i - 1]) {
                decreasing = false;
            } else if (A[i] < A[i - 1]) {
                increasing = false;
            }
        }
        return increasing || decreasing;
    }
}

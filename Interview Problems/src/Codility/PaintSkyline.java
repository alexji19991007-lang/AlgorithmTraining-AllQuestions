package Codility;

public class PaintSkyline {
    public int paint(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < A.length - 1; ++i) {
            if (A[i + 1] < A[i]) {
                res += A[i] - A[i + 1];
            }
        }
        return res + A[A.length - 1];
    }
}

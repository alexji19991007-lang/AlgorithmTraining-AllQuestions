public class SquaresOfSortedArray {
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int right = 0;
        // we will first set our left and right pointers.
        // The left pointer should be pointing at the largest negative number, and keeps moving left
        // The right pointer should be pointing at the smallest positive number, and keeps moving right
        while (right < n && A[right] < 0) {
            right++;
        }
        int left = right - 1;
        int[] res = new int[n];
        int curPos = 0;
        while (left >= 0 && right < n) {
            // if the square of the number pointed to by the left ptr is larger than the one pointed
            // to by the right pointer, insert the square of the number pointed to by the right ptr
            // and vice versa
            if (A[left] * A[left] > A[right] * A[right]) {
                res[curPos] = A[right] * A[right];
                right++;
                curPos++;
            } else {
                res[curPos] = A[left] * A[left];
                left--;
                curPos++;
            }
        }
        // The above loop ends when either right or left ptr finishes scanning, so we process the
        // remaining things.
        while (left >= 0) {
            res[curPos] = A[left] * A[left];
            left--;
            curPos++;
        }
        while (right < n) {
            res[curPos] = A[right] * A[right];
            right++;
            curPos++;
        }
        return res;
    }
}

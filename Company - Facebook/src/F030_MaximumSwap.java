// LeetCode 670
public class F030_MaximumSwap {
    // TC: O(n)
    // SC: O(n)
    public int maximumSwap(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] lastOccur = new int[10];
        // Record the last occurrence of each digit in the array
        for (int i = 0; i < A.length; ++i) {
            lastOccur[A[i] - '0'] = i;
        }
        for (int i = 0; i < A.length; ++i) {
            for (int d = 9; d > A[i] - '0'; --d) {
                // Suppose we are looking at the first digit, if we want to make the number after
                // swap maximum, then all we have to do is just check if there exists a number as
                // large as possible after the first digit and we swap the first digit with the
                // last occurrence of the largest number
                // E.g. 1993 --> we know there exists 9 after 1, and the last occurrence of 9 is at
                //      index 2, so swap i with 9 at index 2, we get our final answer.
                if (lastOccur[d] > i) {
                    char temp = A[i];
                    A[i] = A[lastOccur[d]];
                    A[lastOccur[d]] = temp;
                    return Integer.parseInt(new String(A));
                }
            }
        }
        return num;
    }
}

package ArrayHopper;

// Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the
// maximum jump distance from that position (you can only jump towards the end of the array). Determine if you are able
// to reach the last index.

// Example:
// {1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array).
// {2, 1, 1, 0, 2}, we are not able to reach the end of array.
public class ArrayHopper {
    public static void main(String[] args) {
        int[] nums = {3, 0, 2, 3, 0, 0, 1};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] array) {
        int n = array.length;
        boolean[] jump = new boolean[n];
        jump[n - 1] = true;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i; j <= Math.min(array[i] + i, n - 1); ++j) {
                if (jump[j]) {
                    jump[i] = true;
                    break;
                }
            }
        }
        return jump[0];
    }
}

package ArrayHopper;

// Given an array of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the
// maximum jump distance from that position (you can only jump towards the end of the array). Determine the minimum
// number of jumps you need to jump out of the array.

// Example:
// {1, 3, 2, 0, 2}, the minimum number of jumps needed is 3 (jump to index 1 then to the end of array, then jump out).
// {3, 2, 1, 1, 0}, you are not able to jump out of array, return -1 in this case.
public class ArrayHopper3 {
    public static void main(String[] args) {
        ArrayHopper3 test = new ArrayHopper3();
        int[] array = {4, 2, 1, 3, 2, 1, 0, 4};
        System.out.println(test.minJump(array));
    }

    public int minJump(int[] array) {
        int[] M = new int[array.length];
        for (int i = array.length - 1; i >= 0; --i) {
            M[i] = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + array[i] && j < array.length + 1; ++j) {
                if (j >= array.length) {
                    M[i] = 1;
                    break;
                } else if (M[j] != Integer.MAX_VALUE && M[i] > M[j] + 1) {
                    M[i] = M[j] + 1;
                }
            }
        }
        return M[0] == Integer.MAX_VALUE ? -1 : M[0];
    }
}

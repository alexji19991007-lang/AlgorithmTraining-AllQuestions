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

    // Easier way of thinking: add another point at the end of the array, then we want to jump to the new end point
    //                         then it's the same as ArrayHopper2.
    public int minJump(int[] array) {
        int n = array.length;
        int[] M = new int[n + 1];
        M[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            M[i] = -1;
            for (int j = i + 1; j <= Math.min(array[i] + i, n); ++j) {
                if (array[i] + i >= j && M[j] != -1 && (M[i] == -1 || M[i] > M[j] + 1)) {
                    M[i] = M[j] + 1;
                }
            }
        }
        return M[0];
    }
}

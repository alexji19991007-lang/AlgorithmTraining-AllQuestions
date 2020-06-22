package ArrayHopper;

// Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum
// jump distance from index i (you can only jump towards the end of the array). Determine the minimum number of jumps you
// need to reach the end of array. If you can not reach the end of the array, return -1.

// Example:
// {3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array).
// {2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.
public class ArrayHopper2 {
    public int minJump(int[] array) {
        int[] jump = new int[array.length];
        jump[0] = 0;
        for (int i = 1; i < jump.length; ++i) {
            jump[i] = -1;
            for (int j = i - 1; j >= 0; --j) {
                if (array[j] + j >= i && jump[j] != -1 && (jump[i] == -1 || jump[i] > 1 + jump[j]))   {
                    jump[i] = jump[j] + 1;
                }
            }
        }
        return jump[jump.length - 1];
    }
}

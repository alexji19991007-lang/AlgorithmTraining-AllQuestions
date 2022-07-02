package ArrayHopper;

import java.util.*;

// Given an array A of non-negative integers, you are initially positioned at an arbitrary index of the array. A[i] means
// the maximum jump distance from that position (you can either jump left or jump right). Determine the minimum
// jumps you need to reach the right end of the array. Return -1 if you can not reach the right end of the array.

// The given array is not null and has length of at least 1.

// Example:
// {1, 3, 1, 2, 2}, 2 --> 2 (jump to index 1 then to the right end of array)
// {4, 0, 1, 0, 0}, 2 --> -1
public class ArrayHopper4 {
    public static void main(String[] args) {
        int[] array = {4, 0, 1, 0, 0};
        ArrayHopper4 test = new ArrayHopper4();
        System.out.println(test.minJump(array, 2));
    }

    public int minJump(int[] array, int index) {
        if (array.length == 0 || index == array.length - 1) {
            return 0;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> visited = new HashMap<>();
        queue.offer(index);
        visited.put(index, 0);
        while (!queue.isEmpty()) {
            int curIndex = queue.poll();
            int curStep = visited.get(curIndex);
            int maxJump = array[curIndex];
            for (int i = maxJump; i > 0; --i) {
                if (curIndex + i >= array.length - 1) {
                    return curStep + 1;
                }
                if (curIndex + i < array.length && !visited.containsKey(curIndex + i)) {
                    queue.offer(curIndex + i);
                    visited.put(curIndex + i, curStep + 1);
                }
                if (curIndex - i >= 0 && !visited.containsKey(curIndex - i)) {
                    queue.offer(curIndex - i);
                    visited.put(curIndex - i, curStep + 1);
                }
            }
        }
        return -1;
    }
}

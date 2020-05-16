import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] solution = new int[n];
        Arrays.fill(solution, -1);
        // This stack is used to hold the indices of elements in the array
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; ++i) {
            int curNum = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < curNum) {
                // if we found the next greater element for the element whose index is on the top
                // of the stack, pop it.
                solution[stack.pop()] = curNum;
            }
            // only push indices in the first round
            if (i < n) {
                stack.push(i);
            }
        }
        return solution;
    }
}

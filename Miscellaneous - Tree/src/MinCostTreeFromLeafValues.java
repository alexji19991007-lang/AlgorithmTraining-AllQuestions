import java.util.Stack;

public class MinCostTreeFromLeafValues {
    // build the tree in a bottom-up manner
    public int mctFromLeafValues(int[] A) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int x : A) {
            // This while loop eliminates the smaller number by the minimum cost
            while (!stack.isEmpty() && stack.peek() < x) {
                int midNum = stack.pop();
                res += midNum * Math.min(x, stack.peek());
                // midNum with the min of x and stack.peek() will contribute to a new node
                // we use Math.min here to make sure that we eliminate the number by min cost
            }
            stack.push(x);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}

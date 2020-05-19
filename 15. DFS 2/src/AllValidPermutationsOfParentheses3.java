import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {},
// subject to the priority restriction: {} higher than <> higher than ().
public class AllValidPermutationsOfParentheses3 {
    private static final char[] PS = new char[] {'(', ')', '<', '>', '{', '}'};

    public List<String> validParenthesesIII(int l, int m, int n) {
        int[] remain = new int[] {l, l, m, m, n, n};
        int targetLen = 2 * l + 2 * m + 2 * n;
        StringBuilder cur = new StringBuilder();
        // We will only put left parenthesis onto the stack.
        // Also, instead of putting the real parenthesis, we will instead put the index
        // of the parenthesis on to the stack, and thus enabling us to check priority more easily.
        Deque<Integer> mStack = new ArrayDeque<>();
        List<String> res = new ArrayList<>();
        helper(cur, mStack, remain, targetLen, res);
        return res;
    }

    public void helper(StringBuilder cur, Deque<Integer> mStack, int[] remain, int targetLen, List<String> res) {
        if (cur.length() == targetLen) {
            res.add(cur.toString());
            return;
        }
        for (int i = 0; i < remain.length; ++i) {
            // If this is a left parenthesis
            if (i % 2 == 0) {
                // We can add the left parenthesis if two conditions are met:
                // 1. We have more such left parenthesis left.
                // 2. The stack is empty or the left parenthesis on top of the stack has higher priority
                //   than the parenthesis we are currently putting.
                if (remain[i] > 0 && (mStack.isEmpty() || mStack.peekFirst() > i)) {
                    // If we still have this kind of left parenthesis left
                    // Select
                    cur.append(PS[i]);
                    mStack.offerFirst(i);
                    remain[i]--;
                    // Recurse down
                    helper(cur, mStack, remain, targetLen, res);
                    // Unselect
                    cur.deleteCharAt(cur.length() - 1);
                    mStack.pollFirst();
                    remain[i]++;
                }
            } else {
                // If this is a right parenthesis
                if (!mStack.isEmpty() && mStack.peekFirst() == i - 1) {
                    cur.append(PS[i]);
                    mStack.pollFirst(); // Get rid of the paired parentheses on the stack
                    remain[i]--;
                    helper(cur, mStack, remain, targetLen, res);
                    cur.deleteCharAt(cur.length() - 1);
                    mStack.offerFirst(i - 1); // Put the left parentheses of the removed pair back onto the stack
                    remain[i]++;
                }
            }
        }
    }
}

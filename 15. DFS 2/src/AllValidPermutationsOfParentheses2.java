import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.
// Assumption: l, m, n >= 0
//             l + m + n > 0
public class AllValidPermutationsOfParentheses2 {
    private static final char[] PS = new char[] {'(', ')', '<', '>', '{', '}'};

    public List<String> validParentheses(int l, int m, int n) {
        int[] remain = new int[] {l, l, m, m, n, n};
        int targetLen = 2 * l + 2 * m + 2 * n;
        StringBuilder cur = new StringBuilder();
        Deque<Character> mStack = new ArrayDeque<>();
        List<String> res = new ArrayList<>();
        helper(cur, mStack, remain, targetLen, res);
        return res;
    }

    public void helper(StringBuilder cur, Deque<Character> mStack, int[] remain, int targetLen, List<String> res) {
        if (cur.length() == targetLen) {
            res.add(cur.toString());
            return;
        }
        for (int i = 0; i < remain.length; ++i) {
            // If this is a left parenthesis
            if (i % 2 == 0) {
                if (remain[i] > 0) {
                    // If we still have this kind of left parenthesis left
                    // Select
                    cur.append(PS[i]);
                    mStack.offerFirst(PS[i]);
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
                if (!mStack.isEmpty() && mStack.peekFirst() == PS[i - 1]) {
                    cur.append(PS[i]);
                    // Get rid of the paired parentheses on the stack
                    mStack.pollFirst();
                    remain[i]--;
                    helper(cur, mStack, remain, targetLen, res);
                    cur.deleteCharAt(cur.length() - 1);
                    // Put the left parentheses of the removed pair back onto the stack
                    mStack.offerFirst(PS[i - 1]);
                    remain[i]++;
                }
            }
        }
    }
}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                right = left == 0 ? right + 1 : right;
                left = left > 0 ? left - 1 : left;
            }
        }
        // After the traverse, left and right each represents the # of mismatched left & right brackets
        Set<String> validExpressions = new HashSet<>();
        recurse(s, 0, 0, 0, left, right, new StringBuilder(), validExpressions);
        return new ArrayList<>(validExpressions);
    }

    public void recurse(String s, int index, int leftCount, int rightCount, int leftRem, int rightRem,
                        StringBuilder expression, Set<String> validExpressions) {
        if (index == s.length()) {
            // If we reached the end of the string, just check if the resulting expression is
            // valid or not
            if (leftRem == 0 && rightRem == 0) {
                validExpressions.add(expression.toString());
            }
            return;
        }
        char cur = s.charAt(index);
        int length = expression.length();
        // The discard case. Note that here we have our pruning condition:
        // We don't recurse if the remaining count for that parenthesis is == 0
        if ((cur == '(' && leftRem > 0) || (cur == ')' && rightRem > 0)) {
            int newLeftRem = cur == '(' ? leftRem - 1 : leftRem;
            int newRightRem = cur == ')' ? rightRem - 1 : rightRem;
            recurse(s, index + 1, leftCount, rightCount, newLeftRem, newRightRem, expression, validExpressions);
        }
        // Not discard case.
        expression.append(cur);
        if (cur != '(' && cur != ')') {
            // Simply recurse one step further if the current character is not a parenthesis.
            recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression, validExpressions);
        } else if (cur == '(') {
            // Consider an left bracket
            recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression, validExpressions);
        } else if (rightCount < leftCount) {
            // Consider an right bracket
            recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression, validExpressions);
        }
        // Notice: if cur == ')' and rightCount >= leftCount, then we know we have to remove this
        //         right bracket anyway
        expression.deleteCharAt(length);
    }
}

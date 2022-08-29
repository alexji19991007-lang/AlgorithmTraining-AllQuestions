public class MinimumMoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        // First pass -- left to right -- removing invalid right parentheses.
        StringBuilder firstPass = new StringBuilder();
        int leftCount = 0;
        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            if (cur == '(') {
                leftCount++;
            } else if (cur == ')') {
                if (leftCount == 0) {
                    continue;
                }
                leftCount--;
            }
            firstPass.append(cur);
        }
        // Second pass -- right to left -- removing invalid left parentheses.
        StringBuilder secondPass = new StringBuilder();
        int rightCount = 0;
        for (int i = firstPass.length() - 1; i >= 0; --i) {
            char cur = firstPass.charAt(i);
            if (cur == ')') {
                rightCount++;
            } else if (cur == '(') {
                if (rightCount == 0) {
                    continue;
                }
                rightCount--;
            }
            secondPass.append(cur);
        }
        return secondPass.reverse().toString();
    }

    public StringBuilder removeInvalidParentheses(CharSequence s, char p1, char p2) {
        StringBuilder sb = new StringBuilder();
        int p1Count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            if (cur == p1) {
                p1Count++;
            } else if (cur == p2) {
                if (p1Count == 0) {
                    continue;
                }
                p1Count--;
            }
            sb.append(cur);
        }
        return sb.reverse();
    }
}

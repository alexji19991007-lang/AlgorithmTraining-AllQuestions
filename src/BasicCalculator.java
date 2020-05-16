import java.util.Stack;

public class BasicCalculator {
    public static void main(String[] args) {
        String s = "3+2*2";
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        // use a stack to hold the current result
        Stack<Integer> stack = new Stack<>();
        // The initial sign is positive
        char sign = '+';
        int num = 0;
        for (int i = 0; i < n; ++i) {
            char cur = s.charAt(i);
            // If we encounter a space, and the space is not the last character, go on
            if (cur == ' ' && i != n - 1) {
                continue;
            }
            // If we encounter a digit, read that digit until we read the whole number
            if (Character.isDigit(cur)) {
                num = num * 10 + Character.getNumericValue(cur);
                continue;
            }
            // if we are reading an operator
            if (!Character.isDigit(cur) || i == n - 1) {
                // push our previous result on to the stack
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
                sign = cur;
                num = 0;
            }
        }
        // calculate the final solution
        int res = 0;
        for (int i : stack) {
            res += i;
        }
        return res;
    }
}

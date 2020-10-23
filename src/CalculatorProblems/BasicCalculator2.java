package CalculatorProblems;

import java.util.ArrayDeque;
import java.util.Deque;

// LeetCode 227
public class BasicCalculator2 {
    // TC: O(n)
    // SC: O(n)
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        Deque<Integer> mStack = new ArrayDeque<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            if (cur == ' ' && i != n - 1) {
                continue;
            }
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            }
            if (!Character.isDigit(cur) || i == n - 1) {
                switch (sign) {
                    case '+':
                        mStack.offerFirst(num);
                        break;
                    case '-':
                        mStack.offerFirst(-num);
                        break;
                    case '*':
                        mStack.offerFirst(mStack.pollFirst() * num);
                        break;
                    case '/':
                        mStack.offerFirst(mStack.pollFirst() / num);
                        break;
                }
                sign = cur;
                num = 0;
            }
        }
        int res = 0;
        while (!mStack.isEmpty()) {
            res += mStack.pollFirst();
        }
        return res;
    }
}

package CalculatorProblems;

import java.util.ArrayDeque;
import java.util.Deque;

// LeetCode 224
public class BasicCalculator {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int operand = 0, result = 0, sign = 1; // 1 for +, -1 for -
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                operand = 10 * operand + Character.getNumericValue(ch);
            } else {
                switch (ch) {
                    case '+':
                        result += sign * operand;
                        sign = 1;
                        operand = 0;
                        break;
                    case '-':
                        result += sign * operand;
                        sign = -1;
                        operand = 0;
                        break;
                    case '(':
                        // Push the result and sign on to the stack, for later
                        // We push the result first, then sign
                        stack.offerFirst(result);
                        stack.offerFirst(sign);
                        // Reset operand and result, as if new evaluation begins for the new sub-expression
                        sign = 1;
                        result = 0;
                        break;
                    case ')':
                        // Evaluate the expression to the left with result, sign and operand
                        result += sign * operand;
                        // Evaluate the expression to the left with result, sign and operand
                        result *= stack.pollFirst();
                        // (operand on stack) + (sign on stack * (result from parenthesis))
                        result += stack.pollFirst();
                        operand = 0;
                        break;
                }
            }
        }
        // If the last part is an operand, we need to add it to the result
        return result + (sign * operand);
    }
}

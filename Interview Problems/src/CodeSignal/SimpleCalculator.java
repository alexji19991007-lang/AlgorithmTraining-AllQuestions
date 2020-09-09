package CodeSignal;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleCalculator {
    public static void main(String[] args) {
        SimpleCalculator test = new SimpleCalculator();
        System.out.println(test.calculate("((1+2)+(((3*3)*(4*4))+(5*5)))"));
    }

    public int calculate(String expression) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (c == '(' || c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                numStack.offerFirst(Character.getNumericValue(c));
            } else if (c == '+' || c == '*') {
                operatorStack.offerFirst(c);
            } else {
                if (operatorStack.isEmpty()) {
                    continue;
                }
                char operator = operatorStack.pollFirst();
                int operand1 = numStack.pollFirst();
                int operand2 = numStack.pollFirst();
                if (operator == '+') {
                    numStack.offerFirst(operand1 + operand2);
                } else {
                    numStack.offerFirst(operand1 * operand2);
                }
            }
        }
//        while (!operatorStack.isEmpty()) {
//            char operator = operatorStack.pollFirst();
//            int operand1 = numStack.pollFirst();
//            int operand2 = numStack.pollFirst();
//            if (operator == '+') {
//                numStack.offerFirst(operand1 + operand2);
//            } else {
//                numStack.offerFirst(operand1 * operand2);
//            }
//        }
        return numStack.pollFirst();
    }
}

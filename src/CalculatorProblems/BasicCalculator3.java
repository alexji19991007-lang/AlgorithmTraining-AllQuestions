package CalculatorProblems;

import java.util.Deque;
import java.util.LinkedList;

// LeetCode 772
public class BasicCalculator3 {
    public static void main(String[] args) {
        BasicCalculator3 test = new BasicCalculator3();
        System.out.println(test.calculate("6-4/2"));
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> operands = new LinkedList<>();
        Deque<Character> operators = new LinkedList<>();
        int num = 0;
        int i = 0;
        while (i < s.length()) {
            char cur = s.charAt(i);
            if (cur == ' ') {
                i++;
                continue;
            }
            if (Character.isDigit(cur)) {
                // get the whole number
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                operands.offerFirst(num);
                num = 0; // reset to 0 for future calculation
            } else if (cur == '(') {
                operators.offerFirst(cur);
            } else if (cur == ')') {
                while (!operators.isEmpty() && operators.peekFirst() != '(') {
                    operands.offerFirst(operation(operands.pollFirst(), operators.pollFirst(), operands.pollFirst()));
                }
                operators.pollFirst(); // get rid of the last '('
            } else if (cur == '+' || cur == '-' || cur == '*' || cur == '/') {
                while (!operators.isEmpty() && hasPrecedence(operators.peekFirst(), cur)) {
                    operands.offerFirst(operation(operands.pollFirst(), operators.pollFirst(), operands.pollFirst()));
                }
                if (cur == '-') {
                    if (operands.isEmpty()) {
                        operands.offerFirst(0);
                    } else {
                        int index = i - 1;
                        while (index >= 0 && s.charAt(index) == ' ') {
                            index--;
                        }
                        if (s.charAt(index) == '(') {
                            operands.offerFirst(0);
                        }
                    }
                }
                operators.offerFirst(cur);
            }
            i++;
        }
        while (!operators.isEmpty()) {
            operands.offerFirst(operation(operands.pollFirst(), operators.pollFirst(), operands.pollFirst()));
        }
        return operands.pollFirst();
    }

    private int operation(int num2, char op, int num1) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return 0;
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op1 == '(' || op1 == ')') {
            return false;
        }
        return (op1 != '+' && op1 != '-') || (op2 != '*' && op2 != '/');
    }
}

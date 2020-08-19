package Karat;

import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Calculator test = new Calculator();
        System.out.println(test.basicCalculator2("2+((8+2)+(3-999))"));
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        System.out.println(test.expressionCalculator("a+b+(c+d)+1", map));
    }

    public int basicCalculator1(String expression) {
        int num = 0, sum = 0, sign = 1; // 1 for +, -1 for -
        char[] chars = expression.toCharArray();
        for (char cur : chars) {
            if (Character.isDigit(cur)) {
                num = num * 10 + Character.getNumericValue(cur);
            } else if (cur == '+' || cur == '-') {
                sum += sign * num;
                num = 0;
                sign = (cur == '+') ? 1 : -1;
            }
        }
        if (num != 0) sum += sign * num;
        return sum;
    }

    // LeetCode 224
    // TC: O(n)
    // SC: O(n)
    public int basicCalculator2(String s) {
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

    public String expressionCalculator(String input, Map<String, Integer> map) {
        if (input == null || input.length() == 0) {
            return "";
        }
        String simple = simplify(input, map);
        int i = 0;
        Deque<Cell> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int num = 0, sum = 0, sign = 1;
        while (i < simple.length()) {
            char cur = simple.charAt(i);
            if (Character.isDigit(cur)) {
                num = num * 10 + Character.getNumericValue(cur);
                i++;
            } else if (cur == '+' || cur == '-') {
                sum += num * sign;
                sign = cur == '+' ? 1 : -1;
                num = 0;
                i++;
            } else if (isChar(cur)) {
                if (sign == 1) {
                    sb.append('+');
                } else {
                    sb.append('-');
                }
                while (i < simple.length() && isChar(simple.charAt(i))) {
                    sb.append(simple.charAt(i));
                    i++;
                }
            } else if (cur == '(') {
                stack.offerFirst(new Cell(sum, sign, sb.toString()));
                sum = 0;
                sign = 1;
                sb.setLength(0);
                i++;
            } else {
                Cell prev = stack.pollFirst();
                sum += sign * num;
                int t = prev.sign * sum;
                num = 0;
                sum = prev.sum + t;
                StringBuilder next = new StringBuilder();
                next.append(prev.str);
                if (sb.length() > 0) {
                    next.append(update(prev.sign, sb.toString()));
                }
                sb = next;
                sign = 1;
                i++;
            }
        }
        if (num != 0) {
            sum += sign * num;
        }
        sb.append('+');
        sb.append(sum);
        return sb.toString();
    }

    public String simplify(String input, Map<String, Integer> map) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            char cur = input.charAt(i);
            if (isChar(cur)) {
                int fast = i;
                StringBuilder word = new StringBuilder();
                while (fast < input.length() && isChar(input.charAt(fast))) {
                    word.append(input.charAt(fast++));
                }
                i = fast;
                String s = word.toString();
                if (map.containsKey(s)) {
                    res.append(map.get(s));
                } else {
                    res.append(s);
                }
            } else {
                res.append(input.charAt(i++));
            }
        }
        return res.toString();
    }

    private String update(int sign, String s) {
        StringBuilder sb = new StringBuilder();
        if (sign == 1) {
            return s;
        }
        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            if (cur == '+') {
                sb.append('-');
            } else if (cur == '-') {
                sb.append('+');
            } else {
                sb.append(cur);
            }
        }
        return sb.toString();
    }

    private boolean isChar(char cur) {
        return cur >= 'a' && cur <= 'z';
    }

    static class Cell {
        int sum;
        int sign;
        String str;

        public Cell(int sum, int sign, String str) {
            this.sum = sum;
            this.sign = sign;
            this.str = str;
        }
    }

}

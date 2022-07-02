import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        Deque<Integer> operands = new ArrayDeque<>();
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        for (String s : tokens) {
            if (operators.contains(s)) {
                int right = operands.pollFirst();
                int left = operands.pollFirst();
                switch (s) {
                    case "+":
                        operands.offerFirst(left + right);
                        break;
                    case "-":
                        operands.offerFirst(left - right);
                        break;
                    case "*":
                        operands.offerFirst(left * right);
                        break;
                    case "/":
                        operands.offerFirst(left / right);
                        break;
                }
            } else {
                operands.offerFirst(Integer.valueOf(s));
            }
        }
        return operands.pollFirst();
    }
}

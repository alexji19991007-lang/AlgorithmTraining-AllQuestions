import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> operands = new Stack<>();
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        for (String item : tokens) {
            if (operators.contains(item)) {
                int right = operands.pop();
                int left = operands.pop();
                switch (item) {
                    case "+":
                        operands.push(left + right);
                        break;
                    case "-":
                        operands.push(left - right);
                        break;
                    case "*":
                        operands.push(left * right);
                        break;
                    case "/":
                        operands.push(left / right);
                        break;
                }
            } else {
                operands.push(Integer.valueOf(item));
            }
        }
        return operands.pop();
    }
}

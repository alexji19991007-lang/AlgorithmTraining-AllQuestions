import java.util.*;

public class VisitWithBreak {
    public static void main(String[] args) {
        String input = "ABC";
        System.out.println(visit(input).toString());
    }

    public static List<String> visit(String input) {
        List<String> res = new ArrayList<>();
        if (input.length() <= 1) {
            res.add(input);
            return res;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(input.charAt(0));
        visitHelper(res, 1, input, sb);
        return res;
    }

    public static void visitHelper(List<String> res, int index, String input, StringBuilder sb) {
        if (index == input.length()) {
            res.add(sb.toString());
            return;
        }
        // Not take a break
        sb.append(input.charAt(index));
        visitHelper(res, index + 1, input, sb);
        // Take a break
        sb.replace(sb.length() - 1, sb.length(), "x" + input.charAt(index));
        visitHelper(res, index + 1, input, sb);
        // 吐
        sb.delete(sb.length() - 2, sb.length());
    }
}

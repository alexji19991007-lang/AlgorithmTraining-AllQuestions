import java.util.*;

public class VisitWithBreak {
    public static void main(String[] args) {
        String input = "ABCDE";
        System.out.println(visit(input).toString());
    }

    public static List<String> visit(String input) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(input.charAt(0));
        visitHelper(input, 1, sb, res);
        return res;
    }

    public static void visitHelper(String input, int index, StringBuilder sb, List<String> res) {
        // Base case
        if (index == input.length()) {
            res.add(sb.toString());
            return;
        }
        // Case 1: Not take a break between two visits, i.e. AB...
        sb.append(input.charAt(index)); // 吃
        visitHelper(input, index + 1, sb, res);
        // Case 2: Take a break between two visits, i.e. AxB...
        sb.replace(sb.length() - 1, sb.length(), "x" + input.charAt(index)); // 吐 + 吃
        visitHelper(input, index + 1, sb, res);
        sb.delete(sb.length() - 2, sb.length());
    }
}

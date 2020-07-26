import java.util.ArrayList;
import java.util.List;

// LeetCode 282
public class F036_ExpressionAddOperators {
    // TC: O(n*3^n)
    // SC: O(n)
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num.length() == 0) {
            return res;
        }
        helper(res, new StringBuilder(), num, target, 0, 0, 0);
        return res;
    }

    public void helper(List<String> res, StringBuilder sb, String num, int target, int index, long eval, long multed) {
        if (index == num.length()) {
            if (eval == target) {
                res.add(sb.toString());
            }
            return;
        }
        for (int i = index; i < num.length(); ++i) {
            if (i != index && num.charAt(index) == '0') {
                break;
            }
            long cur = Long.parseLong(num.substring(index, i + 1));
            int prevLength = sb.length();
            if (index == 0) {
                sb.append(cur);
                helper(res, sb, num, target, i + 1, eval + cur, cur);
            } else {
                sb.append("+").append(cur);
                helper(res, sb, num, target, i + 1, eval + cur, cur);
                sb.setCharAt(prevLength, '-');
                helper(res, sb, num, target, i + 1, eval - cur, -cur);
                sb.setCharAt(prevLength, '*');
                helper(res, sb, num, target, i + 1, eval - multed + multed * cur, multed * cur);
            }
            sb.delete(prevLength, sb.length());
        }
    }
}

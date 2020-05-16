import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        String solution = "";
        findPermutation(n, 0, 0, solution, res);
        return res;
    }

    public void findPermutation(int n, int left, int right, String solution, List<String> res) {
        if (left + right == 2 * n) {
            res.add(solution);
        }
        // left < n, we can still put more left brackets
        if (left < n) {
            findPermutation(n, left + 1, right, solution + "(", res);
        }
        if (left > right) {
            findPermutation(n, left, right + 1, solution + ")", res);
        }
    }
}

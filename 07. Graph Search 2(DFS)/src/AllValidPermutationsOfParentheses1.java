import java.util.*;

// TC: O(2^(2n) * n)
// SC: O(n)

public class AllValidPermutationsOfParentheses1 {
    public List<String> validParentheses(int n) {
        List<String> res = new ArrayList<>();
        char[] solution = new char[n * 2];
        findPermutation(n, 0, 0, 0, solution, res);
        return res;
    }

    public void findPermutation(int n, int index, int left, int right, char[] solution, List<String> res) {
        if (index == 2 * n) {
            res.add(new String(solution));
            return;
        }
        if (left < n) {
            solution[index] = '(';
            findPermutation(n, index + 1, left + 1, right, solution, res);
        }
        if (right < left) {
            solution[index] = ')'; // 这里直接把我们在访问left child时候加的东西给overwrite了
            findPermutation(n, index + 1, left, right + 1, solution, res);
        }
    }
}

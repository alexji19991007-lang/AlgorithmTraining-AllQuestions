package Citrix;

import java.util.ArrayList;
import java.util.List;

// LeetCode 22
// TC: O(2^(2n) * n)
// SC: O(n)
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
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
            solution[index] = ')';
            findPermutation(n, index + 1, left, right + 1, solution, res);
        }
    }
}

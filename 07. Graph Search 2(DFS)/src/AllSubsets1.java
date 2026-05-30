import java.util.*;

// TC: O(2^n * n)
// SC: O(n)

public class AllSubsets1 {
    public List<String> subSets(String set) {
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        StringBuilder solution = new StringBuilder();
        findSubsets(set, 0, solution, res);
        return res;
    }

    public void findSubsets(String set, int index, StringBuilder solution, List<String> res) {
        if (index == set.length()) {
            res.add(solution.toString());
            return;
        }
        solution.append(set.charAt(index)); // Select the current character
        findSubsets(set, index + 1, solution, res); // Go to next level
        solution.deleteCharAt(solution.length() - 1); // Un-select the current character --> backtrack!!
        findSubsets(set, index + 1, solution, res); // Go to next level
    }
}

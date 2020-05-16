import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum1 {
    public static void main(String[] args) {
        List<List<Integer>> res;
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        res = combinationSum(candidates, target);
        System.out.println(res.toString());
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> temp = new ArrayList<>();
        backtrack(res, temp, candidates, target, 0);
        return res;
    }

    public static void backtrack(List<List<Integer>> res, List<Integer> temp, int[] candidates, int target, int index) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < candidates.length; ++i) {
            // we need this part if duplicate members are not allowed
//            if (i > index && candidates[i] == candidates[i - 1]) {
//                continue;
//            }
            temp.add(candidates[i]);
            // the last parameter should be i + 1 if duplicates are not allowed
            backtrack(res, temp, candidates, target - candidates[i], i); // duplicates allowed
            temp.remove(temp.size() - 1);
        }
    }
}

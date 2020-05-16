import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        ArrayList<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        permutationHelper(res, numsList, 0);
        return res;
    }

    private void permutationHelper(List<List<Integer>> res, ArrayList<Integer> nums, int index) {
        if (index == nums.size() - 1) {
            res.add(new ArrayList<>(nums));
        }
        for (int i = index; i < nums.size(); ++i) {
            // all numbers before index are fixed, so all we do is to move numbers after index to
            // the current position
            Collections.swap(nums, index, i);
            permutationHelper(res, nums, index + 1);
            Collections.swap(nums, index, i);
        }
    }
}

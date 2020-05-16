import java.util.ArrayList;
import java.util.List;

public class Subset {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        findSubset(nums, 0, temp, res);
        return res;
    }

    public void findSubset(int[] nums, int index, List<Integer> temp, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[index]); // DO: put the character at the current index into temp
        findSubset(nums, index + 1, temp, res); // go to next level
        temp.remove(temp.size() - 1); // UNDO: not choose the character at the current index
        findSubset(nums, index + 1, temp, res); // go to next level
    }
}

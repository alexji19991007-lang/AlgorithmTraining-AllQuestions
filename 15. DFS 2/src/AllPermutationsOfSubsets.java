import java.util.ArrayList;
import java.util.List;

// Given a string with no duplicate characters,return a list
// with all permutations of the string and all its subsets.
public class AllPermutationsOfSubsets {
    public List<String> allPermutationsOfSubsets(String set) {
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        char[] array = set.toCharArray();
        helper(array, 0, res);
        return res;
    }

    public void helper(char[] array, int index, List<String> res) {
        // Every state in the recursion tree is a valid result.
        res.add(new String(array, 0, index));
        for (int i = index; i < array.length; ++i) {
            swap(array, index, i);
            helper(array, index + 1, res);
            swap(array, index, i);
        }
    }

    public void swap(char[] input, int left, int right) {
        char temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }
}

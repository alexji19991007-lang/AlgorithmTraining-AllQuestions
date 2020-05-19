import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given a set of characters represented by a String, return a list containing all subsets
// of the characters whose size is K. Notice that each subset returned will be sorted for deduplication.
public class AllSubsetsWithDuplicates2 {
    public List<String> subSetsIIOfSizeK(String set, int k) {
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        char[] array = set.toCharArray();
        Arrays.sort(array);
        helper(array, k, new StringBuilder(), 0, res);
        return res;
    }

    public void helper(char[] array, int k, StringBuilder sb, int index, List<String> res) {
        if (sb.length() == k) {
            res.add(sb.toString());
            return;
        }
        if (index == array.length) {
            return;
        }
        sb.append(array[index]);
        helper(array, k, sb, index + 1, res); // Select the current character
        sb.deleteCharAt(sb.length() - 1); // Delete
        // Skip all consecutive duplicating elements
        while (index < array.length - 1 && array[index] == array[index + 1]) {
            index++;
        }
        helper(array, k, sb, index + 1, res);
    }
}

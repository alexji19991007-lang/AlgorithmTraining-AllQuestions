import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Assumption: Has duplicates. The original string is not sorted.
public class AllSubsets2 {
    public List<String> subSets(String set) {
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        char[] array = set.toCharArray();
        Arrays.sort(array);
        helper(array, 0, new StringBuilder(), res);
        return res;
    }

    public void helper(char[] array, int index, StringBuilder sb, List<String> res) {
        if (index == array.length) {
            res.add(sb.toString());
            return;
        }
        // Case 1: choose array[index].
        sb.append(array[index]);
        helper(array, index + 1, sb, res);
        sb.deleteCharAt(sb.length() - 1);
        // Skip all duplicates.
        while (index < array.length - 1 && array[index] == array[index + 1]) {
            index++;
        }
        // Case 2: not choose array[index].
        sb.append(array[index]);
    }
}

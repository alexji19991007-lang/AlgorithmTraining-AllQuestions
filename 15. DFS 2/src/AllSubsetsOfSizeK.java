import java.util.ArrayList;
import java.util.List;

// Assumption: No duplicates.
public class AllSubsetsOfSizeK {
    public List<String> subSetsOfSizeK(String set, int k) {
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        char[] array = set.toCharArray();
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
        helper(array, k, sb, index + 1, res);
        sb.deleteCharAt(sb.length() - 1);
        helper(array, k, sb, index + 1, res);
    }
}

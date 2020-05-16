import java.util.ArrayList;
import java.util.List;

public class SubsetsOfSizeK {
    public List<String> subSetsOfSizeK(String set, int k) {
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        char[] array = set.toCharArray();
        StringBuilder sb = new StringBuilder();
        helper(array, k, sb, 0, res);
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
        helper(array, k, sb.append(array[index]), index + 1, res); // Select the current character
        sb.deleteCharAt(sb.length() - 1); // Delete
        helper(array, k, sb, index + 1, res); // Not select the current character
    }
}

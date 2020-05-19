import java.util.ArrayList;
import java.util.List;

public class CombinationsForTelephonePads1 {
    public String[] combinations(int number) {
        List<String> res = new ArrayList<>();
        String[] numToChar = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        helper(Integer.toString(number).toCharArray(), numToChar, 0, new StringBuilder(), res);
        String[] ans = new String[res.size()];
        return res.toArray(ans);
    }

    public void helper(char[] number, String[] numToChar, int level, StringBuilder sb, List<String> res) {
        if (level == number.length) {
            res.add(sb.toString());
            return;
        }
        char[] chars = numToChar[number[level] - '0'].toCharArray();
        if (chars.length == 0) {
            helper(number, numToChar, level + 1, sb, res);
        } else {
            for (int i = 0; i < chars.length; ++i) {
                sb.append(chars[i]);
                helper(number, numToChar, level + 1, sb, res);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}

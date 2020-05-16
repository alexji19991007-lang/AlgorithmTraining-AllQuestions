import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNum {
    private static final Map<Character, String[]> mappings = new HashMap<>();{
        mappings.put('2', new String[] {"a", "b", "c"});
        mappings.put('3', new String[] {"d", "e", "f"});
        mappings.put('4', new String[] {"g", "h", "i"});
        mappings.put('5', new String[] {"j", "k", "l"});
        mappings.put('6', new String[] {"m", "n", "o"});
        mappings.put('7', new String[] {"p", "q", "r", "s"});
        mappings.put('8', new String[] {"t", "u", "v"});
        mappings.put('9', new String[] {"w", "x", "y", "z"});
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        backtrack(result, "", digits, 0);
        return result;
    }

    private void backtrack(List<String> result, String cur, String digits, int index) {
        if (cur.length() == digits.length()) {
            result.add(cur);
            return;
        }
        // try every letter in the current button
        for (String j : mappings.get(digits.charAt(index))) {
            cur += j; // do
            backtrack(result, cur, digits, index + 1); // go to the next level
            cur = cur.substring(0, cur.length() - 1); // undo
        }
    }
}

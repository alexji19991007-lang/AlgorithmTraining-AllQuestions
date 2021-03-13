import java.util.*;

// TC: O(n! * n)
// SC: O(n^2)

public class AllPermutations2 {
    public List<String> permutations(String input) {
        List<String> res = new ArrayList<>();
        if (input == null) {
            return res;
        }
        if (input.length() <= 1) {
            res.add(input);
            return res;
        }
        char[] charList = input.toCharArray();
        helper(res, 0, charList);
        return res;
    }

    public void helper(List<String> res, int index, char[] charList) {
        if (index == charList.length) {
            res.add(new String(charList));
            return;
        }
        Set<Character> used = new HashSet<>(); // 这个HashSet仅对本层有效
        for (int i = index; i < charList.length; ++i) {
            if (!used.contains(charList[i])) { // 当前字母若已经swap过就不再swap了
                used.add(charList[i]);
                swap(charList, index, i);
                helper(res, index + 1, charList);
                swap(charList, index, i);
            }
        }
    }
    // Time Complexity: O(n * n!)
    // Space Complexity: O(n^2) --> due to one hash set on each level

    public void swap(char[] input, int left, int right) {
        char temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }
}

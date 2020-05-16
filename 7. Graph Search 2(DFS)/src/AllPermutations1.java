import java.util.*;

public class AllPermutations1 {
    public List<String> permutations(String set) {
        List<String> res = new ArrayList<>();
        if (set.length() == 0) {
            res.add("");
            return res;
        }
        char[] charList = set.toCharArray();
        findPermutations(charList, 0, res);
        return res;
    }

    public void findPermutations(char[] charList, int index, List<String> res) {
        if (index == charList.length) {
            res.add(new String(charList));
            return;
        }
        for (int i = index; i < charList.length; ++i) {
            swap(charList, index, i);
            findPermutations(charList, index + 1, res);
            swap(charList, index, i);
        }
    }

    public void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

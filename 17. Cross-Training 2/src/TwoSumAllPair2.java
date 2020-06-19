import java.util.*;

public class TwoSumAllPair2 {
    public List<List<Integer>> allPairs(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            int count = map.getOrDefault(num, 0);
            if (target == num * 2 && count == 1) {
                res.add(Arrays.asList(num, num));
            } else if (map.containsKey(target - num) && count == 0) {
                // If count > 0, we have already found such a pair before.
                res.add(Arrays.asList(target - num, num));
            }
            map.put(num, count + 1);
        }
        return res;
    }
}

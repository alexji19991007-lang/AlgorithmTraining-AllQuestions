import java.util.*;

public class TwoSumAllPair1 {
    public List<List<Integer>> allPairs(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // We use a map to match each number with a list of indices where they appear.
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.length; ++i) {
            // 假设target是5，当前数字（index i）是2，我们只要看一下前面是否出现过3
            // 若前面有3，则所有（j, i）均为正确答案，其中j必须在i以前，并且array[j] == 3
            List<Integer> indices = map.getOrDefault(target - array[i], null);
            if (indices != null) {
                for (int j : indices) {
                    res.add(Arrays.asList(j, i));
                }
            }
            // Update the map.
            if (!map.containsKey(array[i])) {
                map.put(array[i], new ArrayList<>());
            }
            map.get(array[i]).add(i);
        }
        return res;
    }

}

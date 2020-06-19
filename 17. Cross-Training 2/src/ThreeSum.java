import java.util.*;

public class ThreeSum {
    public List<List<Integer>> allTriples(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(array);
        // We want to find i < j < k, such that array[i] + array[j] + array[k] = target
        for (int i = 0; i < array.length - 2; ++i) {
            // Skip duplicates. E.g. if we have 2, 2, 2, we only consider the first '2'.
            if (i > 0 && array[i] == array[i - 1]) {
                continue;
            }
            // 确定i的位置，j，k用two pointers相向而行的方法
            int j = i + 1, k = array.length - 1;
            while (j < k) {
                int sum = array[i] + array[j] + array[k];
                if (sum == target) {
                    res.add(Arrays.asList(array[i], array[j], array[k]));
                    j++;
                    // Skip all duplicate array[j] as well
                    while (j < k && array[j] == array[j  - 1]) {
                        j++;
                    }
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }

}

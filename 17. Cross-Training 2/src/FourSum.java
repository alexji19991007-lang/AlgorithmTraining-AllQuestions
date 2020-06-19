import java.util.*;

public class FourSum {
    static class Pair {
        int left;
        int right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public boolean exist(int[] array, int target) {
        Map<Integer, Pair> map = new HashMap<>();
        for (int i = 1; i < array.length; ++i) {
            for (int j = 0; j < i; ++j) {
                int pairSum = array[i] + array[j];
                // 假设四个数字a, b, c, d为最终答案
                // 现在i指向d，j指向c。我们在map里发现了a + b = target - pairSum；
                // 并且 (target - pairSum).right = b < c，也就是说abcd没有重合。
                // 所以abcd是正确答案
                if (map.containsKey(target - pairSum) && map.get(target - pairSum).right < j) {
                    return true;
                }
                if (!map.containsKey(pairSum)) {
                    map.put(pairSum, new Pair(j, i));
                }
            }
        }
        return false;
    }
}

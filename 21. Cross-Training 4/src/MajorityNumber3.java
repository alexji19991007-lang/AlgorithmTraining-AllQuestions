import java.util.*;

public class MajorityNumber3 {
    public static void main(String[] args) {
        int[] array = {1, 2, 1, 2, 3, 3, 1};
        MajorityNumber3 test = new MajorityNumber3();
        System.out.println(test.majority(array, 4));
    }

    public List<Integer> majority(int[] array, int k) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        // At most k - 1 numbers in our solution.
        int[] num = new int[k - 1];
        Arrays.fill(num, array[0]);
        int[] count = new int[k - 1];
        for (int value : array) {
            map.put(value, map.getOrDefault(value, 0) + 1);
            boolean found = false;
            boolean hasZeroCount = false;
            // 1. 如果已经有存在的，加count
            for (int j = 0; j < num.length; ++j) {
                if (value == num[j]) {
                    count[j]++;
                    found = true;
                    break;
                }
            }
            // 2. 如果没有找到已存在的，看看有没有count已经变成0的
            if (!found) {
                for (int j = 0; j < num.length; ++j) {
                    if (count[j] == 0) {
                        num[j] = value;
                        count[j] = 1;
                        hasZeroCount = true;
                        break;
                    }
                }
            }
            // 3. 如果没有count已经变成0的，每个count--
            if (!found && !hasZeroCount) {
                for (int j = 0; j < num.length; ++j) {
                    count[j]--;
                }
            }
        }
        Arrays.fill(count, 0);
        for (int j = 0; j < num.length; ++j) {
            count[j] = map.get(num[j]);
        }
        Set<Integer> hasAdded = new HashSet<>();
        for (int i = 0; i < num.length; ++i) {
            if (!hasAdded.contains(num[i]) && count[i] > array.length / k) {
                hasAdded.add(num[i]);
                res.add(num[i]);
            }
        }
        return res;
    }
}

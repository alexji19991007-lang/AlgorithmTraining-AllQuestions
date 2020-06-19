import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorityNumber3 {
    public static void main(String[] args) {
        int[] array = {1, 2, 2};
        System.out.println(majority(array, 3).toString());
    }

    public static List<Integer> majority(int[] array, int k) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int[] num = new int[k - 1];
        Arrays.fill(num, array[0]);
        int[] count = new int[k - 1];
        for (int i = 0; i < array.length; ++i) {
            boolean found = false;
            boolean hasZeroCount = false;
            // 1. 如果已经有存在的，加count
            for (int j = 0; j < num.length; ++j) {
                if (array[i] == num[j]) {
                    count[j]++;
                    found = true;
                    break;
                }
            }
            // 2. 如果没有找到已存在的，看看有没有count已经变成0的
            if (!found) {
                for (int j = 0; j < num.length; ++j) {
                    if (count[j] == 0) {
                        num[j] = array[i];
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
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < num.length; ++j) {
                if (array[i] == num[j]) {
                    count[j]++;
                    break;
                }
            }
        }
        for (int i = 0; i < num.length; ++i) {
            if (count[i] > array.length / k) {
                res.add(num[i]);
            }
        }
        return res;
    }
}

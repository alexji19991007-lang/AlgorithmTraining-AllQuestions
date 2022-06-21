import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityNumber2 {
    public List<Integer> majority(int[] array) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int num1 = array[0], count1 = 0;
        int num2 = array[0], count2 = 0;
        for (int value : array) {
            map.put(value, map.getOrDefault(value, 0) + 1);
            if (value == num1) {
                count1++;
            } else if (value == num2) {
                count2++;
            } else if (count1 == 0) {
                count1 = 1;
                num1 = value;
            } else if (count2 == 0) {
                count2 = 1;
                num2 = value;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = map.get(num1);
        // 如果num1 == num2，只加一次到答案中
        count2 = num1 == num2 ? 0 : map.get(num2);
        if (count1 > array.length / 3) {
            res.add(num1);
        }
        if (count2 > array.length / 3) {
            res.add(num2);
        }
        return res;
    }
}

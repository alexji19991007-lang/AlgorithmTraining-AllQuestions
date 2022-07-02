import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSumClosest {
    public List<Integer> closest(int[] array, int target) {
        Arrays.sort(array);
        int left = 0, right = array.length - 1;
        List<Integer> res = new ArrayList<>();
        res.add(array[left]);
        res.add(array[right]);
        int curDiff = Math.abs(array[left] + array[right] - target);
        while (left < right - 1) {
            int sum = array[left] + array[right];
            if (curDiff > Math.abs(sum - target)) {
                res.set(0, array[left]);
                res.set(1, array[right]);
                curDiff = Math.abs(sum - target);
            }
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return res;
            }
        }
        int sum = array[left] + array[right];
        if (curDiff > Math.abs(sum - target)) {
            res.set(0, array[left]);
            res.set(1, array[right]);
        }
        return res;
    }
}

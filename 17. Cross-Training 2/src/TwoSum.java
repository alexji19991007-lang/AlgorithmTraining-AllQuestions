import java.util.HashSet;
import java.util.Set;

public class TwoSum {
    public boolean existSum(int[] array, int target) {
        Set<Integer> set = new HashSet<>();
        for (int x : array) {
            int diff = target - x;
            if (set.contains(diff)) {
                return true;
            } else {
                set.add(x);
            }
        }
        return false;
    }
}

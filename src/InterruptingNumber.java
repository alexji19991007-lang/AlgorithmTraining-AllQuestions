import java.util.HashSet;
import java.util.Set;

public class InterruptingNumber {
    public static void main(String[] args) {
        int[] nums = {7, 4, 2, 9, 3, 6, 11, 10};
        System.out.println(firstInterruption(nums));
    }

    public static Integer firstInterruption(int[] nums) {
        Set<Integer> mSet = new HashSet<>();
        int globalMin = Integer.MAX_VALUE;
        for (int x : nums) {
            mSet.add(x);
            globalMin = Math.min(globalMin, x);
        }
        for (int i = 0; i < nums.length; ++i) {
            if (!mSet.contains(globalMin + i)) {
                return globalMin + i;
            }
        }
        return null;
    }
}

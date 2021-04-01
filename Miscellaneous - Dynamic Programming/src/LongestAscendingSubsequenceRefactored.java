import java.util.HashMap;
import java.util.Map;

public class LongestAscendingSubsequenceRefactored {
    public static final Map<Integer, Integer> SPECIAL_TO_REAL = new HashMap<>() {{
        put(1, 0);
        put(8, 1);
        put(0, 2);
        put(7, 3);
    }};

    public static void main(String[] args) {
        LongestAscendingSubsequenceRefactored test = new LongestAscendingSubsequenceRefactored();
        int[] array = {1, 1, 1, 1, 1, 1, 0, 0, 0, 8, 8, 8, 8, 1, 1, 1, 1, 8, 0, 7};
        // 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2, 3
        System.out.println(test.longest(array));
    }

    public int longest(int[] array) {
        if (array.length <= 1) {
            return array.length;
        }
        // The smallest ending value of all the ascending subsequences with length i
        int[] smallestEnding = new int[array.length + 1];
        int globalMax = 1;
        smallestEnding[1] = SPECIAL_TO_REAL.get(array[0]);
        for (int i = 1; i < array.length; ++i) {
            // Find the largest value smaller than the target
            int index = find(smallestEnding, 1, globalMax, SPECIAL_TO_REAL.get(array[i]));
            if (index == globalMax) {
                // 如果比target小的最大值在当前array最后一个，那么当前的element无法improve之前任何
                // 值，但是可以加在最后使得res变长一格
                smallestEnding[++globalMax] = SPECIAL_TO_REAL.get(array[i]);
            } else {
                // 如果比target小的最大值不在当前array最后一个，那么当前的element可以improve index + 1
                // 位置的值（让smallestEnding[index + 1]变得更小，后面的数字更加容易形成ascending的模式）
                smallestEnding[index + 1] = SPECIAL_TO_REAL.get(array[i]);
            }
        }
        return globalMax;
    }

    // Find the largest value smaller than or equal to the target using binary search
    public int find(int[] smallestEnding, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (smallestEnding[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (smallestEnding[right] <= target) {
            return right;
        }
        if (smallestEnding[left] <= target) {
            return left;
        }
        return 0;
    }
}
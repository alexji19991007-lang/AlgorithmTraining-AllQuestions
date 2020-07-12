public class LongestAscendingSubsequence {
    public int longest(int[] array) {
        if (array.length <= 1) {
            return array.length;
        }
        // The smallest ending value of all the ascending subsequences with length i
        int longest = 1;
        int[] smallestEnding = new int[array.length + 1];
        smallestEnding[1] = array[0];
        for (int i = 1; i < array.length; ++i) {
            // Find the largest value smaller than the target
            int index = find(smallestEnding, 0, longest, array[i]);
            if (index == longest) {
                // 如果比target小的最大值在当前array最后一个，那么当前的element无法improve之前任何
                // 值，但是可以加在最后使得longest变长一格
                smallestEnding[++longest] = array[i];
            } else {
                // 如果比target小的最大值不在当前array最后一个，那么当前的element可以improve index + 1
                // 位置的值（让smallestEnding[index + 1]变得更小，后面的数字更加容易形成ascending的模式）
                smallestEnding[index + 1] = array[i];
            }
        }
        return longest;
    }

    // Find the largest value smaller than the target using binary search
    public int find(int[] smallestEnding, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (smallestEnding[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (smallestEnding[right] < target) {
            return right;
        }
        if (smallestEnding[left] < target) {
            return left;
        }
        return 0;
    }
}

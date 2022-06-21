import java.util.Arrays;

public class LongestAscendingSubsequence2 {
    public static void main(String[] args) {
        int[] array = {5, 10, 12, 12, 13};
        System.out.println(Arrays.toString(longest(array)));
    }

    public static int[] longest(int[] a) {
        if (a.length == 0 || a.length == 1) {
            return a;
        }
        // The smallest ending value of all the ascending subsequences with length i
        int[] smallestEnding = new int[a.length + 1];
        // M[i] = The current maximum length ending with array[i]
        int[] M = new int[a.length];
        int longest = 1;
        // What is the index of the ending value of the current longest ascending subsequence
        int longestIndex = 0;
        smallestEnding[1] = a[0];
        M[0] = 1;
        for (int i = 1; i < a.length; ++i) {
            // Find the largest value smaller than the target
            int index = find(smallestEnding, 1, longest, a[i]);
            if (index == longest) {
                // 如果比target小的最大值在当前array最后一个，那么当前的element无法improve之前任何
                // 值，但是可以加在最后使得res变长一格
                smallestEnding[++longest] = a[i];
                M[i] = longest;
                longestIndex = i;
            } else {
                // 如果比target小的最大值不在当前array最后一个，那么当前的element可以improve index + 1
                // 位置的值（让samllestEnding[index + 1]变得更小，后面的数字更加容易行程ascending的模式）
                smallestEnding[index + 1] = a[i];
                M[i] = index + 1;
            }
        }
        return backTrace(a, longestIndex, longest, M);
    }

    public static int[] backTrace(int[] array, int longestIndex, int longest, int[] M) {
        int[] res = new int[longest];
        res[longest - 1] = array[longestIndex];
        int curIndex = longest - 2;
        // 从longestIndex一个个往前找。array[i]要想放到result里面需要满足两个条件：
        // 1. array[i] < array[longestIndex], 说明array[i]放进去以后可以保证还是ascending subsequence。
        // 2. M[i] == M[longestIndex] - 1, 说明在以array[i]为结尾的longest ascending subsequence长度正好比array[longestIndex]为结尾的longest ascending subsequence的长度小1。
        for (int i = longestIndex - 1; i >= 0 && curIndex >= 0; --i) {
            if (array[i] < array[longestIndex] && M[i] == M[longestIndex] - 1) {
                res[curIndex--] = array[i];
                longestIndex = i;
            }
        }
        return res;
    }

    // Find the largest value smaller than the target using binary search
    public static int find(int[] smallestEnding, int left, int right, int target) {
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

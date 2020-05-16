public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, 5, 4, 5, -5, -4, 7};
        System.out.println(firstMissingPositive(nums));
        int[] nums2 = {-1, 9, 8, 13, 10, 11, -5, -4, 14};
        System.out.println(firstMissingPositive2(nums2));
    }

    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 1;
        }
        // Numbers from index 0 to k - 1 are all positive numbers. If there is no missing positive
        // from 1 to k, then the first missing number is k + 1
        int k = partition(nums) + 1;
        // check all the positive numbers, if the current number i is within 1 to k, change nums[i - 1]
        // to negative (we only change it once, so if there exists duplicates, the later occurrences
        // are ignored.
        int temp;
        for (int i = 0; i < k; ++i) {
            temp = Math.abs(nums[i]);
            // For example, if temp = 2, change nums[2 - 1] to negative if it is positive.
            if (temp <= k) {
                nums[temp - 1] = (nums[temp - 1] < 0) ? nums[temp - 1] : -nums[temp - 1];
            }
        }
        for (int i = 0; i < k; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return k + 1;
    }

    public static int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 1;
        }
        int[] result = partition2(nums);
        int k = result[0] + 1;
        int minPositive = result[1];
        int diff = minPositive - 1;
        for (int i = 0; i < k; ++i) {
            int temp = Math.abs(nums[i]) - diff;
            if (temp <= k) {
                nums[temp - 1] = (nums[temp - 1] < 0) ? nums[temp - 1] : -nums[temp - 1];
            }
        }
        for (int i = 0; i < k; ++i) {
            if (nums[i] > 0) {
                return i + diff + 1;
            }
        }
        return k + 1 + diff;
    }

    public static int partition(int[] nums) {
        int n = nums.length;
        int k = -1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                k++;
                swap(nums, k, i);
            }
        }
        return k;
    }

    public static int[] partition2(int[] nums) {
        int[] res = new int[2];
        int n = nums.length;
        int minPositive = Integer.MAX_VALUE;
        int k = -1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                minPositive = Math.min(minPositive, nums[i]);
                k++;
                swap(nums, k, i);
            }
        }
        res[0] = k;
        res[1] = minPositive;
        return res;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

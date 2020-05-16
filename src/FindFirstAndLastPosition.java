public class FindFirstAndLastPosition {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        int firstAppearance = firstOccur(nums, target);
        if (firstAppearance == -1) {
            return res;
        }
        res[0] = firstAppearance;
        int lastAppearance = lastOccur(nums, target, firstAppearance);
        res[1] = lastAppearance;
        return res;
    }

    private int firstOccur(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }

    private int lastOccur(int[] nums, int target, int start) {
        // Actually this won't happen
        if (nums.length == 0) {
            return -1;
        }
        int left = start;
        int right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (nums[right] == target) {
            return right;
        }
        if (nums[left] == target) {
            return left;
        }
        // This won't happen
        return -1;
    }
}

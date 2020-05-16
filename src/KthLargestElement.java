public class KthLargestElement {
    // O(n) on average, O(n^2) worst case
    // Space O(1)
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            // Do partition just like quick sort
            int j = partition(nums, low, high);
            // the value returned is the pivot's final location after this round of partition
            if (j < k) {
                low = j + 1;
            } else if (j > k) {
                high = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int low, int high) {
        // Always take the last number as pivot
        int pivot = high;
        int i = low - 1;
        for (int j = low; j < high; ++j) {
            if (nums[j] < nums[pivot]) {
                i++;
                swap(nums, i, j);
            }
        }
        i++;
        swap(nums, i, pivot);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

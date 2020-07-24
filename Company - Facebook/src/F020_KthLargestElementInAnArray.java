import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class F020_KthLargestElementInAnArray {
    // Method 1: Use Min Heap
    // TC: klogk + (n - k)logk + klogk = O((n + k)logk)
    // SC: O(k)
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>(k, (i1, i2) -> {
            if (i1.equals(i2)) {
                return 0;
            }
            return i1 < i2 ? -1 : 1;
        });
        for (int i = 0; i < nums.length; ++i) {
            if (i < k) {
                minHeap.offer(nums[i]);
            } else if (minHeap.peek() < nums[i]) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.poll();
    }

    // Method 2: Quick Select
    // TC: O(n) on average, O(n^2) in the worst case
    // SC: O(1)
    public int findKthLargest2(int[] nums, int k) {
        k = nums.length - k;
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int j = partition(nums, low, high);
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

    public int partition(int[] nums, int low, int high) {
        Random rand = new Random();
        int pivot = low + rand.nextInt(high - low + 1);
        swap(nums, high, pivot);
        int start = low, end = high - 1;
        while (start <= end) {
            if (nums[start] < nums[high]) {
                start++;
            } else {
                swap(nums, start, end--);
            }
        }
        swap(nums, start, high);
        return start;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

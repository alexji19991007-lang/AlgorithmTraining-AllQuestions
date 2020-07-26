// LeetCode 88
public class F037_MergeSortedArray {
    // TC: O(n)
    // SC: O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2.length == 0) {
            return;
        }
        int ptr1 = m - 1;
        int ptr2 = n - 1;
        int i = nums1.length - 1;
        while (i >= 0) {
            if (ptr1 == -1 || ptr2 == -1) {
                break;
            }
            nums1[i--] = nums1[ptr1] >= nums2[ptr2] ? nums1[ptr1--] : nums2[ptr2--];
        }
        if (ptr1 == -1) {
            while (i >= 0) {
                nums1[i--] = nums2[ptr2--];
            }
        }
    }
}

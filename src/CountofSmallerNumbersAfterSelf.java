import java.util.ArrayList;
import java.util.List;

public class CountofSmallerNumbersAfterSelf {
    private static int[] nums;
    private static int[] indexes;
    private static int[] count;

    public static void main(String[] args) {
        int[] input = {5,2,6,1};
        System.out.println(countSmaller(input).toString());
    }

    public static List<Integer> countSmaller(int[] nums) {
        CountofSmallerNumbersAfterSelf.nums = nums;
        indexes = new int[nums.length];
        count = new int[nums.length];
        // To solve this problem, we will merge sort the indexes according to the number of that index
        for (int i = 0; i < nums.length; ++i) {
            indexes[i] = i;
        }
        mergeSort(0, nums.length - 1);
        List<Integer> res = new ArrayList<>();
        for (int i : count) {
            res.add(i);
        }
        return res;
    }

    public static void mergeSort(int start, int end) {
        if (end <= start) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(start, mid);
        mergeSort(mid + 1, end);
        merge(start, end);
    }

    public static void merge(int start, int end) {
        int mid = start + (end - start) / 2;
        // Left half pointer
        int leftIndex = start;
        // Right half pointer
        int rightIndex = mid + 1;
        // Use a new array to hold the new indexes after sorting
        int[] newIndex = new int[end - start + 1];
        // rightCount counts how many numbers are smaller than the elements on the left half
        int rightCount = 0;

        int cur = 0; // pointer to fill in the newIndex array
        while (leftIndex <= mid && rightIndex <= end) {
            // if the current element on the left half is greater than the current element on the right half
            if (nums[indexes[leftIndex]] > nums[indexes[rightIndex]]) {
                // put the index of the right half element to the newIndex array (right element
                // is smaller so it should be sorted before the left element)
                newIndex[cur] = indexes[rightIndex];
                // this element has discovered one more element that is smaller than itself, so increment count by 1
                rightCount++;
                rightIndex++; // go to next element on the right half
            } else {
                // the current left half element is smaller, so we cannot find any elements on the
                // right half that is smaller than the current left half element
                newIndex[cur] = indexes[leftIndex];
                // update the count for that element
                count[indexes[leftIndex]] += rightCount;
                leftIndex++;
            }
            cur++;
        }
        while (leftIndex <= mid) {
            newIndex[cur] = indexes[leftIndex];
            count[indexes[leftIndex]] += rightCount;
            leftIndex++;
            cur++;
        }
        while (rightIndex <= end) {
            newIndex[cur++] = indexes[rightIndex++];
        }
        // copy the newIndex array into the original indexes array
        System.arraycopy(newIndex, 0, indexes, start, end + 1 - start);
    }
}

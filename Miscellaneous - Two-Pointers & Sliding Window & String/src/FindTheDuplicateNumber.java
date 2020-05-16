public class FindTheDuplicateNumber {
    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 1, 3, 1, 5};
        System.out.println(findDuplicate(nums));
    }

    // This problem is very similar to the DetectCycle Problem (find the start point of a cycle in
    // a linked list). We can convert the nums array into a linked list:
    // index:   0   1   2   3   4   5   6
    // value:   2   6   4   1   3   1   5
    // linked list with cycle:
    // index:   0     2     4     3     1     6
    // value:   2->   4->   3->   1->   6->   5-> points to 1 by index 5
    // Now all we have to do is to find the start of the cycle, which is the duplicate number
    public static int findDuplicate(int[] nums) {
        int slow = nums[0]; // 2
        int fast = nums[0]; // 2
        slow = nums[slow]; // slow = 4
        fast = nums[nums[fast]]; // fast = 3
        while (slow != fast) {
            slow = nums[slow]; // slow moves forward 1 step each time
            fast = nums[nums[fast]]; // fast moves forward 2 steps each time
        }
        int ptr1 = nums[0];
        int ptr2 = slow;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
    }
}

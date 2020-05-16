public class SortColors {
    //  2     0     2     1     1     0
    // p0                             p2
    // cur
    public void sortColors(int[] nums) {
        // 三个pointer.
        // p0: the rightmost boundary of 0s. p2: the leftmost boundary of 2s
        // cur: the current element under consideration
        // p0 and p2 will move if and only if the number they point to changes
        int cur = 0, p0 = 0, p2 = nums.length - 1;
        // we will continue sorting until cur goes past p2
        while (cur <= p2) {
            // 若当前数字(cur)为2，将cur的数字与p2的数字交换
            if (nums[cur] == 2) {
                int temp = nums[cur];
                nums[cur] = nums[p2];
                nums[p2] = temp;
                // 注意，在此情况下我们只需向前移动p2，因为p2当前位置是2，以后不会再动了
                // 对于cur来说，它换回来的东西仍有可能发生改变，所以不能动cur
                p2--;
            }
            // 若当前数字为0，将cur的数字与p0的数字交换
            else if (nums[cur] == 0) {
                int temp = nums[cur];
                nums[cur] = nums[p0];
                nums[p0] = temp;
                p0++;
                // 在此情况下，cur可以直接移动，因为它换来的东西不会是2（如果是2那之前cur指向p0的时候就会和p2交换）
                cur++;
            } else {
                // 1不需要移动
                cur++;
            }
        }
    }
}

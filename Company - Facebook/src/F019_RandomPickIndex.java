import java.util.Random;

// LeetCode 398
public class F019_RandomPickIndex {
    // TC: O(n)
    // SC: O(1)
    private Random rand;
    private int[] nums;

    public F019_RandomPickIndex(int[] nums) {
        this.rand = new Random();
        this.nums = nums;
    }

    public int pick(int target) {
        int res = -1;
        int occurrence = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != target) {
                continue;
            }
            res = rand.nextInt(++occurrence) == 0 ? i : res;
        }
        return res;
    }
}

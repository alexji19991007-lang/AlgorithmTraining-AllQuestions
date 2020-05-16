public class JumpGame {
    public static void main(String[] args) {
        int[] nums = {3, 0, 2, 3, 0, 0, 1};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] M = new boolean[n];
        M[n - 1] = true;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j <= nums[i] + i; ++j) {
                if (M[j]) {
                    M[i] = true;
                    break;
                }
            }
        }
        return M[0];
    }
}

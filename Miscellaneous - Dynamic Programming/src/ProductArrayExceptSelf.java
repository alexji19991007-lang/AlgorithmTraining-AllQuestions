public class ProductArrayExceptSelf {
    //                     1   2   3   4   5
    //ans(initialize)      1   1   2   6   24    This is the products of all the numbers to the left of the current index (excluding current)
    //right               60  60   20  5   1     This is the products of all the numbers to the right of the current index (excluding current)
    //ans(final)          120 60   40  30  24

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        // initialize the ans array
        ans[0] = 1;
        for (int i = 1; i < n; ++i) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }
        int R = 1;
        // use R to implicitly keep track of the product on the right
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = ans[i] * R;
            R *= nums[i];
        }
        return ans;
    }
}

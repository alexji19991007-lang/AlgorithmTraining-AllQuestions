package HouseRobber;

public class HouseRobber {
    // f(k) = Largest amount that you can rob from the first k houses.
    // Ai = Amount of money at the ith house.
    // Suppose we already have f(k - 1) and f(k - 2), and we want to get f(k).
    // We have two options:
    //      1. Rob the k-th house, and add its amount to the f(k - 2). -> f(k - 2) + Ak
    //      2. Skip the k-th house, and stick with the amount of f(k - 1).
    // Thus we have: f(k) = max(f(k – 2) + Ak, f(k – 1))
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //                       prev2   prev1
        // Set up the base case: f(-1) = f(0) = 0
        int prev1 = 0;
        int prev2 = 0;
        for (int cur : nums) {
            int temp = prev1;
            // temp here is f(k - 1) and cur + prev2 here is f(k - 2) + Ak
            // move both prev1 and prev2 1 step forward
            prev1 = Math.max(temp, cur + prev2);
            prev2 = temp;
        }
        return prev1;
    }
}

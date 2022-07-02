// LeetCode 42
public class TrappingRainWater {
    // TC: O(n)
    // SC: O(1)
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right];
        while (left < right) {
            if (height[left] <= height[right]) {
                res += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                res += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return res;
    }
}

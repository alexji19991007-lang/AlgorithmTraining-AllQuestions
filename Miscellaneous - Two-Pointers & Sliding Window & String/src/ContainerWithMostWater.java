public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxA = 0, left = 0, right = height.length - 1;
        while (left < right) {
            // the current volume of water that can be stored is determined by the shorter side of
            // the container
            int curA = Math.min(height[left], height[right]) * (right - left);
            maxA = Math.max(curA, maxA);
            // if left length is shorter than right length, then we move left forward to see if we
            // can contain more water, and vice versa.
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxA;
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Assumption:
public class GreaterThanLeft {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 5, 6, 7, 9, 8, 10};
        System.out.println(greaterThanLeftLessThanRight(nums).toString());
    }


    public static List<Integer> greaterThanLeft(int[] nums) {
        int n = nums.length;
        int[] maxLeft = new int[nums.length];
        int[] minRight = new int[nums.length];
        maxLeft[0] = Integer.MIN_VALUE;
        minRight[n - 1] = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            maxLeft[i] = Math.max(nums[i], maxLeft[i - 1]);
        }
        for (int i = n - 2; i >= 0; --i) {
            minRight[i] = Math.min(nums[i], minRight[i + 1]);
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] >= maxLeft[i] && nums[i] <= minRight[i]) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    public static List<Integer> greaterThanLeftLessThanRight(int[] nums) {
        int n = nums.length;
        int maxLeft = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() > num) {
                stack.pop();
            }
            if (num >= maxLeft) {
                stack.push(num);
                maxLeft = Math.max(maxLeft, num);
            }
        }
        return stack;
    }
}

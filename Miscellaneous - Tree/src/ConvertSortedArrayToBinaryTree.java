public class ConvertSortedArrayToBinaryTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int leftPos, int rightPos) {
        if (leftPos > rightPos) {
            return null;
        }
        // always choose middle node as our root
        int midPos = (leftPos + rightPos) / 2;
        // create the root
        TreeNode root = new TreeNode(nums[midPos]);
        // get the left and the right recursively
        root.left = helper(nums, leftPos, midPos - 1);
        root.right = helper(nums, midPos + 1, rightPos);
        return root;
    }
}

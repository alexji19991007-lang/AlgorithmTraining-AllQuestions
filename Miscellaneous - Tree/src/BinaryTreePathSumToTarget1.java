// Assumption: The path sum must be from root to leaf
public class BinaryTreePathSumToTarget1 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        sum -= root.val;
        if (root.left == null && root.right == null) {
            return sum == 0;
        }
        return hasPathSum(root.right, sum) || hasPathSum(root.left, sum);
    }
}

public class CheckIfBinaryTreeIsBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(findHeight(root.left) - findHeight(root.right)) <= 1
                &&
                isBalanced(root.left) && isBalanced(root.right);
    }

    public int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
    }
    // Height = log(n) levels
    // Each level time = O(n)
    // Thus, total time = n + n/2 * 2 + n/4 * 4 + … = O(n log n)
}

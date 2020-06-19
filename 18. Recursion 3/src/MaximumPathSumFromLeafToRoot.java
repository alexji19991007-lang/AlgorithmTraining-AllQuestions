public class MaximumPathSumFromLeafToRoot {
    public int maxPathSumLeafToRoot(TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;
        maxGain(root, maxSum, 0);
        return maxSum[0];
    }

    public void maxGain(TreeNode root, int[] maxSum, int curSum) {
        if (root.left == null && root.right == null) {
            maxSum[0] = Math.max(maxSum[0], curSum + root.key);
        }
        if (root.left != null) {
            maxGain(root.left, maxSum, curSum + root.key);
        }
        if (root.right != null) {
            maxGain(root.right, maxSum, curSum + root.key);
        }
    }
}

public class MaximumPathSumBinaryTree2 {
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;
        maxGain(root, maxSum);
        return maxSum[0];
    }

    public int maxGain(TreeNode root, int[] maxSum) {
        if (root == null) {
            return 0;
        }
        int leftGain = Math.max(maxGain(root.left, maxSum), 0);
        int rightGain = Math.max(maxGain(root.right, maxSum), 0);
        int makeNewPath = root.key + leftGain + rightGain;
        maxSum[0] = Math.max(maxSum[0], makeNewPath);
        return root.key + Math.max(leftGain, rightGain);
    }
}

public class MaximumPathSumBinaryTree1 {
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;
        maxGain(root, maxSum);
        return maxSum[0];
    }

    public int maxGain(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return node.key;
        }
        int leftGain= maxGain(node.left, maxSum);
        int rightGain = maxGain(node.right, maxSum);
        if (node.left != null && node.right != null) {
            int makeNewPath = node.key + leftGain + rightGain;
            maxSum[0]  = Math.max(maxSum[0], makeNewPath);
            return node.key + Math.max(leftGain, rightGain);
        }
        return node.left == null ? rightGain + node.key : leftGain + node.key;
    }

}

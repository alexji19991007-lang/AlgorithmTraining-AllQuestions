// Assumption: 1. The path can be from any node to any node.
public class MaximumPathSumBT2 {
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
        // What is the maximum gain from left and right subtrees
        // Note: since we can start from any node to any node, we have a choice not to include the
        //       results from left & right subtree, i.e. we do not include leftGain or rightGain
        //       when the gain is negative.
        int leftGain = Math.max(maxGain(node.left, maxSum), 0);
        int rightGain = Math.max(maxGain(node.right, maxSum), 0);
        // If we want both the leftGain and rightGain, then we have to make a new path including
        // the current node
        int priceNewPath = node.key + leftGain + rightGain;
        maxSum[0] = Math.max(maxSum[0], priceNewPath);
        // If we only take one of the two subtrees' gains
        return node.key + Math.max(leftGain, rightGain);
    }
}

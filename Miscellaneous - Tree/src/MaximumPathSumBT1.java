// Assumption: 1. The path should be from one leaf node to another leaf node.
//             2. If no such path, return Integer.MIN_VALUE.
public class MaximumPathSumBT1 {
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;
        maxGain(root, maxSum);
        return maxSum[0];
    }

    public int maxGain(TreeNode node, int[] maxSum) {
        // If the node is null, gain is 0
        if (node == null) {
            return 0;
        }
        // if this is a leaf node, return the val of this node
        if (node.left == null && node.right == null) {
            return node.val;
        }
        // What is the maximum gain from left and right subtrees
        int leftGain = maxGain(node.left, maxSum);
        int rightGain = maxGain(node.right, maxSum);
        if (node.left != null && node.right != null) {
            // If we want both the leftGain and rightGain, then we have to make a new path including
            // the current node.
            int priceNewPath = node.val + leftGain + rightGain;
            maxSum[0] = Math.max(maxSum[0], priceNewPath);
            // Return the max gain if we only choose one child, i.e. not make a new path
            return node.val + Math.max(leftGain, rightGain);
        }
        // If there is one null child, we cannot make a new path.
        return node.left == null ? rightGain + node.val : leftGain + node.val;
    }
}

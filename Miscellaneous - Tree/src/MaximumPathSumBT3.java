// Assumption: 1. Both the starting and ending node of the sub-path should be on the same path from
//                root to one of the leaf nodes.
//             2. The sub-path is allowed to contain only one node.
public class MaximumPathSumBT3 {
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
        // Since the path should start and end at the same path, we can only choose one path.
        // Thus, we choose the path that gives us the max gain.
        int curSum = node.key + Math.max(leftGain, rightGain);
        // Update the final solution if needed.
        maxSum[0] = Math.max(maxSum[0], curSum);
        // Return this level's solution to the previous level.
        return curSum;
    }
}

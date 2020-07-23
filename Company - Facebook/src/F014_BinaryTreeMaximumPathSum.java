// LeetCode 124

// For this problem, a path is defined as any sequence of nodes from some
// starting node to any node in the tree along the parent-child connections.
// The path must contain at least one node and does not need to go through the root.
public class F014_BinaryTreeMaximumPathSum {
    // TC: O(n), visit each node exactly once
    // SC: O(logn), we need to keep a recursion stack of the size of the tree's height
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

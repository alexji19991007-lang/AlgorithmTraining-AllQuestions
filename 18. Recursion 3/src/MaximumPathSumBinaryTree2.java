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
        // 回想一下subarray sum这道题。
        // 如果左边/右边直上直下的path的gain小于0的话，我们就不考虑那条path的gain了
        // 因为当前node is better by itself
        int leftGain = Math.max(maxGain(root.left, maxSum), 0);
        int rightGain = Math.max(maxGain(root.right, maxSum), 0);
        int makeNewPath = root.key + leftGain + rightGain;
        maxSum[0] = Math.max(maxSum[0], makeNewPath);
        return root.key + Math.max(leftGain, rightGain);
    }
}

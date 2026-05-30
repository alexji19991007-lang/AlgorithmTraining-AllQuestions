public class MaximumPathSumBinaryTree1 {
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;
        maxPathGain(root, maxSum);
        return maxSum[0];
    }

    // 注意我们这里return的并不是最终答案，而是一条直上直下的path的gain.
    // 更准确一点说，是当前node左边直上直下path的gain & 当前node右边直上直下path的gain，然后取两者最大。
    // 最终答案是maxSum[0]来记录
    public int maxPathGain(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return node.key;
        }
        // 当前node左边直上直下path的gain
        int leftGain= maxPathGain(node.left, maxSum);
        // 当前node右边直上直下path的gain
        int rightGain = maxPathGain(node.right, maxSum);
        // 必须要左右两边都不是null，我们才能以当前node为中间点创建新的leaf to leaf path
        if (node.left != null && node.right != null) {
            // 创建新path，并且尝试更新最终答案
            int makeNewPath = node.key + leftGain + rightGain;
            maxSum[0]  = Math.max(maxSum[0], makeNewPath);
            // 左右两边答案取最大，加上当前node的值
            return node.key + Math.max(leftGain, rightGain);
        }
        return node.left == null ? rightGain + node.key : leftGain + node.key;
    }

}

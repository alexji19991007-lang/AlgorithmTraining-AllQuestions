// LeetCode 1448
public class CountGoodNodesInBinaryTree {
    // TC: O(n)
    // SC: O(n)
    public int goodNodes(TreeNode root) {
        int[] res = {0};
        traverseTree(root, Integer.MIN_VALUE, res);
        return res[0];
    }

    public void traverseTree(TreeNode node, int curMax, int[] res) {
        if (curMax <= node.key) {
            res[0]++;
        }
        if (node.left != null) {
            traverseTree(node.left, Math.max(curMax, node.key), res);
        }
        if (node.right != null) {
            traverseTree(node.right, Math.max(curMax, node.key), res);
        }
    }
}

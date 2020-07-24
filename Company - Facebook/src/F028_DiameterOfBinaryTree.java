// LeetCode 543
public class F028_DiameterOfBinaryTree {
    // TC: O(n), we visit every node exactly once
    // SC: O(n), in the worst case (a very skewed BT), the recursion stack is equal to the number of
    //     nodes
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        diameterHelper(root, res);
        return res[0];
    }

    public int diameterHelper(TreeNode node, int[] res) {
        if (node == null) {
            return 0;
        }
        int leftDiameter = diameterHelper(node.left, res);
        int rightDiameter = diameterHelper(node.right, res);
        res[0] = Math.max(res[0], leftDiameter + rightDiameter);
        return Math.max(leftDiameter, rightDiameter) + 1;
    }
}

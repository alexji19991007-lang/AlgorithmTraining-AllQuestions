public class BinaryTreeDiameter {
    public int diameter(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        diameterHelper(root, res);
        return res[0];
    }

    public void diameterHelper(TreeNode node, int[] res) {
        if (node == null) {
            return;
        }
        int leftHeight = findHeight(node.left);
        int rightHeight = findHeight(node.right);
        if (leftHeight > 0 && rightHeight > 0) {
            res[0] = Math.max(res[0], leftHeight + rightHeight + 1);
        }
        diameterHelper(node.left, res);
        diameterHelper(node.right, res);
    }

    public int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
    }
}

public class FlattenBinaryTreeToLinkedList {
    public TreeNode flatten(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        flattenHelper(root, prev);
        return root;
    }

    private void flattenHelper(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return;
        }
        TreeNode leftChild = root.left;
        TreeNode rightChild = root.right;
        if (prev[0] == null) {
            prev[0] = root;
            root.left = null;
        } else {
            prev[0].right = root;
            prev[0] = root;
            root.left = null;
        }
        flattenHelper(leftChild, prev);
        flattenHelper(rightChild, prev);
    }

}

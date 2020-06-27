public class FlattenBinaryTreeToString {
    public String flattenBinaryTree(TreeNode root) {
        String str = "";
        return helper(root, str);
    }

    public String helper(TreeNode root, String str) {
        if (root == null) {
            return str;
        }
        str += root.key;
        if (root.left == null && root.right == null) {
            return str;
        }
        str += '(';
        str = helper(root.left, str);
        str += ')';
        if (root.right != null) {
            str += '(';
            str = helper(root.right, str);
            str += ')';
        }
        return str;
    }
}

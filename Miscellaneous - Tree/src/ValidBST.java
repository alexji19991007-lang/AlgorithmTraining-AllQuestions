public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        return isBSTUntil(root, null, null);
    }

    public boolean isBSTUntil(TreeNode root, Integer minimum, Integer maximum) {
        if (root == null) {
            return true;
        }
        if ((minimum != null && root.key <= minimum) || (maximum != null && root.key >= maximum)) {
            return false;
        }
        // Do not use root.val - 1 or root.val + 1 to avoid overflow
        return isBSTUntil(root.left, minimum, root.key) && isBSTUntil(root.right, root.key, maximum);
    }
}

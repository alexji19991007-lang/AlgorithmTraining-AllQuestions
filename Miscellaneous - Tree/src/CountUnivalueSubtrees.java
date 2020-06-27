public class CountUnivalueSubtrees {
    public int countUnivalSubtrees(TreeNode root) {
        int[] count = new int[1];
        helper(root, count);
        return count[0];
    }

    public boolean helper(TreeNode root, int[] count) {
        if (root == null) {
            return true;
        }
        boolean left = helper(root.left, count);
        boolean right = helper(root.right, count);
        if (left && right) {
            if (root.left != null && root.key != root.left.key) {
                return false;
            }
            if (root.right != null && root.key != root.right.key) {
                return false;
            }
            count[0]++;
            return true;
        }
        return false;
    }
}

// Assumption: 1. No parent pointer.
//             2. Both targets are not guaranteed to exist in the tree.
public class LowestCommonAncestor3 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
        boolean[] found = new boolean[2];
        // found[0] == foundOne, found[1] == foundTwo
        TreeNode LCA = helper(root, one, two, found);
        if (found[0] && found[1]) {
            return LCA;
        }
        return null;
    }

    public TreeNode helper(TreeNode root, TreeNode one, TreeNode two, boolean[] found) {
        if (root == null) {
            return null;
        }
        // We cannot immediately return here if the root == one of two keys, because two targets
        // are not guaranteed to be in the tree, so we have to locate them individually.
        TreeNode left = helper(root.left, one, two, found);
        TreeNode right = helper(root.right, one, two, found);
        // If both left and right are not null, this means we have already found two nodes, so the
        // current root is the LCA.
        // Note: this if branch will only be execute once (i.e. when the root is LCA)
        if (left != null && right != null) {
            return root;
        }
        if (root == one || root == two) {
            if (root == one) {
                found[0] = true;
            }
            if (root == two) {
                found[1] = true;
            }
            return root;
        }
        return left != null ? left : right;
    }
}

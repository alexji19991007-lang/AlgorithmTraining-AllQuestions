public class LowestCommonAncestorBST1 {
    public TreeNode lca(TreeNode root, int p, int q) {
        return p < q ? findLCA(root, p, q) : findLCA(root, q, p);
    }

    public TreeNode findLCA(TreeNode root, int n1, int n2) {
        while (root != null && (root.key < n1 || root.key > n2)) {
            if (root.key < n1) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }

}

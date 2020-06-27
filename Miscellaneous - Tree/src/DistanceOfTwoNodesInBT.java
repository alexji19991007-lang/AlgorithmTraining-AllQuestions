public class DistanceOfTwoNodesInBT {
    public int distance(TreeNode root, int k1, int k2) {
        int x = pathLength(root, k1) - 1;
        int y = pathLength(root, k2) - 1;
        int LCA = lowestCommonAncestor(root, k1, k2).key;
        int distLCA = pathLength(root, LCA) - 1;
        return x + y - 2 * distLCA;
    }

    public int pathLength(TreeNode root, int k) {
        if (root != null) {
            int x = 0;
            if ((root.key == k) || (x = pathLength(root.left, k)) > 0
                    || (x = pathLength(root.right, k)) > 0) {
                return x + 1;
            }
            return 0;
        }
        return 0;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        } else if (root.key == p || root.key == q) {
            return root;
        }
        TreeNode leftSearch = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSearch = lowestCommonAncestor(root.right, p, q);
        if (leftSearch != null && rightSearch != null) {
            return root;
        }
        if (leftSearch != null) {
            return leftSearch;
        }
        return rightSearch;
    }
}

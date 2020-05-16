public class LowestCommonAncestor1 {
    // Assumption: 1. No parent pointer.
    //             2. Both targets exist in the tree.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null) {
            return null;
        }
        // We can also put an else-if check here. Since two nodes are guaranteed to be in the tree,
        // if we find one of the two nodes, we can immediately return.
        // Note: if one is two's ancestor, once we find one, we can directly return.

        // if (root == one || root == two) {
        //   return root;
        // }
        TreeNode leftSearch = lowestCommonAncestor(root.left, one, two);
        TreeNode rightSearch = lowestCommonAncestor(root.right, one, two);
        if (leftSearch != null && rightSearch != null) {
            return root;
        }
        if (root == one || root == two) {
            return root;
        }
        return leftSearch != null ? leftSearch : rightSearch;
    }
}

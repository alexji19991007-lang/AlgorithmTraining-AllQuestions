// LeetCode 236
public class F058_LowestCommonAncestor {
    // TC: O(n) where n is the number of nodes in the BT
    // SC: O(h) keep a recursion stack of the height of the tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // We can also put an else-if check here. Since two nodes are guaranteed to be in the tree,
        // if we find one of the two nodes, we can immediately return.
        // Note: if one is two's ancestor, once we find one, we can directly return.
        if (root == p || root == q) {
            return root;
        }
        TreeNode leftSearch = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSearch = lowestCommonAncestor(root.right, p, q);
        if (leftSearch != null && rightSearch != null) {
            return root;
        }
        return leftSearch != null ? leftSearch : rightSearch;
    }
}

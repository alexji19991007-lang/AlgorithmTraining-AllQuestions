// LeetCode 1123
public class F070_LowestCommonAncestorOfDeepestLeaves {
    // TC: O(n)
    // SC: O(n)
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        TreeNodeWithDepth n = getLCA(root, 0);
        return n.node;
    }

    public TreeNodeWithDepth getLCA(TreeNode node, int depth) {
        if (node == null) {
            return new TreeNodeWithDepth(null, depth);
        }
        TreeNodeWithDepth l = getLCA(node.left, depth + 1);
        TreeNodeWithDepth r = getLCA(node.right, depth + 1);
        // If the left and right subtree has the same depth, the current node is the LCA for them (in
        // this case, the current node is the LCA for the deepest nodes of the subtree rooted at the
        // current node).
        if (l.depth == r.depth) {
            return new TreeNodeWithDepth(node, l.depth);
        }
        // Otherwise, return the TreeNodeWithDepth with larger depth (in this case, we have already
        // found a solution for the subtree starting at the current node).
        return l.depth > r.depth ? l : r;
    }

    static class TreeNodeWithDepth {
        TreeNode node;
        int depth;

        public TreeNodeWithDepth(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}

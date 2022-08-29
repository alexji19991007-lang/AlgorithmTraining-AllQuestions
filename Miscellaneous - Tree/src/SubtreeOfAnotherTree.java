// LeetCode 572
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return s != null && (equals(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
    }

    public boolean equals(TreeNode x, TreeNode y) {
        if (x == null && y == null)
            return true;
        if (x == null || y == null)
            return false;
        return x.key == y.key && equals(x.left, y.left) && equals(x.right, y.right);
    }
}

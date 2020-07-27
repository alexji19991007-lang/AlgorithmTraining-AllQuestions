// LeetCode 1026
public class F045_MaximumDifferenceBetweenNodeAndAncestor {
    // TC: O(n)
    // SC: O(h)
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return maxDiff(root, root.key, root.key);
    }

    public int maxDiff(TreeNode node, int max, int min) {
        if (node == null) {
            return max - min;
        }
        max = Math.max(max, node.key);
        min = Math.min(min, node.key);
        return Math.max(maxDiff(node.left, max, min), maxDiff(node.right, max, min));
    }
}

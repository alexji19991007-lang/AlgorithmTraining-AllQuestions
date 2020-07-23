// LeetCode 398
public class F018_RangeSumOfBST {
    // TC: O(n), worst case we need to go through all nodes
    // SC: O(h), we need to keep a recursion stack the size of the height of the tree
    public int rangeSumBST(TreeNode root, int L, int R) {
        int[] res = new int[1];
        helper(root, L, R, res);
        return res[0];
    }

    public void helper(TreeNode node, int L, int R, int[] res) {
        if (node == null) {
            return;
        }
        if (node.key >= L && node.key <= R) {
            res[0] += node.key;
        }
        if (node.key > L) {
            helper(node.left, L, R, res);
        }
        if (node.key < R) {
            helper(node.right, L, R, res);
        }
    }
}

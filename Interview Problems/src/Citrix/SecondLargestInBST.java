package Citrix;

public class SecondLargestInBST {
    public int secondLargest(TreeNode root) {
        if (root.right == null && root.left != null) {
            TreeNode cur = root.left;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.val;
        }
        if (root.right == null) {
            return Integer.MIN_VALUE;
        }
        int res = secondLargest(root.right);
        return res == Integer.MIN_VALUE ? root.val : res;
    }
}

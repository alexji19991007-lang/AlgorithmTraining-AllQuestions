public class HouseRobber3 {
    public int robTree(TreeNode root) {
        int[] res = robSub(root);
        // res[0] means the maximum amount we can get if we don't rob the current root
        // res[1] means the maximum amount we can get if we rob the current root
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];

        // if we do not rob the current root, we can still choose whether to rob the left node or
        // right node, so take max of both
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // if we rob the current root, then we cannot rob the left and right node
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}

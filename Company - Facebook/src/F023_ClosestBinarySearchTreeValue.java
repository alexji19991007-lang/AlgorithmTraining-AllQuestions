// LeetCode 270
public class F023_ClosestBinarySearchTreeValue {
    // Iteratively
    // TC: O(h), since we are going from root down to a leaf node
    // SC: O(1)
    public int closestValue(TreeNode root, double target) {
        int res = root.key;
        while (root != null) {
            if ((double)root.key == target) {
                return res;
            }
            if (Math.abs(root.key - target) < Math.abs(res - target)) {
                res = root.key;
            }
            root = root.key > target ? root.left : root.right;
        }
        return res;
    }

    // Recursively
    // TC: O(h)
    // SC: O(h)
    public int closestValue2(TreeNode root, double target) {
        int res = root.key;
        if ((double)res == target) {
            return res;
        }
        TreeNode child = root.key > target ? root.left : root.right;
        if (child == null) {
            return res;
        }
        int res2 = closestValue(child, target);
        return Math.abs(res - target) < Math.abs(res2 - target) ? res : res2;
    }
}

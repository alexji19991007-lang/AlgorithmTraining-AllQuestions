public class LongestAscendingPathBT {
    public int longest(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root, 0, root.val);
    }

    public int helper(TreeNode root, int len, int lastVal) {
        if (root == null) {
            return 0;
        }
        // If current node's value is greater than it's parent's value, we can increment the count
        if (root.val > lastVal) {
            len++;
        } else {
            // Otherwise, the original ascending path can no longer continue at this node, so a new
            // ascending path will start counting from the current node.
            len = 1;
        }
        int leftMaxLength = helper(root.left, len, root.val);
        int rightMaxLength = helper(root.right, len, root.val);
        // The return value is the longest ascending path
        return Math.max(len, Math.max(leftMaxLength, rightMaxLength));
    }

    // Another solution:
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Take the max as a global variable.
        int[] max = new int[1];
        helper1(root, 0, root.val, max);
        return max[0];
    }

    public void helper1(TreeNode root, int len, int lastVal, int[] max) {
        if (root == null) {
            return;
        }
        if (root.val > lastVal) {
            len++;
        } else {
            len = 1;
        }
        max[0] = Math.max(max[0], len);
        helper(root.left, len, root.val);
        helper(root.right, len, root.val);
    }
}

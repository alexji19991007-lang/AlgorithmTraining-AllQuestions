public class ClosestNumberInBST {
    // Iteratively
    public int closest(TreeNode root, int target) {
        int res = root.key;
        while (root != null) {
            if (root.key == target) {
                return root.key;
            }
            if (Math.abs(root.key - target) < Math.abs(res - target)) {
                res = root.key;
            }
            root = root.key < target ? root.right : root.left;
        }
        return res;
    }

    // Recursively
    public int closestRecursive(TreeNode root, int target) {
        TreeNode[] res = new TreeNode[1];
        res[0] = root;
        findHelper(root, target, res);
        return res[0].key;
    }

    public void findHelper(TreeNode root, int target, TreeNode[] res) {
        if (root == null) {
            return;
        }
        if (root.key == target) {
            res[0] = root;
            return;
        }
        if (Math.abs(root.key - target) < Math.abs(res[0].key - target)) {
            res[0] = root;
        }
        if (root.key < target) {
            findHelper(root.right, target, res);
        } else {
            findHelper(root.left, target, res);
        }
    }
}

import java.util.*;

public class IsBinaryTreeOrNot {
    public boolean isBST(TreeNode root) {
        return isBSTUntil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isBSTUntil(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.key <= min || root.key >= max) {
            return false;
        }
        return isBSTUntil(root.left, min, root.key) && isBSTUntil(root.right, root.key, max);
    }
}

import java.util.*;

public class SearchInBST {
    // Recursive
    public TreeNode search1(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.key == key) {
            return root;
        }
        if (root.key > key) {
            return search1(root.left, key);
        } else {
            return search1(root.right, key);
        }
    }

    // Iterative
    public TreeNode search2(TreeNode root, int key) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.key == key) {
                return cur;
            } else if (cur.key > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

}

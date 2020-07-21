import java.util.*;

public class InsertInBST {
    // Recursive
    public TreeNode insert1(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        if (root.key > key) {
            root.left = insert1(root.left, key);
        } else if (root.key < key) {
            root.right = insert1(root.right, key);
        }
        return root;
    }

    // Iterative
    public TreeNode insert2(TreeNode root, int key) {
        TreeNode newNode = new TreeNode(key);
        if (root == null) {
            return newNode;
        }
        TreeNode prev = null;
        TreeNode cur = root;
        while (cur != null) {
            prev = cur;
            if (cur.key > key) {
                cur = cur.left;
            } else if (cur.key < key) {
                cur = cur.right;
            } else {
                return root;
            }
        }
        if (prev.key > key) {
            prev.left = newNode;
        } else {
            prev.right = newNode;
        }
        return root;
    }
}

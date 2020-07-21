import java.util.*;

public class DeleteInBST {
    public TreeNode deleteTree(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // Find target node
        if (root.key > key) {
            root.left = deleteTree(root.left, key);
            return root;
        } else if (root.key < key) {
            root.right = deleteTree(root.right, key);
            return root;
        }
        // Guarantee root != null && root.key == target
        // Case 1: The node to be deleted has no child
        // Case 2 & 3: The node to be deleted has no left / right child
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }
        // Guarantee root.left != null && root.right != null
        // Case 4: The node to be deleted has both left and right child, so
        //     	     we need to move some nodes from right subtree to replace it
        // Case 4.1: node.right does not have left child, meaning itself is the smallest
        if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }
        // Case 4.2: node.right has left child, we need to find the smallest node and move it up
        // 1. find and delete smallest node in root.right
        TreeNode smallest = findSmallest(root.right);
        smallest.left = root.left;
        smallest.right = root.right;
        return smallest;
    }

    public TreeNode findSmallest(TreeNode cur) {
        TreeNode prev = cur;
        cur = cur.left;
        while (cur.left != null) {
            prev = cur;
            cur = cur.left;
        }
        // cur is the smallest one, and prev is its parent
        // Invariance: cur does not have a left child
        prev.left = cur.right;
        return cur;
    }
}

import java.util.ArrayDeque;
import java.util.Queue;

public class CousinsInBinaryTree {
    public boolean checkCousins(TreeNode root, int x, int y) {
        if (root == null || root.key == x || root.key == y) {
            return false;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean findX = false, findY = false;
        TreeNode parentX = null, parentY = null;
        while (!q.isEmpty() && !findX && !findY) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = q.poll();
                // Check left child
                if (node.left != null) {
                    q.offer(node.left);
                    if (node.left.key == x) {
                        parentX = node;
                        findX = true;
                    } else if (node.left.key == y) {
                        parentY = node;
                        findY = true;
                    }
                }
                // Check right child
                if (node.right != null) {
                    q.offer(node.right);
                    if (node.right.key == x) {
                        parentX = node;
                        findX = true;
                    } else if (node.right.key == y) {
                        parentY = node;
                        findY = true;
                    }
                }
            }
        }
        return findX && findY && parentX != parentY;
    }
}

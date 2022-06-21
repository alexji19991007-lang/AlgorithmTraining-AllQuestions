import java.util.*;

public class CheckIfBTIsCompleted {
    public boolean isCompleted(TreeNode root) {
        if (root == null) {
            return true;
        }
        // We must use a linked list here because ArrayDeque does not support null.
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.peek() != null) {
            TreeNode cur = q.poll();
            q.offer(cur.left);
            q.offer(cur.right);
        }
        while (!q.isEmpty() && q.peek() == null) {
            q.poll();
        }
        return q.size() == 0;
    }
}

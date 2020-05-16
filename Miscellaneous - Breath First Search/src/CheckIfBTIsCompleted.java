import java.util.ArrayDeque;
import java.util.Queue;

public class CheckIfBTIsCompleted {
    public boolean isCompleted(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
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

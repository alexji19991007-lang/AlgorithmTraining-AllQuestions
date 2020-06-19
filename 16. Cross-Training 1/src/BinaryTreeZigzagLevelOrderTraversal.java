import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<Integer> zigZag(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        boolean leftToRight = false;
        q.offer(root);
        while (!q.isEmpty()) {
            Deque<Integer> curLevel = new ArrayDeque<>();
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.poll();
                if (leftToRight) {
                    curLevel.offerLast(cur.key);
                } else {
                    curLevel.offerFirst(cur.key);
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            for (int val : curLevel) {
                res.add(val);
            }
            leftToRight = !leftToRight;
        }
        return res;
    }

}

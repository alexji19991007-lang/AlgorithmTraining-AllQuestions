import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        boolean leftToRight = false;
        q.offer(root);
        while (!q.isEmpty()) {
            LinkedList<Integer> curLevel = new LinkedList<>();
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.poll();
                if (leftToRight) {
                    curLevel.offerLast(cur.val);
                } else {
                    curLevel.offerFirst(cur.val);
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(curLevel);
            leftToRight = !leftToRight;
        }
        return res;
    }
}

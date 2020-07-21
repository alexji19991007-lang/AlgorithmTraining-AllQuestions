import java.util.*;

public class InOrderTraversal {
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        helper(root.left, res);
        res.add(root.key);
        helper(root.right, res);
    }

    public List<Integer> inOrderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> mStack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !mStack.isEmpty()) {
            if (cur != null) {
                mStack.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = mStack.pollFirst();
                res.add(cur.key);
                cur = cur.right;
            }
        }
        return res;
    }
}

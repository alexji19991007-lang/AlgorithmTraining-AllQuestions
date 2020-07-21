import java.util.*;

public class PostOrderTraversal {
    public List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.key);
    }

    public List<Integer> postOrderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> mStack = new LinkedList<>();
        TreeNode prev = null;
        mStack.offerFirst(root);
        while (!mStack.isEmpty()) {
            TreeNode cur = mStack.peekFirst();
            if (prev == null || cur == prev.left || cur == prev.right) {
                if (cur.left != null) {
                    mStack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    mStack.offerFirst(cur.right);
                } else {
                    res.add(cur.key);
                    mStack.pollFirst();
                }
            } else if (prev == cur.left) {
                if (cur.right != null) {
                    mStack.offerFirst(cur.right);
                } else {
                    res.add(cur.key);
                    mStack.pollFirst();
                }
            } else {
                res.add(cur.key);
                mStack.pollFirst();
            }
            prev = cur;
        }
        return res;
    }
}

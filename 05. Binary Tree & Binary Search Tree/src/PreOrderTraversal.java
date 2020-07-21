import java.util.*;

public class PreOrderTraversal {
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preHelper(root, res);
        return res;
    }

    public void preHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.key);
        preHelper(root.left, res);
        preHelper(root.right, res);
    }

    public List<Integer> preOrderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> mStack = new LinkedList<>();
        mStack.offerFirst(root);
        while (!mStack.isEmpty()) {
            TreeNode cur = mStack.pollFirst();
            res.add(cur.key);
            // 先放右边再放左边，保证先pop出来的永远是左边
            if (cur.right != null) {
                mStack.offerFirst(cur.right);
            }
            if (cur.left != null) {
                mStack.offerFirst(cur.left);
            }
        }
        return res;
    }
}

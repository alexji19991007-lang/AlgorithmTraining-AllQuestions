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
            // Case 1: Visiting downward
            if (prev == null || cur == prev.left || cur == prev.right) {
                // Continue visiting downward, but only in one direction per time
                if (cur.left != null) {
                    mStack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    mStack.offerFirst(cur.right);
                } else {
                    // If no left or right subtree, then we can add this node to result
                    res.add(cur.key);
                    mStack.pollFirst();
                }
            }
            // Case 2: Returning from left
            else if (prev == cur.left) {
                // See if we can proceed to visiting the right subtree
                if (cur.right != null) {
                    mStack.offerFirst(cur.right);
                } else {
                    // If right subtree, then we can add this node to result
                    res.add(cur.key);
                    mStack.pollFirst();
                }
            }
            // Case 3: Returning from right
            else {
                // Both left and right subtrees are finished, we can add this node to the result
                res.add(cur.key);
                mStack.pollFirst();
            }
            prev = cur;
        }
        return res;
    }
}

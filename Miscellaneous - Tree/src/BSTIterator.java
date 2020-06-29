import java.util.ArrayDeque;
import java.util.Deque;

class BSTIterator {
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new ArrayDeque<>();
        // go left till no more left
        this.leftMostInorder(root);
    }

    private void leftMostInorder(TreeNode root) {
        while (root != null) {
            this.stack.offerFirst(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode top = this.stack.pollFirst();
        if (top.right != null) {
            this.leftMostInorder(top.right);
        }
        return top.key;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return this.stack.size() > 0;
    }
}
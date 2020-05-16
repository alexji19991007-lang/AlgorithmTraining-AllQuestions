import java.util.Stack;

class BSTIterator {
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        // go left till no more left
        this._leftMostInorder(root);
    }

    private void _leftMostInorder(TreeNode root) {
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode top = this.stack.pop();
        if (top.right != null) {
            this._leftMostInorder(top.right);
        }
        return top.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return this.stack.size() > 0;
    }
}
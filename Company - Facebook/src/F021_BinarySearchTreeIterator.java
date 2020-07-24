import java.util.ArrayDeque;
import java.util.Deque;

// LeetCode 173
public class F021_BinarySearchTreeIterator {
    // TC: O(n), go through all nodes in the BST
    // SC: O(n), in the worst case we need to keep a stack of all the nodes in the BST
    Deque<TreeNode> mStack;

    public F021_BinarySearchTreeIterator(TreeNode root) {
        this.mStack = new ArrayDeque<>();
        leftMostInorder(root);
    }

    private void leftMostInorder(TreeNode node) {
        while (node != null) {
            mStack.offerFirst(node);
            node = node.left;
        }
    }

    public int next() {
        if (mStack.isEmpty()) {
            throw new NullPointerException("No More TreeNodes");
        }
        TreeNode cur = mStack.pollFirst();
        if (cur.right != null) {
            leftMostInorder(cur.right);
        }
        return cur.key;
    }

    public boolean hasNext() {
        return !mStack.isEmpty();
    }
}

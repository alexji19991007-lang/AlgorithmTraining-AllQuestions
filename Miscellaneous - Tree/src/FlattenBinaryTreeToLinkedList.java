public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        // although the solution looks like preorder traversal, we should do it in postorder way,
        // i.e. to build the linked list from the end. Thus, we have to set up the right subtree first
        flattenHelper(root, null);
    }

    private TreeNode flattenHelper(TreeNode root, TreeNode pre) {
        // Traverse the tree in post order
        if (root == null) {
            return pre;
        }
        // set right subtree
        pre = flattenHelper(root.right, pre);
        // use the already-setuped right portion to set up the left portion
        pre = flattenHelper(root.left, pre);
        root.right = pre;
        root.left = null;
        // the current node (root) becomes the previous node
        pre = root;
        return pre;
    }
}

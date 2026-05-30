public class ReconstructBinarySearchTreeWithPostOrder {

    public TreeNode reconstruct(int[] post) {
        // We use an array to simulate passing index by reference
        // Start from the last element in postorder (which is the root)
        int[] index = new int[] {post.length - 1};
        // Initial lower bound is Integer.MIN_VALUE
        return helper(post, index, Integer.MIN_VALUE);
    }

    public TreeNode helper(int[] postorder, int[] index, int min) {
        // Base case:
        // 1. All elements are used
        // 2. Current value violates BST constraint (must be > min)
        if (index[0] < 0 || postorder[index[0]] <= min) {
            return null;
        }
        // The current element is the root of the subtree
        TreeNode root = new TreeNode(postorder[index[0]--]);
        // IMPORTANT:
        // Since we are traversing postorder from right to left:
        // Order becomes: root -> right subtree -> left subtree

        // First construct the RIGHT subtree
        // All values in the right subtree must be > root.key
        root.right = helper(postorder, index, root.key);
        // Then construct the LEFT subtree
        // Values must be between (min, root.key)
        root.left = helper(postorder, index, min);
        return root;
    }
}

package Reconstruct;

public class ReconstructBinarySearchTreeFromPostorder {
    public static void main(String[] args) {
        int[] post = {1, 4, 3, 11, 8, 5};
        TreeNode x = reconstruct(post);
        System.out.println(x);
    }

    public static TreeNode reconstruct(int[] post) {
        int[] index = new int[] {post.length - 1};
        return helper(post, index, Integer.MIN_VALUE);
    }

    public static TreeNode helper(int[] postorder, int[] index, int min) {
        if (index[0] < 0 || postorder[index[0]] <= min) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[index[0]--]);
        root.right = helper(postorder, index, root.key);
        root.left = helper(postorder, index, min);
        return root;
    }
}

import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeFromPreorderAndInorder {
    private int[] preorder;
    private int[] inorder;
    // use a hashmap to quickly find the index of a preorder element in the inorder array
    private Map<Integer, Integer> indexMap = new HashMap<>();
    // start from first preorder element
    private int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        int i = 0;
        // set up hashmap
        for (Integer val : inorder) {
            indexMap.put(val, i);
            i++;
        }
        return helper(0, inorder.length);
    }

    private TreeNode helper(int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return null;
        }
        // the current root should be the first elemet in the current preorder array
        // NOTE: the change of first element is achieved by incrementing preIndex
        TreeNode root = new TreeNode(preorder[preIndex]);
        // get the index in the inorder array, so everything on its left is the left subtree. Vice versa.
        int index = indexMap.get(preorder[preIndex]);
        // increment preIndex for recursion
        preIndex++;
        // set up left and right subtree
        // left inclusive, but not right inclusive
        root.left = helper(leftIndex, index);
        root.right = helper(index + 1, rightIndex);
        return root;
    }
}

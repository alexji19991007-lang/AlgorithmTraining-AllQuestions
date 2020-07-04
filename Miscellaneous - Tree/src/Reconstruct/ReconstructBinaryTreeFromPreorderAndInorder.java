package Reconstruct;

import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeFromPreorderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // use a hashmap to quickly find the index of a preorder element in the inorder array
        Map<Integer, Integer> indexMap = new HashMap<>();
        int i = 0;
        // start from first preorder element
        int[] preIndex = {0};
        // set up hashmap
        for (Integer val : inorder) {
            indexMap.put(val, i);
            i++;
        }
        return helper(preorder, inorder, indexMap, preIndex, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] preOrder, int[] inOrder, Map<Integer, Integer> indexMap, int[] preIndex, int inorderLeft, int inorderRight) {
        if (inorderLeft > inorderRight) {
            return null;
        }
        int pre = preIndex[0];
        // the current root should be the first element in the current preorder array
        // NOTE: the change of first element is achieved by incrementing preIndex
        TreeNode root = new TreeNode(preOrder[pre]);
        // get the index in the inorder array, so everything on its left is the left subtree. Vice versa.
        int index = indexMap.get(root.key);
        // increment preIndex for recursion
        preIndex[0]++;
        // set up left and right subtree
        // left inclusive, but not right inclusive
        root.left = helper(preOrder, inOrder, indexMap, preIndex, inorderLeft, index - 1);
        root.right = helper(preOrder, inOrder, indexMap, preIndex, index + 1, inorderRight);
        return root;
    }
}

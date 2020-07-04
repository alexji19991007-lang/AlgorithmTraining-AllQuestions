package Reconstruct;

import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeFromPostorderAndInorder {
    public TreeNode reconstruct(int[] inOrder, int[] postOrder) {
        // use a hashmap to quickly find the index of a post-order element in the inorder array
        Map<Integer, Integer> indexMap = new HashMap<>();
        int[] postIndex = new int[] {postOrder.length - 1};
        int i = 0;
        for (Integer val : inOrder) {
            indexMap.put(val, i++);
        }
        return helper(inOrder, postOrder, indexMap, postIndex, 0, inOrder.length - 1);
    }

    public TreeNode helper(int[] inOrder, int[] postOrder, Map<Integer, Integer> indexMap, int[] postIndex, int inorderLeft, int inorderRight) {
        if (inorderLeft > inorderRight) {
            return null;
        }
        // Pick up postIndex element as our root;
        int post = postIndex[0];
        TreeNode root = new TreeNode(postOrder[post]);
        // Root splits inorder list into left and right subtrees
        int index = indexMap.get(root.key);
        postIndex[0]--;
        // Build right subtree
        root.right = helper(inOrder, postOrder, indexMap, postIndex, index + 1, inorderRight);
        // Build left subtree
        root.left = helper(inOrder, postOrder, indexMap, postIndex, inorderLeft, index - 1);
        return root;
    }
}

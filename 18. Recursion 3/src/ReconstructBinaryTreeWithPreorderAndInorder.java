import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeWithPreorderAndInorder {
    public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
        // Build a map: value -> index in inorder
        // This allows O(1) lookup to split left/right subtree
        Map<Integer, Integer> indexMap = new HashMap<>();
        int i = 0;
        // Use array to simulate pass-by-reference for preorder index
        int[] preIndex = {0};
        for (Integer val : inOrder) {
            indexMap.put(val, i);
            i++;
        }
        // Start recursion on the full inorder range
        return helper(preOrder, indexMap, preIndex, 0, inOrder.length - 1);
    }

    public TreeNode helper(int[] preOrder, Map<Integer, Integer> indexMap, int[] preIndex, int inorderLeft, int inorderRight) {
        // Base case: no elements to construct subtree
        if (inorderLeft > inorderRight) {
            return null;
        }
        // Step 1: pick root from preorder
        // Preorder traversal: root -> left -> right
        int pre = preIndex[0];
        TreeNode root = new TreeNode(preOrder[pre]);
        // Find root position in inorder
        int index = indexMap.get(root.key);
        // Move preorder index forward
        preIndex[0]++;
        // Step 2: build left subtree
        // Elements left of 'index' in inorder belong to left subtree
        root.left = helper(preOrder, indexMap, preIndex, inorderLeft, index - 1);
        // Step 3: build right subtree
        // Elements right of 'index' in inorder belong to right subtree
        root.right = helper(preOrder, indexMap, preIndex, index + 1,inorderRight);
        return root;
    }
}

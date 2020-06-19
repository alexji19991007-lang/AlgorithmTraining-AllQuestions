import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeWithPreorderAndInorder {
    public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int i = 0;
        int[] preIndex = {0};
        for (Integer val : inOrder) {
            indexMap.put(val, i);
            i++;
        }
        return helper(preOrder, inOrder, indexMap, preIndex, 0, inOrder.length - 1);
    }

    public TreeNode helper(int[] preOrder, int[] inOrder, Map<Integer, Integer> indexMap, int[] preIndex, int inorderLeft, int inorderRight) {
        if (inorderLeft > inorderRight) {
            return null;
        }
        int pre = preIndex[0];
        TreeNode root = new TreeNode(preOrder[pre]);
        int index = indexMap.get(root.key);
        preIndex[0]++;
        root.left = helper(preOrder, inOrder, indexMap, preIndex, inorderLeft, index - 1);
        root.right = helper(preOrder, inOrder, indexMap, preIndex, index + 1,inorderRight);
        return root;
    }
}

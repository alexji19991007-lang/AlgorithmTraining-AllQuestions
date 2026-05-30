import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReconstructBinaryTreeWithLevelorderAndInorder {
    public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
        // Build a map: value -> index in inorder
        // This helps us quickly determine whether a node belongs to left or right subtree
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; ++i) {
            inMap.put(inOrder[i], i);
        }
        // Convert levelOrder array to a list (easier to manipulate)
        List<Integer> levelList = new ArrayList<>();
        for (int num : levelOrder) {
            levelList.add(num);
        }

        // Start recursion
        return helper(inMap, levelList);
    }

    public TreeNode helper(Map<Integer, Integer> inMap, List<Integer> levelList) {

        // Base case: no nodes left
        if (levelList.isEmpty()) {
            return null;
        }

        // The FIRST element in level order is always the root
        TreeNode root = new TreeNode(levelList.remove(0));

        // Prepare lists for left and right subtrees
        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();

        // Partition remaining nodes into left/right using inorder positions
        for (int num : levelList) {
            // If index < root index → belongs to left subtree
            if (inMap.get(num) < inMap.get(root.key)) {
                leftSubtree.add(num);
            } else {
                rightSubtree.add(num);
            }
        }

        // Recursively build left and right subtrees
        root.left = helper(inMap, leftSubtree);
        root.right = helper(inMap, rightSubtree);

        return root;
    }
}
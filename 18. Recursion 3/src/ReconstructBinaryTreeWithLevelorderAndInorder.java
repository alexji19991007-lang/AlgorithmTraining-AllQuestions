import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReconstructBinaryTreeWithLevelorderAndInorder {
    public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; ++i) {
            inMap.put(inOrder[i], i);
        }
        List<Integer> levelList = new ArrayList<>();
        for (int num : levelOrder) {
            levelList.add(num);
        }
        return helper(inMap, levelList);
    }

    public TreeNode helper(Map<Integer, Integer> inMap, List<Integer> levelList) {
        if (levelList.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(levelList.remove(0));
        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();
        for (int num : levelList) {
            if (inMap.get(num) < inMap.get(root.key)) {
                leftSubtree.add(num);
            } else {
                rightSubtree.add(num);
            }
        }
        root.left = helper(inMap, leftSubtree);
        root.right = helper(inMap, rightSubtree);
        return root;
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReconstructBinaryTreeFromLevelOrderAndInorder {
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
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : levelList) {
            if (inMap.get(num) < inMap.get(root.val)) {
                left.add(num);
            } else {
                right.add(num);
            }
        }
        root.left = helper(inMap, left);
        root.right = helper(inMap, right);
        return root;
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalSumOfBT {
    public List<Integer> diagonalSum(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> levelSum = new HashMap<>();
        traverse(root, 0, levelSum);
        for (int i = 0; i < levelSum.size(); ++i) {
            res.add(levelSum.get(i));
        }
        return res;
    }

    public void traverse(TreeNode root, int level, Map<Integer, Integer> levelSum) {
        if (root == null) {
            return;
        }
        levelSum.put(level, levelSum.getOrDefault(level, 0) + root.key);
        traverse(root.left, level + 1, levelSum);
        traverse(root.right, level, levelSum);
    }
}

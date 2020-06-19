import java.util.HashSet;
import java.util.Set;

public class BinaryTreePathSumToTarget3 {
    public boolean exist(TreeNode root, int target) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        return findPath(root, 0, target, set);
    }

    public boolean findPath(TreeNode node, int curSum, int target, Set<Integer> set) {
        if (node == null) {
            return false;
        }
        curSum += node.key;
        int prefixSum = curSum - target;
        if (set.contains(prefixSum)) {
            return true;
        }
        boolean needRemove = set.add(curSum);
        if (findPath(node.left, curSum, target, set) || findPath(node.right, curSum, target, set)) {
            return true;
        }
        if (needRemove) {
            set.remove(curSum);
        }
        return false;
    }
}

import java.util.HashMap;
import java.util.Map;

public class BinaryTreePathSumToTarget3 {
    // O(nlogn) for a balanced BT, O(n^2) for the worst case
    public int pathSum(TreeNode root, int sum) {
        int res = 0;
        if (root == null) {
            return res;
        }
        if (sum == root.key) {
            res++;
        }
        // exclude root
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        // include root
        res += pathSum(root.left, sum - root.key);
        res += pathSum(root.right, sum - root.key);
        return res;
    }

    // O(n) using prefix sum & recursive backtracking
    private Map<Integer, Integer> map;

    public int pathSumRecursive(TreeNode root, int sum) {
        this.map = new HashMap<>();
        map.put(0, 1);
        return backtrack(root, 0, sum);
    }

    public int backtrack(TreeNode node, int curSum, int target) {
        if (node == null) {
            return 0;
        }
        // update the prefix sum by adding the current val
        curSum += node.key;
        // get the number of valid path, ended by the current node
        int res = map.getOrDefault(curSum - target, 0);
        // update the map with the current sum, so the map is good to be passed to the next recursion
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        // add the left recursion and right recursion to the current solution
        res += backtrack(node.left, curSum, target) + backtrack(node.right, curSum, target);
        // restore the map, as the recursion goes from the bottom to the top
        map.put(curSum, map.get(curSum) - 1);
        return res;
    }

}

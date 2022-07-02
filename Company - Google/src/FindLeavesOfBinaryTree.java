import java.util.ArrayList;
import java.util.List;

// LeetCode 366
public class FindLeavesOfBinaryTree {
    // TC: O(N)
    // SC: O(N)
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        getHeight(root, res);
        return res;
    }

    public int getHeight(TreeNode node, List<List<Integer>> res) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeight(node.left, res);
        int rightHeight = getHeight(node.right, res);
        int curHeight = Math.max(leftHeight, rightHeight) + 1;
        if (res.size() == curHeight) {
            res.add(new ArrayList<>());
        }
        res.get(curHeight).add(node.val);
        return curHeight;
    }
}

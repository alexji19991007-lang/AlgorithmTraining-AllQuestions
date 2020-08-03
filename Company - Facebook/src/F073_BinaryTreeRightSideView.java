import java.util.*;

// LeetCode 199
public class F073_BinaryTreeRightSideView {
    // TC: O(n)
    // SC: O(d), where d is the diameter of the tree
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> nodeAtDepth = new HashMap<>();
        int maxDepth = -1;
        Deque<TreeNode> nodeStack = new LinkedList<>();
        Deque<Integer> depthStack = new LinkedList<>();
        nodeStack.offerFirst(root);
        depthStack.offerFirst(0);
        while (!nodeStack.isEmpty() && !depthStack.isEmpty()) {
            TreeNode node = nodeStack.pollFirst();
            int depth = depthStack.pollFirst();
            if (node != null) {
                maxDepth = Math.max(maxDepth, depth);
                if (!nodeAtDepth.containsKey(depth)) {
                    nodeAtDepth.put(depth, node.key);
                }
                nodeStack.offerFirst(node.left);
                depthStack.offerFirst(depth + 1);
                nodeStack.offerFirst(node.right);
                depthStack.offerFirst(depth + 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= maxDepth; ++i) {
            res.add(nodeAtDepth.get(i));
        }
        return res;
    }

    // TC: O(n)
    // SC: O(h)
    public List<Integer> rightSideView_DFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        rightHelper(root, 0, res);
        return res;
    }

    public void rightHelper(TreeNode node, int level, List<Integer> res) {
        if (level == res.size()) {
            res.add(node.key);
        }
        if (node.right != null) {
            rightHelper(node.right, level + 1, res);
        }
        if (node.left != null) {
            rightHelper(node.left, level + 1, res);
        }
    }
}

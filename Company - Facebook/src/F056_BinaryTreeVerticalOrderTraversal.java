import java.util.*;

// LeetCode 314
public class F056_BinaryTreeVerticalOrderTraversal {
    // TC: O(n)
    // SC: O(n)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int minX = 0, maxX = 0;
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> indexQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        indexQueue.offer(0);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = nodeQueue.poll();
                int index = indexQueue.poll();
                if (!map.containsKey(index)) {
                    map.put(index, new ArrayList<>());
                }
                map.get(index).add(node.key);
                minX = Math.min(minX, index);
                maxX = Math.max(maxX, index);
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    indexQueue.offer(index - 1);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    indexQueue.offer(index + 1);
                }
            }
        }
        for (int i = minX; i <= maxX; ++i) {
            res.add(map.get(i));
        }
        return res;
    }
}

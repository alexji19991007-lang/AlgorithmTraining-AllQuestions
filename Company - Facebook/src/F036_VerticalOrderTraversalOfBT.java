import java.util.*;

// LeetCode 987
public class F036_VerticalOrderTraversalOfBT {
    // TC: O(nlog(n/k)), where k is the number of columns in the result
    // SC: O(n)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
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
            Map<Integer, List<Integer>> levelMap = new HashMap<>();
            for (int i = 0; i < size; ++i) {
                TreeNode node = nodeQueue.poll();
                int index = indexQueue.poll();
                if (!levelMap.containsKey(index)) {
                    levelMap.put(index, new ArrayList<>());
                }
                levelMap.get(index).add(node.key);
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
            for (int key : levelMap.keySet()) {
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                List<Integer> list = levelMap.get(key);
                Collections.sort(list);
                map.get(key).addAll(list);
            }
        }
        for (int i = minX; i <= maxX; ++i) {
            res.add(map.get(i));
        }
        return res;
    }
}

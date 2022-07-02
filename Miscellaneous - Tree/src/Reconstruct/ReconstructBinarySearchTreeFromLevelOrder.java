package Reconstruct;

import javax.lang.model.util.Elements;
import java.util.ArrayDeque;
import java.util.Queue;

public class ReconstructBinarySearchTreeFromLevelOrder {
    static class TreeNodeExt {
        TreeNode node;
        // node.key is within (min, max)
        // node.left.key is within (min, node.key)
        // node.right.key is within (node.key, max)
        int min, max;

        public TreeNodeExt(TreeNode node, int min, int max) {
            this.node = node;
            this.min = min;
            this.max = max;
        }
    }

    public TreeNode reconstruct(int[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        Queue<TreeNodeExt> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(level[0]);
        queue.add(new TreeNodeExt(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        // [nextLevelStart, ...] --> 后面层的点
        int nextLevelStart = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNodeExt cur = queue.poll();
                // Find valid left child --> its value within (cur.min, cur.node.key)
                if (nextLevelStart < level.length && level[nextLevelStart] > cur.min && level[nextLevelStart] < cur.node.key) {
                    TreeNode left = new TreeNode(level[nextLevelStart++]);
                    queue.offer(new TreeNodeExt(left, cur.min, cur.node.key));
                    cur.node.left = left;
                }
                // Find valid right child --> its value within (cur.node.key, cur.max)
                if (nextLevelStart < level.length && level[nextLevelStart] > cur.node.key && level[nextLevelStart] < cur.max) {
                    TreeNode right = new TreeNode(level[nextLevelStart++]);
                    queue.offer(new TreeNodeExt(right, cur.node.key, cur.max));
                    cur.node.right = right;
                }
            }
        }
        return root;
    }
}

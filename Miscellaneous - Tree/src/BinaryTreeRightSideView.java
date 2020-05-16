import java.util.*;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        // We will use a hash map to record if we have already seen a node at that depth
        // The map will also contain all the nodes that we can see on the right (i.e. the solution)
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<>();
        int maxDepth = -1;

        // We will use two stacks. The first stack to hold the nodes and the second one to hold the
        // depth. Note that two stacks are synchronized all the time, which means if we push a node
        // on to the nodeStack, we must also push a depth on to the depthStack.
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        // Use BFS, push the root first
        nodeStack.push(root);
        depthStack.push(0);
        // Do BFS here
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();
            if (node != null) {
                // Any node at levels shallower than maxDepth cannot be seen
                maxDepth = Math.max(maxDepth, depth);
                // IMPORTANT NOTE: Only the first node that we encounter at a particular depth
                // contains the correct right-view value
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }
                // Why we first push left then right?
                // Because we always want to look at the right side of the tree first, so push it
                // onto stack later will make it be popped out first
                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int depth = 0; depth <= maxDepth; depth++) {
            res.add(rightmostValueAtDepth.get(depth));
        }
        return res;
    }
}

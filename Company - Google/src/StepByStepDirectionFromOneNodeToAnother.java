// LeetCode 2096
public class StepByStepDirectionFromOneNodeToAnother {
    // TC: O(N)
    // SC: O(N)
    public String getDirections(TreeNode root, int startValue, int destValue) {
        if (root == null) {
            return "";
        }
        TreeNode lca = findLCA(root, startValue, destValue);
        StringBuilder start = new StringBuilder();
        findTreeNode(lca, startValue, start, true);
        StringBuilder dest = new StringBuilder();
        findTreeNode(lca, destValue, dest, false);
        dest.reverse();
        return start.append(dest).toString();
    }

    public TreeNode findLCA(TreeNode node, int one, int two) {
        if (node == null) {
            return null;
        }
        if (node.val == one || node.val == two) {
            return node;
        }
        TreeNode leftSearch = findLCA(node.left, one, two);
        TreeNode rightSearch = findLCA(node.right, one, two);
        if (leftSearch != null && rightSearch != null) {
            return node;
        }
        return leftSearch != null ? leftSearch : rightSearch;
    }

    public TreeNode findTreeNode(TreeNode node, int target, StringBuilder sb, boolean isStart) {
        if (node == null) {
            return null;
        }
        if (node.val == target) {
            return node;
        }
        TreeNode leftSearch = findTreeNode(node.left, target, sb, isStart);
        if (leftSearch != null) {
            char toAdd = isStart ? 'U' : 'L';
            sb.append(toAdd);
            return leftSearch;
        }
        TreeNode rightSearch = findTreeNode(node.right, target, sb, isStart);
        if (rightSearch != null) {
            char toAdd = isStart ? 'U' : 'R';
            sb.append(toAdd);
            return rightSearch;
        }
        return null;
    }
}

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Assumption: 1. No parent pointer.
//             2. We are give a list of K nodes, where K >= 2.
//             3. All K targets are guaranteed to exist in the tree.
public class LowestCommonAncestor4 {
    public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
        Set<TreeNode> toFind = new HashSet<>(nodes);
        return findLCA(root, toFind);
    }

    public TreeNode findLCA(TreeNode root, Set<TreeNode> toFind) {
        if (root == null) {
            return null;
        }
        if (toFind.contains(root)) {
            // the root is one of the uppermost node to find.
            return root;
        }
        // Since the nodes are guaranteed to be in the tree. All we have to do is to find the LCA
        // of two uppermost TreeNode.
        TreeNode leftSearch = findLCA(root.left, toFind);
        TreeNode rightSearch = findLCA(root.right, toFind);
        if (leftSearch != null && rightSearch != null) {
            return root;
        }
        return leftSearch != null ? leftSearch : rightSearch;
    }
}

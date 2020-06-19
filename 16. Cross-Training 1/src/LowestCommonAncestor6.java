import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Assumption: 1. No parent pointer.
//             2. We are give a list of M nodes, where M >= 2.
//             3. All M targets are guaranteed to exist in the tree.
public class LowestCommonAncestor6 {
    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
        Set<KnaryTreeNode> set = new HashSet<>(nodes);
        return helper(root, set);
    }

    public KnaryTreeNode helper(KnaryTreeNode root, Set<KnaryTreeNode> set) {
        if (root == null || set.contains(root)) {
            return root;
        }
        KnaryTreeNode found = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode node = helper(child, set);
            if (node == null) {
                continue;
            }
            if (found == null) {
                found = node;
            } else {
                return root;
            }
        }
        return found;
    }
}

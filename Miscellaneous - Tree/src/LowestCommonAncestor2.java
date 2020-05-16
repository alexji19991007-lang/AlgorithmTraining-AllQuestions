import java.util.ArrayList;
import java.util.List;

// Assumption: 1. There is a parent pointer.
//             2. Both targets are not guaranteed to exist in the tree.
public class LowestCommonAncestor2 {
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        List<TreeNodeP> pathA = getPathToRoot(one);
        List<TreeNodeP> pathB = getPathToRoot(two);
        TreeNodeP LCA = null;
        int a = pathA.size() - 1;
        int b = pathB.size() - 1;
        while(a >= 0 && b >= 0) {
            if(pathA.get(a) != pathB.get(b)) {
                break;
            }
            LCA = pathA.get(a);
            a--;
            b--;
        }
        return LCA;
    }

    public List<TreeNodeP> getPathToRoot(TreeNodeP node) {
        List<TreeNodeP> path = new ArrayList<>();
        if(node == null) {
            return path;
        }
        while(node != null) {
            path.add(node);
            node = node.parent;
        }
        return path;
    }
}

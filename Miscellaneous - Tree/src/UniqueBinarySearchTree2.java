import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTree2 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return helper(1, n);
    }

    public List<TreeNode> helper(int m, int n) {
        // m: minimum value of current tree; n: maximum value of current tree
        List<TreeNode> result = new ArrayList<>();
        if (m > n) {
            result.add(null);
            return result;
        }
        for (int i = m; i <= n; i++) {
            // left subtree should range from m to i - 1
            List<TreeNode> ls = helper(m, i - 1);
            // right subtree should range from i + 1 to n
            List<TreeNode> rs = helper(i + 1, n);
            for (TreeNode l : ls) {
                for (TreeNode r : rs) {
                    TreeNode curr = new TreeNode(i);
                    curr.left = l;
                    curr.right = r;
                    result.add(curr);
                }
            }
        }
        return result;
    }
}

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public String[] binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        constructPaths(root, new StringBuilder(), res);
        String[] ans = new String[res.size()];
        return res.toArray(ans);
    }

    public void constructPaths(TreeNode root, StringBuilder sb, List<String> res) {
        if (root == null) {
            return;
        }
        int curLen = sb.length();
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            constructPaths(root.left, sb, res);
            constructPaths(root.right, sb, res);
        }
        sb.delete(curLen, sb.length());
    }
}

import java.util.*;

public class GetKeysInBTInGivenRange {
    public List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> res = new ArrayList<>();
        getHelper(root, res, min, max);
        return res;
    }

    public void getHelper(TreeNode root, List<Integer> res, int min, int max) {
        if (root == null) {
            return;
        }
        if (root.key > min) {
            getHelper(root.left, res, min, max);
        }
        if (root.key >= min && root.key <= max) {
            res.add(root.key);
        }
        if (root.key < max) {
            getHelper(root.right, res, min, max);
        }
    }
}

import java.util.*;

public class HeightOfBinaryTree {
    public int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
    }

    // Time: O(n) where n is the total number of nodes in the tree
    // Space: O(n) --> 拉成了一条线
}

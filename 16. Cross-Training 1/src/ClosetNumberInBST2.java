import java.util.ArrayDeque;
import java.util.Deque;

public class ClosetNumberInBST2 {
    public int[] closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> res = new ArrayDeque<>();
        helper(res, root, target, k);
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = res.pollFirst();
        }
        return ans;
    }

    public void helper(Deque<Integer> res, TreeNode root, double target, int k) {
        if (root == null) {
            return;
        }
        helper(res, root.left, target, k);
        if (res.size() == k) {
            if (Math.abs(root.key - target) < Math.abs(res.peekFirst() - target)) {
                res.pollFirst();
            } else {
                return;
            }
        }
        res.offerLast(root.key);
        helper(res, root.right, target, k);
    }

}

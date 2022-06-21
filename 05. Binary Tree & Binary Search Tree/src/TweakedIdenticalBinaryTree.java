public class TweakedIdenticalBinaryTree {
    // TC: O(4^(logn)) = O(2^(2logn)) = O(2^log(n^2)) = O(n^2)， assuming the tree is balanced
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null || one.key != two.key) {
            return false;
        }
        return (isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right))
                ||
                (isTweakedIdentical(one.right, two.left) && isTweakedIdentical(one.left, two.right));
    }
}

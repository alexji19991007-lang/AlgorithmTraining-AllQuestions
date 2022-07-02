package Reconstruct;

public class ReconstructBinarySearchTreeFromPreorder {
    public TreeNode reconstruct(int[] pre) {
        int[] index = {0};
        return helper(pre, index, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public TreeNode helper(int[] pre, int[] index, int max, int min) {
        if (index[0] == pre.length) {
            return null;
        }
        int val = pre[index[0]];
        if (val > max || val < min) {
            return null;
        }
        index[0]++;
        TreeNode newNode = new TreeNode(val);
        newNode.left = helper(pre, index, val, min);
        newNode.right = helper(pre, index, max, val);
        return newNode;
    }
}

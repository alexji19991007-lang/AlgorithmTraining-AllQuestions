import java.util.LinkedList;
import java.util.List;

public class ClosestBinarySearchTreeValue2 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        helper(res, root, target, k);
        return res;
    }

    public void helper(LinkedList<Integer> res, TreeNode root, double target, int k) {
        if (root == null) {
            return;
        }
        // We first go left (inorder traversal makes the sequence ordered from small to large)
        helper(res, root.left, target, k);
        // if the size of the linked list has already reached k, we should either remove the first
        // node or finished our search
        if (res.size() == k) {
            // let's say k = 2 and we have two numbers n1, n2 in the current linked list (n1 < n2 by the nature of our traversal sequence)
            // Case 1: if n3 is closer to target than n1, n2 must also be closer to the target than
            //         n1, thus remove n1, add n3
            // Case 2: if n3 is not closer to target than n1, there is no need to do further searches
            //         because n3 > n1 and any number coming after n3 will only be bigger than n3,
            //         so there is no way for the following numbers to be closer to the target
            if (Math.abs(root.key - target) < Math.abs(res.peekFirst() - target)) {
                res.removeFirst();
            } else {
                return;
            }
        }
        // add the current node's value
        res.add(root.key);
        // search right
        helper(res, root.right, target, k);
    }
}

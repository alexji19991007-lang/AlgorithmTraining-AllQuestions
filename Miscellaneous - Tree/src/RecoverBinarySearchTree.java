import java.util.Collections;
import java.util.List;

// Given a Binary Search Tree with only two nodes swapped. Try to find them and recover the binary search tree.

public class RecoverBinarySearchTree {
    // First, consider a simpler problem. What if we are asked to recover a sorted list with only two elements swapped?
    public List<Integer> recoverSortedList(List<Integer> nums) {
        int n = nums.size();
        int x = -1, y = -1;
        for(int i = 0; i < n - 1; ++i) {
            if (nums.get(i + 1) < nums.get(i)) {
                y = i + 1;
                // first swap occurrence (maybe we only have to swap the current two elements)
                if (x == -1) {
                    x = i;
                } else {
                    // second swap occurrence (if we find another disordered element, then we swap the two elements
                    // found)
                    break;
                }
            }
        }
        Collections.swap(nums, x, y);
        return nums;
    }

    // With the solution of the above problem in our mind, we can solve the BST problem by inorder traversal.
    public TreeNode recover(TreeNode root) {
        if (root == null) {
            return null;
        }
        // twoSwappedAndPred[0 & 1]: two nodes to be swapped
        //                  [2]: predecessor to be compared with.
        TreeNode[] twoSwappedAndPred = new TreeNode[3];
        findTwoSwapped(root, twoSwappedAndPred);
        swap(twoSwappedAndPred[0], twoSwappedAndPred[1]);
        return root;
    }

    public void findTwoSwapped(TreeNode root, TreeNode[] twoSwappedAndPred) {
        if (root == null) {
            return;
        }
        findTwoSwapped(root.left, twoSwappedAndPred);
        // pred should be smaller than our current node's value as we are doing inorder traversal
        if (twoSwappedAndPred[2] != null && twoSwappedAndPred[2].key > root.key) {
            twoSwappedAndPred[1] = root;
            if (twoSwappedAndPred[0] == null) {
                twoSwappedAndPred[0] = twoSwappedAndPred[2];
            } else {
                return;
            }
        }
        twoSwappedAndPred[2] = root;
        findTwoSwapped(root.right, twoSwappedAndPred);
    }

    public void swap(TreeNode x, TreeNode y) {
        int temp = x.key;
        x.key = y.key;
        y.key = temp;
    }
}

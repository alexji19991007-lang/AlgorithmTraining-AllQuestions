import java.util.*;

public class ConstructAndDeconstructBinaryTree {
    public static void main(String[] args) {
        ConstructAndDeconstructBinaryTree test = new ConstructAndDeconstructBinaryTree();
//        Integer[] input = new Integer[]{1, 2, 3, 4, null, null, 5, 6, 7, 8, 9, null, null, 10, 11};
        Integer[] input = new Integer[]{1, 2, null, null, 4};
        List<Integer> inputList = new ArrayList<>(Arrays.asList(input));
        TreeNode root = test.construct(inputList);
        List<Integer> outputList = test.deconstruct(root);
        System.out.println(outputList.toString());
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            this.value = x;
            this.left = null;
            this.right = null;
        }
    }

    public List<Integer> deconstruct(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // use queue to do layer traversal
        Queue<TreeNode> queue = new LinkedList<>();
        // list is used to store the keys
        List<Integer> ls = new ArrayList<>();
        // add the root to queue
        queue.offer(root);
        while (!queue.isEmpty()) {
            // current size of queue
            int size = queue.size();
            for (int i = size; i >= 0; --i) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    ls.add(cur.value);
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                } else {
                    ls.add(null);
                }
            }
        }
        while (ls.get(ls.size() - 1) == null) {
            ls.remove(ls.size() - 1);
        }
        return ls;
    }

    public TreeNode construct(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        Queue<TreeNode> mQueue = new ArrayDeque<>();
        TreeNode root = new TreeNode(list.get(0));
        mQueue.offer(root);
        int start = 1;
        while (!mQueue.isEmpty() && start < list.size()) {
            int end = Math.min(start + mQueue.size() * 2, list.size());
            while (start < end) {
                TreeNode cur = mQueue.poll();
                Integer val1 = list.get(start++);
                if (val1 != null) {
                    cur.left = new TreeNode(val1);
                    mQueue.offer(cur.left);
                }
                Integer val2 = list.get(start++);
                if (val2 != null) {
                    cur.right = new TreeNode(val2);
                    mQueue.offer(cur.right);
                }
            }
        }
        return root;
    }
}

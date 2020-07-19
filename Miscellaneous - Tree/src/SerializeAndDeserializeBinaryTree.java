import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
    public static final String N = "null";
    public static final char SPLITTER = ',';

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(3);
        root.left = a;
        root.right = b;
        a.left = null;
        a.right = null;
        b.left = new TreeNode(4);
        b.right = new TreeNode(5);

        SerializeAndDeserializeBinaryTree test = new SerializeAndDeserializeBinaryTree();
        String serializedTree = test.serialize(root);
        System.out.println(serializedTree);
        TreeNode mRoot = test.deserialize(serializedTree);
        System.out.println(test.serialize(mRoot));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int lastIndexNotNull = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.poll();
                if (cur != null) {
                    res.append(cur.key);
                    lastIndexNotNull = res.length() - 1;
                    q.offer(cur.left);
                    q.offer(cur.right);
                } else {
                    res.append(N);
                }
                res.append(SPLITTER);
            }
        }
        return res.substring(0, lastIndexNotNull + 1);
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.equals("null")) {
            return null;
        }
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> q = new ArrayDeque<>();
        int index = 1;
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = q.poll();
                for (int j = index; j < index + 2 && j < values.length; ++j) {
                    if (values[j].equals(N)) {
                        if (j % 2 == 1) {
                            cur.left = null;
                        } else {
                            cur.right = null;
                        }
                    } else {
                        TreeNode next = new TreeNode(Integer.parseInt(values[j]));
                        q.offer(next);
                        if (j % 2 == 1) {
                            cur.left = next;
                        } else {
                            cur.right = next;
                        }
                    }
                }
                index += 2;
            }
        }
        return root;
    }
}

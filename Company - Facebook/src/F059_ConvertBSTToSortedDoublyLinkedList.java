// LeetCode 426
public class F059_ConvertBSTToSortedDoublyLinkedList {
    // TC: O(n) where n is the number of nodes in the BST
    // SC: O(h) keep a recursion stack of the height of the tree
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node[] first = new Node[1];
        Node[] last = new Node[1];
        helper(root, first, last);
        first[0].left = last[0];
        last[0].right = first[0];
        return first[0];
    }

    public void helper(Node node, Node[] first, Node[] last) {
        if (node == null) {
            return;
        }
        helper(node.left, first, last);
        if (last[0] == null) {
            first[0] = node;
        } else {
            last[0].right = node;
            node.left = last[0];
        }
        last[0] = node;
        helper(node.right, first, last);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}

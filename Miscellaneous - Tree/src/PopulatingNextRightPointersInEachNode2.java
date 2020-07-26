public class PopulatingNextRightPointersInEachNode2 {
    // 1            1
    //            /   \
    // 2         2     3
    //          /     / \
    // 3       4      5  6
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node[] leftMost = new Node[]{root};
        while (leftMost[0] != null) {
            Node[] prev = new Node[]{null};
            // 注意当cur在n层的时候，我们实际上process的是n + 1层的next pointers
            // cur要从第n层的最左边一个node开始，开始后通过已经process好的next pointers就可以access到第n层的其他node了
            // E.g. 上图中当cur指向2的时候，我们实际在process第三层。
            //      我们已经有了2这个node，那么第二层的所有node都可以通过2的next pointers获得
            //      同时第三层的所有node都可以通过第二层的left & right pointers获得
            Node cur = leftMost[0];
            leftMost[0] = null;
            while (cur != null) {
                processChild(cur.left, leftMost, prev);
                processChild(cur.right, leftMost, prev);
                cur = cur.next;
            }
        }
        return root;
    }

    public void processChild(Node childNode, Node[] leftMost, Node[] prev) {
        if (childNode == null) {
            return;
        }
        // 如果prev是null的话，说明我们刚开始process这一个level，所以需要吧leftMost[0]设置成当前node
        // 如果prev不是null的话，直接连next就行
        if (prev[0] != null) {
            prev[0].next = childNode;
        } else {
            leftMost[0] = childNode;
        }
        prev[0] = childNode;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

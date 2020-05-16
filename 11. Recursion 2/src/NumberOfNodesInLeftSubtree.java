public class NumberOfNodesInLeftSubtree {
    static class TreeNodeLeft {
        public int key;
        public TreeNodeLeft left;
        public TreeNodeLeft right;
        public int numNodesLeft;

        public TreeNodeLeft(int key) {
            this.key = key;
        }
    }

    public void numNodesLeft(TreeNodeLeft root) {
        numNode(root);
    }

    public int numNode(TreeNodeLeft root) {
        if (root == null) {
            return 0;
        }
        int numLeftNodes = numNode(root.left);
        int numRightNodes = numNode(root.right);
        root.numNodesLeft = numLeftNodes;
        return numLeftNodes + numRightNodes + 1;
    }
}

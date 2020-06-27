public class DistanceBetweenTwoNodesInBST {
    public int disBST(TreeNode root, TreeNode n1, TreeNode n2) {
        if (root == null) {
            return -1;
        }
        TreeNode LCA = n1.key < n2.key ? findLCA(root, n1, n2) : findLCA(root, n2, n1);
        int distLCA_to_n1 = findDist(LCA, n1);
        int distLCA_to_n2 = findDist(LCA, n2);
        return distLCA_to_n1 + distLCA_to_n2;
    }

    public TreeNode findLCA(TreeNode root, TreeNode n1, TreeNode n2) {
        while (root != null && (root.key < n1.key || root.key > n2.key)) {
            if (root.key < n1.key) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;
    }

    private int findDist(TreeNode LCA, TreeNode node) {
        int result = 0;
        while (LCA != null && LCA != node) {
            if (node.key < LCA.key) {
                LCA = LCA.left;
            } else {
                LCA = LCA.right;
            }
            result++;
        }
        return result;
    }
}



import java.util.ArrayList;
import java.util.List;

public class PathSum2 {
    private List<List<Integer>> pathList;
    private List<Integer> pathNodes;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        this.pathList = new ArrayList<>();
        this.pathNodes = new ArrayList<>();
        recurseTree(root, sum);
        return pathList;
    }

    public void recurseTree(TreeNode node, int remain) {
        if (node == null) {
            return;
        }
        pathNodes.add(node.val);
        if (remain == node.val && node.left == null && node.right == null) {
            pathList.add(new ArrayList<>(pathNodes));
        } else {
            recurseTree(node.left, remain - node.val);
            recurseTree(node.right, remain - node.val);
        }
        pathNodes.remove(pathNodes.size() - 1);
    }
}

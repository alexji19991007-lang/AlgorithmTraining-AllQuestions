package CitadelHackerrank;

import java.util.*;

public class SpecialNodes {
    public static void main(String[] args) {
        SpecialNodes test = new SpecialNodes();
        List<Integer> treeFrom = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 1, 1));
        List<Integer> treeTo = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7));
        System.out.println(Arrays.toString(test.specialNodes(7, treeFrom, treeTo)));
    }

    public int[] specialNodes(int treeNodes, List<Integer> treeFrom, List<Integer> treeTo) {
        List<List<Integer>> nodeNeighbors = new ArrayList<>();
        List<Set<Integer>> nodeNeighborsSet = new ArrayList<>(treeNodes);
        for (int i = 0; i < treeNodes; ++i) {
            nodeNeighbors.add(new ArrayList<>());
            nodeNeighborsSet.add(new HashSet<>());
        }
        for (int i = 0; i < treeFrom.size(); ++i) {
            nodeNeighbors.get(treeFrom.get(i) - 1).add(treeTo.get(i) - 1);
            nodeNeighbors.get(treeTo.get(i) - 1).add(treeFrom.get(i) - 1);
        }
        for (int i = 0; i < treeNodes; ++i) {
            nodeNeighborsSet.get(i).addAll(nodeNeighbors.get(i));
        }
        Set<Integer> allNodesSet = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < treeNodes; ++i) {
            allNodesSet.add(i);
            if (nodeNeighborsSet.get(i).size() == 1) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty() && allNodesSet.size() > 2) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; ++i) {
                int curFromNode = queue.poll();
                Integer[] curToNodeList = nodeNeighborsSet.get(curFromNode).toArray(new Integer[nodeNeighborsSet.get(curFromNode).size()]);
                allNodesSet.remove(curFromNode);
                nodeNeighborsSet.get(curToNodeList[0]).remove(curFromNode);
                if (nodeNeighborsSet.get(curToNodeList[0]).size() == 1) {
                    queue.offer(curToNodeList[0]);
                }
            }
        }
        Integer[] allNodesArray = allNodesSet.toArray(new Integer[allNodesSet.size()]);
        List<Integer> index1 = new ArrayList<>();
        List<Integer> index2 = new ArrayList<>();
        int[] depth = {0};
        if (allNodesSet.size() == 1) {
            dfs(allNodesArray[0], -1, nodeNeighbors, 0, depth, index1);
        } else {
            dfs(allNodesArray[0], allNodesArray[1], nodeNeighbors, 0, depth, index1);
            dfs(allNodesArray[1], allNodesArray[0], nodeNeighbors, 0, depth, index2);
        }
        int[] solution = new int[treeNodes];
        for (int i : index1) {
            solution[i] = 1;
        }
        for (int i : index2) {
            solution[i] = 1;
        }
        return solution;
    }

    public void dfs(int node, int prevNode, List<List<Integer>> nodeNeighbors, int depth, int[] maxDepth, List<Integer> nodesOnDiameter) {
        if (depth > maxDepth[0]) {
            maxDepth[0] = depth;
            nodesOnDiameter.clear();
        }
        if (depth == maxDepth[0]) {
            nodesOnDiameter.add(node);
        }
        for (int nextNode : nodeNeighbors.get(node)) {
            if (nextNode != prevNode) {
                dfs(nextNode, node, nodeNeighbors, depth + 1, maxDepth, nodesOnDiameter);
            }
        }
    }
}

package Karat;

import java.util.*;

public class CommonAncestor {
    public static void main(String[] args) {
        CommonAncestor test = new CommonAncestor();
        int[][] pairs = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 9}};
        for (List<Integer> lst : test.zeroOrOneParent(pairs)) {
            System.out.println(lst.toString());
        }
        System.out.println();
        System.out.println(test.hasCommonAncestor(pairs, 8, 5));
        System.out.println();
        System.out.println(test.earliestAncestor(pairs, 9));
    }

    public List<List<Integer>> zeroOrOneParent(int[][] pairs){
        Map<Integer, Node> relations = establishRelations(pairs);
        List<Integer> zeroAncestor = new ArrayList<>(), oneAncestor = new ArrayList<>();
        for (Map.Entry<Integer, Node> entry : relations.entrySet()) {
            if (entry.getValue().parents.size() == 0) {
                zeroAncestor.add(entry.getKey());
            } else if (entry.getValue().parents.size() == 1) {
                oneAncestor.add(entry.getKey());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(zeroAncestor);
        ans.add(oneAncestor);
        return ans;
    }

    public boolean hasCommonAncestor(int[][] pairs, int node1, int node2) {
        Map<Integer, Node> relations = establishRelations(pairs);
        Node nodeOne = relations.get(node1);
        Node nodeTwo = relations.get(node2);
        Set<Node> nodeOneAncestor = new HashSet<>();
        getAncestors(nodeOne, nodeOneAncestor);
        Set<Node> nodeTwoAncestor = new HashSet<>();
        getAncestors(nodeTwo, nodeTwoAncestor);
        for (Node a1 : nodeOneAncestor) {
            if (nodeTwoAncestor.contains(a1)) {
                return true;
            }
        }
        return false;
    }

    public Integer earliestAncestor(int[][] pairs, int node) {
        Map<Integer, Node> relations = establishRelations(pairs);
        Node first = relations.get(node);
        Node potentialSolution = first;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(first);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Node cur = queue.poll();
                potentialSolution = cur;
                for (Node p : cur.parents) {
                    queue.offer(p);
                }
            }
        }
        return potentialSolution.val == first.val ? null : potentialSolution.val;
    }

    private Map<Integer, Node> establishRelations(int[][] pairs) {
        Map<Integer, Node> valToNode = new HashMap<>();
        for (int[] pair : pairs) {
            Node parent = valToNode.getOrDefault(pair[0], new Node(pair[0]));
            Node child = valToNode.getOrDefault(pair[1], new Node(pair[1]));
            parent.parentToChild(child);
            valToNode.put(pair[0], parent);
            valToNode.put(pair[1], child);
        }
        return valToNode;
    }

    private void getAncestors(Node node, Set<Node> ancestors) {
        for (Node p : node.parents) {
            ancestors.add(p);
            getAncestors(p, ancestors);
        }
    }

    static class Node {
        int val;
        Set<Node> parents;
        Set<Node> children;

        public Node(int val) {
            this.val = val;
            this.parents = new HashSet<>();
            this.children = new HashSet<>();
        }

        public void parentToChild(Node child) {
            this.children.add(child);
            child.parents.add(this);
        }
    }
}

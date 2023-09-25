package Codility;

import java.util.*;

public class MaximumNetworkRank {
    public static void main(String[] args) {
        MaximumNetworkRank test = new MaximumNetworkRank();
        System.out.println(test.maxRank(new int[]{1, 2, 3, 3}, new int[]{2, 3, 1, 4}, 4));
    }

    static class Node {
        int number;
        List<Node> neighbors;

        public Node(int number) {
            this.number = number;
            this.neighbors = new ArrayList<>();
        }
    }

    public int maxRank(int[] A, int[] B, int N) {
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
            Node nodeA = nodeMap.getOrDefault(A[i], new Node(A[i]));
            Node nodeB = nodeMap.getOrDefault(B[i], new Node(B[i]));
            nodeA.neighbors.add(nodeB);
            nodeB.neighbors.add(nodeA);
            nodeMap.put(A[i], nodeA);
            nodeMap.put(B[i], nodeB);
        }
        return 0;
    }
}

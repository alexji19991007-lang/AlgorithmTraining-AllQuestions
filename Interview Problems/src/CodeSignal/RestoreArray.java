package CodeSignal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestoreArray {
    public static void main(String[] args) {
        RestoreArray tst = new RestoreArray();
        int[][] pairs = {{5, 3}, {1, 5}, {7, 3}, {4, 6}, {2, 1}, {2, 4}};
        System.out.println(Arrays.toString(tst.restore(pairs)));
    }

    public int[] restore(int[][] pairs) {
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int[] pair : pairs) {
            Node x = nodeMap.getOrDefault(pair[0], new Node(pair[0], null, null));
            Node y = nodeMap.getOrDefault(pair[1], new Node(pair[1], null, null));
            if (x.right == null && y.left == null) {
                x.right = y;
                y.left = x;
            } else if (x.left == null && y.right == null) {
                x.left = y;
                y.right = x;
            } else if (x.left == null && y.left == null) {
                x = reverseRight(x);
                x.right = y;
                y.left = x;
            } else {
                x = reverseRight(x);
                x.left = y;
                y.right = x;
            }
            nodeMap.put(pair[0], x);
            nodeMap.put(pair[1], y);
        }
        int[] res = new int[nodeMap.size()];
        Node firstNode = null;
        for (int key : nodeMap.keySet()) {
            if (nodeMap.get(key).right == null) {
                firstNode = nodeMap.get(key);
                break;
            }
        }
        for (int i = 0; i < res.length; ++i) {
            res[i] = firstNode.val;
            firstNode = firstNode.left;
        }
        return res;
    }

    public Node reverseRight(Node head) {
        Node newHead = head;
        while (newHead.right != null) {
            newHead = newHead.right;
        }
        Node tail = newHead;
        while (head != tail) {
            int temp = head.val;
            head.val = tail.val;
            tail.val = temp;
            head = head.right;
            tail = tail.left;
        }
        return newHead;
    }

    public Node reverseLeft(Node head) {
        Node newHead = head;
        while (newHead.left != null) {
            newHead = newHead.left;
        }
        Node tail = newHead;
        while (head != tail) {
            int temp = head.val;
            head.val = tail.val;
            tail.val = temp;
            head = head.left;
            tail = tail.right;
        }
        return newHead;
    }

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

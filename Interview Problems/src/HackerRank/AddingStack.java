package HackerRank;

public class AddingStack {
    public static void main(String[] args) {
        AddingStack test = new AddingStack();
        test.push(4);
        test.push(5);
        test.inc(2, 1);
        System.out.println(test.pop());
        System.out.println(test.pop());
    }

    private final Node head;
    private final Node tail;
    private long sum;
    private int size;

    public AddingStack() {
        this.head = new Node(-1);
        this.tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        this.sum = 0;
        this.size = 0;
    }

    public void push(int v) {
        Node newNode = new Node(v);
        Node nextNode = head.next;
        newNode.next = nextNode;
        nextNode.prev = newNode;
        head.next = newNode;
        newNode.prev = head;
        sum += v;
        size++;
    }

    public int pop() {
        if (empty()) {
            return -1;
        }
        Node res = head.next;
        Node nextNode = head.next.next;
        nextNode.prev = head;
        head.next = nextNode;
        res.next = null;
        res.prev = null;
        size--;
        return res.val;
    }

    public void inc(int i, int v) {
        Node cur = tail.prev;
        for (int j = 0; j < i && cur != head; ++j) {
            cur.val += v;
            cur = cur.prev;
        }
    }

    public boolean empty() {
        return size == 0;
    }

    public int peek() {
        if (empty()) {
            return -1;
        }
        return head.next.val;
    }

    public long sum() {
        return sum;
    }

    static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
}

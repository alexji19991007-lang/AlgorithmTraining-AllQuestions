public class MyLinkedList {
    int value;
    MyLinkedList next;
    MyLinkedList head;

    public MyLinkedList() {}

    public int get(int index) {
        //return -1 if the list is empty.
        if (head == null) return -1;
        //set current to head to start with
        MyLinkedList cur = head;
        //move to the No.index node
        while(index-- > 0) {
            //if we reach end unexpectedly, out of range, return -1
            if (cur.next == null) return -1;
            //move to the next node
            cur = cur.next;
        }
        //if the index doesn't go out of range, return the node found.
        return cur.value;
    }

    public void addAtHead(int val) {
        MyLinkedList headNode = new MyLinkedList();
        headNode.value = val;
        headNode.next = head;
        head = headNode;
    }

    public void addAtTail(int val) {
        MyLinkedList tailNode = new MyLinkedList();
        tailNode.value = val;
        if(head == null) {head = tailNode;return;}
        MyLinkedList cur = head;
        while (cur.next!=null) cur = cur.next;
        cur.next = tailNode;
    }

    public void addAtIndex(int index, int val) {
        if(index == 0) {addAtHead(val); return;}
        MyLinkedList newNode = new MyLinkedList();
        newNode.value = val;
        if (head == null) return;
        MyLinkedList pre = head;
        MyLinkedList cur = pre.next;
        while(index-- > 1 && cur != null) {
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = newNode;
        newNode.next = cur;
    }

    public void deleteAtIndex(int index) {
        if (index == 0) {head = head.next; return;}
        MyLinkedList pre = head;
        MyLinkedList cur = pre.next;
        while (index-- > 1 && cur != null) {
            pre = pre.next;
            cur = cur.next;
        }
        if (cur != null) {pre.next = cur.next;}
    }
}
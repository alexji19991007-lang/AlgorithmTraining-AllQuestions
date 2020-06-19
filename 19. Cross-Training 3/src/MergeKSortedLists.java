import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public ListNode merge(List<ListNode> listOfLists) {
        if (listOfLists == null || listOfLists.size() == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(listOfLists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                if (l1.value == l2.value) {
                    return 0;
                }
                return l1.value < l2.value ? -1 : 1;
            }
        });
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode ptr = dummy;
        for (ListNode x : listOfLists) {
            if (x != null) {
                heap.add(x);
            }
        }
        while (!heap.isEmpty()) {
            ptr.next = heap.poll();
            ptr = ptr.next;
            if (ptr.next != null) {
                heap.add(ptr.next);
            }
        }
        return dummy.next;
    }
}

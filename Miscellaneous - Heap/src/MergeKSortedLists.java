import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode ptr = dummy;
        for (ListNode x : lists) {
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
    // Time Complexity: O(Nlogk)
    // We have N nodes to insert and for each insertion we need to compare k items which takes logk time


}

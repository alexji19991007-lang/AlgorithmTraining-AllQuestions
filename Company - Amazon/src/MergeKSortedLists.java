import java.util.List;
import java.util.PriorityQueue;

// LeetCode 23
public class MergeKSortedLists {
    // TC: O(nlogk), the comparison cost will be reduced to O(logk) for every pop & insert.
    //     but finding the smallest value takes only O(1). In total N nodes in the final list.
    // SC: O(n + k) = O(n) since k should be far smaller than n, create a final list of size n,
    //     and a priority queue of size k.
    public ListNode merge(List<ListNode> listOfLists) {
        if (listOfLists == null || listOfLists.size() == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(listOfLists.size(), (l1, l2) -> {
            if (l1.value == l2.value) {
                return 0;
            }
            return l1.value < l2.value ? -1 : 1;
        });
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode ptr = dummy;
        for (ListNode x : listOfLists) {
            if (x != null) {
                heap.offer(x);
            }
        }
        while (!heap.isEmpty()) {
            ptr.next = heap.poll();
            ptr = ptr.next;
            if (ptr.next != null) {
                heap.offer(ptr.next);
            }
        }
        return dummy.next;
    }
}

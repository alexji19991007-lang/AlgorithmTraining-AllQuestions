import java.util.Arrays;
import java.util.List;

public class MergeKSortedLists {
    // Use Divide and conquer:
    public ListNode mergeKLists(ListNode[] lists) {
        return helper(Arrays.asList(lists));
    }

    public ListNode helper(List<ListNode> lists) {
        int length = lists.size();

        if (length == 0) {
            return null;
        }
        if (length == 1) {
            return lists.get(0);
        }

        int mid = (length - 1) / 2;
        ListNode l1 = helper(lists.subList(0, mid + 1));
        ListNode l2 = helper(lists.subList(mid + 1, length));

        return mergeTwoSorted(l1, l2);
    }

    // Merge two sorted Linked List
    public ListNode mergeTwoSorted(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode ptr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ptr.next = l1;
                l1 = l1.next;
            } else {
                ptr.next = l2;
                l2 = l2.next;
            }
            ptr = ptr.next;
        }
        while (l1 != null) {
            ptr.next = l1;
            l1 = l1.next;
            ptr = ptr.next;
        }

        while (l2 != null) {
            ptr.next = l2;
            l2 = l2.next;
            ptr = ptr.next;
        }
        return dummy.next;
    }

//    public ListNode mergeKLists(ListNode[] lists) {
//        if (lists == null || lists.length == 0) {
//            return null;
//        }
//        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
//        ListNode dummy = new ListNode(Integer.MIN_VALUE);
//        ListNode ptr = dummy;
//        for (ListNode x : lists) {
//            if (x != null) {
//                heap.add(x);
//            }
//        }
//        while (!heap.isEmpty()) {
//            ptr.next = heap.poll();
//            ptr = ptr.next;
//            if (ptr.next != null) {
//                heap.add(ptr.next);
//            }
//        }
//        return dummy.next;
//    }
//    // Time Complexity: O(Nlogk)
//    // We have N nodes to insert and for each insertion we need to compare k items which takes logk time

}

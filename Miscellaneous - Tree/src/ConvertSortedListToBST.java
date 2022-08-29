// LeetCode 109
public class ConvertSortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        int size = findSize(head);
        ListNode[] curHead = new ListNode[1];
        curHead[0] = head;
        return convert(0, size - 1, curHead);
    }

    public TreeNode convert(int left, int right, ListNode[] curHead) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode leftNode = convert(left, mid - 1, curHead);

        TreeNode node = new TreeNode(curHead[0].value);
        node.left = leftNode;
        curHead[0] = curHead[0].next;

        node.right = convert(mid + 1, right, curHead);
        return node;
    }

    public int findSize(ListNode head) {
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        return size;
    }
}

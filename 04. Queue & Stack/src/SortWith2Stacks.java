import java.util.*;

// Time Complexity: O(n^2)
// Space Complexity: O(n) --> actually no additional memory
public class SortWith2Stacks {
    public void sort(LinkedList<Integer> s1) {
        if (s1 == null || s1.size() <= 1) {
            return;
        }
        Deque<Integer> s2 = new LinkedList<>();
        sortHelper(s1, s2);
    }

    public void sortHelper(Deque<Integer> input, Deque<Integer> buffer) {
        int prevMax = Integer.MAX_VALUE;
        while (input.peekFirst() < prevMax) {
            int curMax = Integer.MIN_VALUE;
            int curMaxCount = 0;
            while (!input.isEmpty() && input.peekFirst() < prevMax) {
                int cur = input.pollFirst();
                if (cur > curMax) {
                    curMax = cur;
                    curMaxCount = 1;
                } else if (cur == curMax) {
                    curMaxCount++;
                }
                buffer.offerFirst(cur);
            }
            while (curMaxCount > 0) {
                input.offerFirst(curMax);
                curMaxCount--;
            }
            while (!buffer.isEmpty()) {
                int temp = buffer.pollFirst();
                if (temp != curMax) {
                    input.offerFirst(temp);
                }
            }
            prevMax = curMax;
        }
    }
}

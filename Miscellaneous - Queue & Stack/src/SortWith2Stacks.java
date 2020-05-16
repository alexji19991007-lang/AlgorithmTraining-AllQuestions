import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SortWith2Stacks {
    public static void main(String[] args) {
        Integer[] input = {3, 6, 7, 5, 2, 1, 8, 4, 0};
        List<Integer> x = Arrays.asList(input);
        LinkedList<Integer> s1 = new LinkedList<>(x);
        sort(s1);
        System.out.println(s1.toString());
    }

    public static void sort(LinkedList<Integer> s1) {
        if (s1 == null || s1.size() <= 1) {
            return;
        }
        Deque<Integer> buffer = new LinkedList<>();
        sortHelper(s1, buffer);
    }

    public static void sortHelper(Deque<Integer> s1, Deque<Integer> buffer) {
        int prevMax = Integer.MAX_VALUE;
        while (!s1.isEmpty() && s1.peekFirst() < prevMax) {
            int curMax = Integer.MIN_VALUE;
            int curMaxCount = 0;
            while (!s1.isEmpty() && s1.peekFirst() < prevMax) {
                int cur = s1.pollFirst();
                if (cur > curMax) {
                    curMax = cur;
                    curMaxCount = 1;
                } else if (cur == curMax) {
                    curMaxCount++;
                }
                buffer.offerFirst(cur);
            }
            while (curMaxCount > 0) {
                s1.offerFirst(curMax);
                curMaxCount--;
            }
            while (!buffer.isEmpty()) {
                int tmp = buffer.pollFirst();
                if (tmp != curMax) {
                    s1.offerFirst(tmp);
                }
            }
            prevMax = curMax;
        }
    }
}

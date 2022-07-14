import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        Integer[] x = {2, 5, 4, 6, 8};
        List<Integer> space = Arrays.asList(x);
        System.out.println(segment(3, space));
        space.sort(Comparator.naturalOrder());
        System.out.println(space);
    }

    public static int segment(int x, List<Integer> space) {
        // Write your code here
        if (space.size() == 0 || x <= 0) {
            return 0;
        }
        int globalMax = Integer.MIN_VALUE;
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < space.size(); ++i) {
            while (!dq.isEmpty() && dq.peekFirst() < i - x + 1) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && space.get(dq.peekLast()) > space.get(i)) {
                dq.pollLast();
            }
            dq.offerLast(i);
            if (i >= x - 1) {
                globalMax = Math.max(globalMax, space.get(dq.peekFirst()));
            }
        }
        return globalMax;
    }
}


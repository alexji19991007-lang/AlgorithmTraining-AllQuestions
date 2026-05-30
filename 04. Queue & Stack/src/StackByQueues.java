import java.util.*;

public class StackByQueues {
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    /** Initialize your data structure here. */
    public StackByQueues() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public Integer pop() {
        if (q1.isEmpty()) {
            return null;
        }
        // Move first size - 1 elements to q2
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }
        // Last element is the stack top
        Integer top = q1.poll();
        // Swap q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return top;
    }

    /** Get the top element. */
    public Integer top() {
        Integer res = pop();
        if (res != null) {
            push(res);
        }
        return res;
    }

    /** Returns whether the stack is empty. */
    public boolean isEmpty() {
        return q1.size() == 0;
    }
}

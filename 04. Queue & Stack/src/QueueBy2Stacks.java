import java.util.*;

public class QueueBy2Stacks {
    private Deque<Integer> in;
    private Deque<Integer> out;

    public QueueBy2Stacks() {
        this.in = new ArrayDeque<>();
        this.out = new ArrayDeque<>();
    }

    public Integer poll() {
        move();
        return out.pollFirst();
    }

    public void offer(int element) {
        in.offerFirst(element);
    }

    public Integer peek() {
        move();
        return out.peekFirst();
    }

    public int size() {
        return in.size() + out.size();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

    private void move() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.offerFirst(in.pollFirst());
            }
        }
    }
}
// offer(): O(1)
// poll() / peek() : Amortized O(1)
// Why amortized?
// Each element:
//      Goes into in once
//      Moves to out once
//      Gets removed once
// So over many operations, cost averages to O(1).
// Worst-case single call: O(n)
// Average: O(1)
import java.util.Deque;
import java.util.LinkedList;

public class DequeBy3Stacks {
    private Deque<Integer> leftHalf;
    private Deque<Integer> rightHalf;
    private Deque<Integer> buffer;

    public DequeBy3Stacks() {
        this.leftHalf = new LinkedList<>();
        this.rightHalf = new LinkedList<>();
        this.buffer = new LinkedList<>();
    }

    public void offerFirst(int element) {
        leftHalf.offerFirst(element);
    }

    public void offerLast(int element) {
        rightHalf.offerFirst(element);
    }

    public Integer pollFirst() {
        move(rightHalf, leftHalf);
        return leftHalf.isEmpty() ? null : leftHalf.pollFirst();
    }

    public Integer pollLast() {
        move(leftHalf, rightHalf);
        return rightHalf.isEmpty() ? null : rightHalf.pollFirst();
    }

    public Integer peekFirst() {
        move(rightHalf, leftHalf);
        return leftHalf.isEmpty() ? null : leftHalf.peekFirst();
    }

    public Integer peekLast() {
        move(leftHalf, rightHalf);
        return rightHalf.isEmpty() ? null : rightHalf.peekFirst();
    }

    public int size() {
        return leftHalf.size() + rightHalf.size();
    }

    public boolean isEmpty() {
        return leftHalf.isEmpty() && rightHalf.isEmpty();
    }

    private void move(Deque<Integer> src, Deque<Integer> dest) {
        if (!dest.isEmpty()) {
            return;
        }
        int halfSize = src.size() / 2;
        for (int i = 0; i < halfSize; ++i) {
            buffer.offerFirst(src.pollFirst());
        }
        while (!src.isEmpty()) {
            dest.offerFirst(src.pollFirst());
        }
        while (!buffer.isEmpty()) {
            src.offerFirst(buffer.pollFirst());
        }
    }
}

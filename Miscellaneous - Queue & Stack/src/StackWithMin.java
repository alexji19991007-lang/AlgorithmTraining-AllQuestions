import java.util.Deque;
import java.util.LinkedList;

public class StackWithMin {
    private Deque<Integer> mStack;
    private Deque<Pair> minRecord;

    public class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return this.key;
        }

        public int getValue() {
            return this.value;
        }
    }

    public StackWithMin() {
        this.mStack = new LinkedList<>();
        this.minRecord = new LinkedList<>();
    }

    public int pop() {
        if (mStack.isEmpty()) {
            return -1;
        }
        int curSize = mStack.size();
        int res = mStack.pop();
        if (res == min() && minRecord.peekFirst().getValue() == curSize) {
            minRecord.pollFirst();
        }
        return res;
    }

    public void push(int element) {
        mStack.offerFirst(element);
        if (mStack.size() == 1) {
            minRecord.offerFirst(new Pair(element, 1));
        } else {
            Pair curMinPair = minRecord.peekFirst();
            int curMin = curMinPair.getKey();
            if (element < curMin) {
                minRecord.offerFirst(new Pair(element, mStack.size()));
            }
        }
    }

    public int top() {
        return mStack.isEmpty() ? -1 : mStack.peekFirst();
    }

    public int min() {
        return mStack.isEmpty() ? -1 : minRecord.peekFirst().getKey();
    }
}

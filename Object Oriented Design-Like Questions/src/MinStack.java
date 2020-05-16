import java.util.Stack;

public class MinStack {
    private int min;
    private Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        this.min = Integer.MAX_VALUE;
        this.stack = new Stack<>();
    }

    public void push(int x) {
        // 如果我们遇到一个比当前min更小的数字，我们先把当前min push到stack上，然后再push当前数字，最后
        // 把当前数字设置为min
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        // 由于我们push操作的功劳，如果我们pop出来的element是当前的min的话，这个element底下的第一个
        // element就会是当前pop完以后的min
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

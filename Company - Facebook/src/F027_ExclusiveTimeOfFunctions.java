import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// LeetCode 636
public class F027_ExclusiveTimeOfFunctions {
    // TC: O(n)
    // SC: O(n/2) = O(n)
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        // The stack's top is the id of the function that is currently under execution
        Deque<Integer> mStack = new ArrayDeque<>();
        int prevTime = 0;
        for (String log : logs) {
            String[] curLog = log.split(":");
            // If the stack is not empty, then we need to update the total running time of the
            // current executing function. The current executing function's id is on top of the stack
            if (!mStack.isEmpty()) {
                res[mStack.peekFirst()] += Integer.parseInt(curLog[2]) - prevTime;
            }
            // Update the prev time
            prevTime = Integer.parseInt(curLog[2]);
            if (curLog[1].equals("start")) {
                // If the current log is starting a new function, then push the new function id
                // onto the stack
                mStack.offerFirst(Integer.parseInt(curLog[0]));
            } else {
                // The ending time unit is also counted toward the function's running time, thus increment
                // the corresponding running time and the prevTime by 1 unit.
                res[mStack.pollFirst()]++;
                prevTime++;
            }
        }
        return res;
    }
}

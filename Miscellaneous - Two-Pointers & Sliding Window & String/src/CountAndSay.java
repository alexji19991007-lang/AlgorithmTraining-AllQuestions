import java.util.LinkedList;
import java.util.List;

public class CountAndSay {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(countAndSay(n));
    }

    public static String countAndSay(int n) {
        LinkedList<Integer> prev = new LinkedList<>();
        prev.add(1);
        // -1 is used as a delimiter to signify the end of one round
        prev.add(-1);
        // get the final sequence
        List<Integer> finalSeq = nextSequence(n, prev);
        // Build the final answer
        StringBuilder seqStr = new StringBuilder();
        for (int digit : finalSeq) {
            seqStr.append(digit);
        }
        return seqStr.toString();
    }

    public static LinkedList<Integer> nextSequence(int n, LinkedList<Integer> prev) {
        if (n <= 1) {
            // remove the -1 delimiter and return
            prev.pollLast();
            return prev;
        }
        LinkedList<Integer> nextSeq = new LinkedList<>();
        int prevDigit = -1;
        int digitCount = 0;
        for (int curDigit : prev) {
            if (prevDigit == -1) {
                // start of a sequence
                prevDigit = curDigit;
                digitCount++;
            } else if (prevDigit == curDigit) {
                // in the middle of consecutive same numbers
                digitCount++;
            } else {
                // we have reached the end of a sequence of consecutive same numbers
                nextSeq.add(digitCount);
                nextSeq.add(prevDigit);
                prevDigit = curDigit;
                digitCount = 1;
                // notice that the last -1 delimiter will no longer be processed as we have reached
                // the end of the for loop
            }
        }
        // add a new delimiter for next recursive call
        nextSeq.add(-1);
        return nextSequence(n - 1, nextSeq);
    }
}

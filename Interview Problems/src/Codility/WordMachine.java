package Codility;

import java.util.ArrayDeque;
import java.util.Deque;

public class WordMachine {
    public int solution(String s) {
        String[] array = s.split(" ");
        Deque<Integer> mStack = new ArrayDeque<>();
        for (String str : array) {
            switch (str) {
                case "+":
                    if (mStack.size() < 2) {
                        return -1;
                    }
                    int additionResult = mStack.pollFirst() + mStack.pollFirst();
                    if (additionResult > 1048575) {
                        return -1;
                    }
                    mStack.offerFirst(additionResult);
                    break;
                case "-":
                    if (mStack.size() < 2) {
                        return -1;
                    }
                    int subtractionResult = mStack.pollFirst() - mStack.pollFirst();
                    if (subtractionResult < 0) {
                        return -1;
                    }
                    mStack.offerFirst(subtractionResult);
                    break;
                case "DUP":
                    if (mStack.isEmpty()) {
                        return -1;
                    }
                    mStack.offerFirst(mStack.peekFirst());
                    break;
                case "POP":
                    if (mStack.isEmpty()) {
                        return -1;
                    }
                    mStack.pollFirst();
                    break;
                default:
                    int operand = Integer.parseInt(str);
                    if (operand < 0 || operand > 1048575) {
                        return -1;
                    }
                    mStack.offerFirst(operand);
                    break;
            }
        }
        return mStack.isEmpty() ? -1 : mStack.pollFirst();
    }
}

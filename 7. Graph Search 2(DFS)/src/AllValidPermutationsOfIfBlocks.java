import java.util.ArrayDeque;
import java.util.Deque;

public class AllValidPermutationsOfIfBlocks {
    public static final String START = "if {";
    public static final String END = "}";
    public static final char LEFT = '{';
    public static final char RIGHT = '}';

    public static void main(String[] args) {
        validPermutation(4);
    }

    public static void validPermutation(int n) {
        char[] solution = new char[n * 2];
        findPermutation(n, 0, 0, 0, solution);
    }

    public static void findPermutation(int n, int index, int left, int right, char[] solution) {
        if (index == 2 * n) {
            printBlock(solution);
            System.out.println("______________________");
            return;
        }
        if (left < n) {
            solution[index] = LEFT;
            findPermutation(n, index + 1, left + 1, right, solution);
        }
        if (right < left) {
            solution[index] = RIGHT;
            findPermutation(n, index + 1, left, right + 1, solution);
        }
    }

    public static void printBlock(char[] solution) {
        Deque<Character> mStack = new ArrayDeque<>();
        int blankSpaces = 0;
        for (char p : solution) {
            if (p == LEFT) {
                // Each tab = 2 * " "
                blankSpaces = mStack.size() * 2;
                for (int i = 0; i < blankSpaces; ++i) {
                    System.out.print(" ");
                }
                System.out.println(START);
                mStack.push(LEFT);
            } else {
                for (int i = 0; i < blankSpaces; ++i) {
                    System.out.print(" ");
                }
                System.out.println(END);
                mStack.pollFirst();
                blankSpaces -= 2;
            }
        }
    }
}
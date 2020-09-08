package RobinhoodOnsite;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class AsteroidCollision {
    public static void main(String[] args) {
        AsteroidCollision test = new AsteroidCollision();
        int[] asteroids = {5, 10, -5};
        System.out.println(Arrays.toString(test.asteroidCollision(asteroids)));
    }

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> mStack = new LinkedList<>();
        for (int ast : asteroids) {
            while (!mStack.isEmpty() && mStack.peekFirst() > 0 && mStack.peekFirst() < -ast) {
                mStack.pollFirst();
            }
            if (mStack.isEmpty() || ast > 0 || mStack.peekFirst() < 0) {
                mStack.offerFirst(ast);
            } else if (ast < 0 && mStack.peekFirst() == -ast) {
                mStack.pollFirst();
            }
        }
        int[] res = new int[mStack.size()];
        for (int i = mStack.size() - 1; i >= 0; --i) {
            res[i] = mStack.pollFirst();
        }
        return res;
    }
}

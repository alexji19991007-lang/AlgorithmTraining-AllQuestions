package HackerRank;

import java.util.ArrayDeque;
import java.util.Queue;

public class CanWeReachFinalPoint {
    public static void main(String[] args) {
        System.out.println(canReach(5, 10, 1, 14, 1));
    }

    public static String canReach(int c, int x1, int y1, int x2, int y2) {
        int[] start = new int[] {x1, y1};
        if (x1 > x2 || y1 > y2) {
            return "No";
        }
        if (isPerfectSquare(start)) {
            return "No";
        }
        if (x1 == x2 && y1 == y2) {
            return "Yes";
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int[][] nextPoints = new int[][] {{cur[0] + cur[1], cur[1]}, {cur[0], cur[0] + cur[1]}, {cur[0] + c, cur[1] + c}};
            for (int[] next : nextPoints) {
                if (!isPerfectSquare(next) && isInBound(next, x2, y2)) {
                    if (next[0] == x2 && next[1] == y2) {
                        return "Yes";
                    }
                    queue.offer(next);
                }
            }
        }
        return "No";
    }

    private static boolean isPerfectSquare(int[] next) {
        double squareRoot = Math.sqrt(next[0] + next[1]);
        return squareRoot - Math.floor(squareRoot) == 0;
    }

    private static boolean isInBound(int[] next, int x2, int y2) {
        return next[0] <= 1000 && next[1] <= 1000 && next[0] <= x2 && next[1] <= y2;
    }
}

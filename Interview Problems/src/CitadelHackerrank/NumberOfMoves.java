package CitadelHackerrank;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class NumberOfMoves {
    private static final int[][] DIRS = new int[][] {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    private static final int MAX_ROW_NUMBER = 8;
    private static final int MAX_COL_NUMBER = 8;

    public static void main(String[] args) {
        NumberOfMoves test = new NumberOfMoves();
        System.out.println(test.numberOfMoves(4, 4, 4, 8));
    }

    public int numberOfMoves(int startRow, int startCol, int endRow, int endCol) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startRow, startCol});
        Set<String> visited = new HashSet<>();
        visited.add(startRow + "," + startCol);

        int numSteps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.poll();
                int curX = cur[0], curY = cur[1];
                if (curX == endRow && curY == endCol) {
                    return numSteps;
                }
                for (int[] dir : DIRS) {
                    int newX = curX + dir[0];
                    int newY = curY + dir[1];
                    String newCoordinate = newX + "," + newY;
                    if (!visited.contains(newCoordinate) &&  withinBoardRange(newX, newY)) {
                        queue.add(new int[] {newX, newY});
                        visited.add(newX + "," + newY);
                    }
                }
            }
            numSteps++;
        }
        return -1;
    }

    private boolean withinBoardRange(int x, int y) {
        return x >= 0 && y >= 0 && x <= MAX_ROW_NUMBER && y <= MAX_COL_NUMBER;
    }
}

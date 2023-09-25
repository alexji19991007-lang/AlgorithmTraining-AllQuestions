package HackerRank;

import java.util.*;

public class AvoidingTheMonsters {
    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
//        System.out.println(Arrays.deepToString(calculateMinDistance(4, 4, Arrays.asList(0, 1), Arrays.asList(3, 2))));
        System.out.println(findBestPath(5, 3, 1, 1, 4, 2, Arrays.asList(0, 2), Arrays.asList(2, 2)));
    }

    public static int findBestPath(int n, int m, int startRow, int startColumn, int endRow, int endColumn, List<Integer> monsterRow, List<Integer> monsterColumn) {
        int[][] minDistanceToMonster = calculateMinDistance(n, m, monsterRow, monsterColumn);
        Queue<int[]> pQueue = new PriorityQueue<>((a, b) -> {
            if (minDistanceToMonster[a[0]][a[1]] == minDistanceToMonster[b[0]][b[1]]) {
                return 0;
            }
            return minDistanceToMonster[a[0]][a[1]] < minDistanceToMonster[b[0]][b[1]] ? 1 : -1;
        });
        boolean[][] visited = new boolean[n][m];
        visited[startRow][startColumn] = true;
        pQueue.offer(new int[] {startRow, startColumn});
        int minDistance = Integer.MAX_VALUE;
        while (!pQueue.isEmpty()) {
            int[] point = pQueue.poll();
            int curRow = point[0], curCol = point[1];
            minDistance = Math.min(minDistance, minDistanceToMonster[curRow][curCol]);
            if (curRow == endRow && curCol == endColumn) {
                return minDistance;
            }
            for (int[] dir : DIRS) {
                int nextRow = curRow + dir[0], nextCol = curCol + dir[1];
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m && !visited[nextRow][nextCol]) {
                    pQueue.offer(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return minDistance;
    }

    public static int[][] calculateMinDistance(int n, int m, List<Integer> monsterRow, List<Integer> monsterColumn) {
        int[][] minDistance = new int[n][m];
        for (int i = 0; i < minDistance.length; ++i) {
            for (int j = 0; j < minDistance[0].length; ++j) {
                minDistance[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < monsterRow.size(); ++i) {
            int mRow = monsterRow.get(i);
            int mCol = monsterColumn.get(i);
            expandDistance(minDistance, mRow, mCol);
        }
        return minDistance;
    }

    public static void expandDistance(int[][] minDistance, int mRow, int mCol) {
        boolean[][] visited = new boolean[minDistance.length][minDistance[0].length];
        int distance = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{mRow, mCol});
        visited[mRow][mCol] = true;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; ++i) {
                int[] point = queue.poll();
                int curRow = point[0], curCol = point[1];
                minDistance[curRow][curCol] = Math.min(minDistance[curRow][curCol], distance);
                for (int[] dir : DIRS) {
                    int nextRow = curRow + dir[0];
                    int nextCol = curCol + dir[1];
                    if (nextRow >= 0 && nextRow < minDistance.length && nextCol >= 0 && nextCol < minDistance[0].length && !visited[nextRow][nextCol]) {
                        queue.offer(new int[]{nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
            distance++;
        }
    }
}

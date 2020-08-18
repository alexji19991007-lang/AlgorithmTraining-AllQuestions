package Karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreasureHunt {
    public static final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        TreasureHunt test = new TreasureHunt();
        int[][] grid = {{1, 0, 0, 0, 0},
                        {0, -1, -1, 0, 0},
                        {0, -1, 0, 1, 0},
                        {-1, 0, 0, 0, 0},
                        {0, 1, -1, 0, 0},
                        {0, 0, 0, 0, 0}};
        for (int[] move : test.shortestPath(grid, new int[]{5, 2}, new int[]{2, 0})) {
            System.out.println(Arrays.toString(move));
        }
    }

    public List<int[]> findLegalMoves(int[][] grid, int i, int j) {
        List<int[]> legalMoves = new ArrayList<>();
        for (int[] dir : DIRS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (inBound(grid, newI, newJ) && grid[i][j] == 0) {
                legalMoves.add(new int[]{i, j});
            }
        }
        return legalMoves;
    }

    // TC: O(m * n)
    // SC: O(m * n)
    public boolean canReachAllZeros(int[][] grid, int i, int j) {
        dfs(grid, i, j);
        for (int m = 0; m < grid.length; ++m) {
            for (int n = 0; n < grid[0].length; ++n) {
                if (grid[m][n] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<int[]> shortestPath(int[][] grid, int[] start, int[] end) {
        int numTreasures = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                numTreasures = grid[i][j] == 1 ? numTreasures + 1 : numTreasures;
            }
        }
        List<List<int[]>> allPaths = new ArrayList<>();
        findPath(grid, start[0], start[1], end, numTreasures, new ArrayList<>(), allPaths);
        if (allPaths.size() == 0) {
            return new ArrayList<>();
        }
        List<int[]> minPath = allPaths.get(0);
        for (List<int[]> path : allPaths) {
            minPath = minPath.size() < path.size() ? minPath : path;
        }
        return minPath;
    }

    private void findPath(int[][] grid, int i, int j, int[] end, int remain, List<int[]> path, List<List<int[]>> allPaths) {
        if (!inBound(grid, i, j) || grid[i][j] == -1 || grid[i][j] == 2) {
            return;
        }
        path.add(new int[]{i, j});
        int temp = grid[i][j];
        if (temp == 1) {
            remain--;
        }
        if (i == end[0] && j == end[1] && remain == 0) {
            allPaths.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            grid[i][j] = temp;
            return;
        }
        grid[i][j] = 2; // visited
        for (int[] dir : DIRS) {
            int newI = i + dir[0], newJ = j + dir[1];
            findPath(grid, newI, newJ, end, remain, path, allPaths);
        }
        grid[i][j] = temp;
        path.remove(path.size() - 1);
    }


    private void dfs(int[][] grid, int i, int j) {
        if (!inBound(grid, i, j) || grid[i][j] == -1 || grid[i][j] == 1) {
            return;
        }
        grid[i][j] = 1; // change to 1 means visited
        for (int[] dir : DIRS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            dfs(grid, newI, newJ);
        }
    }

    private boolean inBound(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}

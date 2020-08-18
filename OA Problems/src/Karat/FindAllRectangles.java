package Karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllRectangles {
    public static void main(String[] args) {
        FindAllRectangles test = new FindAllRectangles();
        int[][] grid = {{1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 0, 0, 0, 1},
                        {1, 1, 1, 0, 0, 0, 1},
                        {1, 1, 1, 0, 0, 0, 1},
                        {1, 1, 1, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1}};
        int[][] grid1 = {{1, 0, 1, 1, 1, 1, 1},
                         {1, 1, 0, 1, 1, 1, 1},
                         {1, 1, 1, 0, 0, 0, 1},
                         {1, 0, 1, 0, 0, 0, 1},
                         {1, 0, 1, 1, 1, 1, 1},
                         {1, 1, 1, 0, 0, 0, 0},
                         {1, 1, 1, 1, 1, 1, 1},
                         {1, 1, 0, 1, 1, 1, 0}};
        int[][] grid2 = {{1, 0, 1, 1, 1, 1, 1},
                         {1, 0, 0, 1, 0, 1, 1},
                         {1, 1, 1, 0, 0, 0, 1},
                         {0, 0, 1, 0, 0, 0, 1},
                         {1, 0, 0, 1, 0, 1, 1},
                         {1, 1, 1, 1, 1, 0, 0},
                         {1, 1, 1, 1, 1, 1, 1},
                         {1, 1, 0, 0, 0, 0, 0}};
        int[][] grid3 = {{1}};
        System.out.println(Arrays.deepToString(test.findOneRectangle(grid)));
        System.out.println();
        for (int[][] arr : test.findAllRectangles(grid1)) {
            System.out.println(Arrays.toString(arr[0]) + ", " + Arrays.toString(arr[1]));
        }
        System.out.println();
        List<List<Point>> shapes = test.findAllShapes(grid3);
        for (List<Point> shape : shapes) {
            for (Point p : shape) {
                System.out.print(p.toString() + " ");
            }
            System.out.println();
        }
    }

    public int[][] findOneRectangle(int[][] grid) {
        int[][] res = new int[2][2];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 0) {
                    res[0][0] = i;
                    res[0][1] = j;
                    int height = 1, width = 1;
                    while (i + height < grid.length && grid[i + height][j] == 0) {
                        height++;
                    }
                    while (j + width < grid[0].length && grid[i][j + width] == 0) {
                        width++;
                    }
                    res[1][0] = i + height - 1;
                    res[1][1] = j + width - 1;
                    return res;
                }
            }
        }
        return null;
    }

    public List<int[][]> findAllRectangles(int[][] grid) {
        List<int[][]> res = new ArrayList<>();
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 0) {
                    int[][] coord = new int[2][2];
                    coord[0][0] = i;
                    coord[0][1] = j;
                    findEnd(i, j, grid, coord);
                    res.add(coord);
                }
            }
        }
        return res;
    }

    private void findEnd(int row, int col, int[][] grid, int[][] coord) {
        int m = grid.length, n = grid[0].length;
        int i = row, j = col;
        while (i < m) {
            if (grid[i][col] == 1) {
                break;
            }
            if (grid[i][col] == -1) {
                continue;
            }
            j = col;
            while (j < n) {
                if (grid[i][j] == 1) {
                    break;
                }
                grid[i][j] = -1;
                j++;
            }
            i++;
        }
        coord[1][0] = i - 1;
        coord[1][1] = j - 1;
    }

    public List<List<Point>> findAllShapes(int[][] grid) {
        List<List<Point>> res = new ArrayList<>();
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 0) {
                    List<Point> shape = new ArrayList<>();
                    dfs(grid, i, j, shape);
                    res.add(shape);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j, List<Point> shape) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 0) {
            return;
        }
        shape.add(new Point(i, j));
        grid[i][j] = -1;
        dfs(grid, i - 1, j, shape);
        dfs(grid, i, j + 1, shape);
        dfs(grid, i + 1, j, shape);
        dfs(grid, i, j - 1, shape);
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public String toString() {
            return "[" + r + ", " + c + "]";
        }
    }
}

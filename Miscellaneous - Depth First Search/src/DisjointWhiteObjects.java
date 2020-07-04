public class DisjointWhiteObjects {
    public int whiteObjects(int[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int res = 0;
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix[0].length; ++col) {
                if (matrix[row][col] == 0 && !visited[row][col]) {
                    res++;
                    visit(matrix, visited, row, col);
                }
            }
        }
        return res;
    }

    public void visit(int[][] matrix, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || visited[r][c] || matrix[r][c] == 1) {
            return;
        }
        // Every time we find a new piece of land that has not been discovered before, mark it as visited
        visited[r][c] = true;
        visit(matrix, visited, r + 1, c);
        visit(matrix, visited, r, c - 1);
        visit(matrix, visited, r - 1, c);
        visit(matrix, visited, r, c + 1);
    }
}

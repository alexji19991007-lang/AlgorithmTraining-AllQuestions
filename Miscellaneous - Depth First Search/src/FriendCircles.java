public class FriendCircles {
    public int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int res = 0;
        for (int i = 0; i < M.length; ++i) {
            if (!visited[i]) {
                dfs(visited, M, i);
                res++;
            }
        }
        return res;
    }

    public void dfs(boolean[] visited, int[][] M, int curRow) {
        for (int j = 0; j < M[0].length; ++j) {
            if (M[curRow][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(visited, M, j);
            }
        }
    }
}

import java.util.*;

public class PlaceToPutChair1 {
    private static final char EQUIP = 'E';
    private static final char OB = 'O';

    public List<Integer> putChair(char[][] gym) {
        int m = gym.length;
        int n = gym[0].length;
        int[][] cost = new int[m][n];
        boolean canReach = false;
        List<Integer> res = Arrays.asList(-1, -1);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (EQUIP == gym[i][j]) {
                    if (!addCost(cost, gym, i, j, canReach)) {
                        return res;
                    }
                    canReach = true;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (EQUIP != gym[i][j] && OB != gym[i][j]) {
                    if (res.get(0) == -1 && res.get(1) == -1) {
                        res = Arrays.asList(i, j);
                    } else if (cost[i][j] < cost[res.get(0)][res.get(1)]) {
                        res.set(0, i);
                        res.set(1, j);
                    }
                }
            }
        }
        return res;
    }

    public boolean addCost(int[][] cost, char[][] gym, int i, int j, boolean canReach) {
        boolean[][] visited = new boolean[gym.length][gym[0].length];
        int pathCost = 1;
        Queue<Pair> queue = new ArrayDeque<>();
        visited[i][j] = true;
        queue.offer(new Pair(i, j));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int l = 0; l < size; ++l) {
                Pair cur = queue.poll();
                List<Pair> neighbors = getNeighbors(cur, gym);
                for (Pair nei : neighbors) {
                    if (!visited[nei.i][nei.j]) {
                        visited[nei.i][nei.j] = true;
                        cost[nei.i][nei.j] += pathCost;
                        queue.offer(nei);
                    }
                }
            }
            pathCost++;
        }
        if (canReach) {
            return true;
        }
        // 如果一个EQUIP被四个obstacle包围住，那我们永远到不了，return false
        // 这个东西只会check一次，i.e.如果canReach是true，说明我们之前已经证明了所有EQUIP都到达的了
        for (int l = 0; l < gym.length; ++l) {
            for (int m = 0; m < gym[0].length; ++m) {
                if (!visited[l][m] && EQUIP == gym[l][m]) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Pair> getNeighbors(Pair cur, char[][] gym) {
        int x = cur.i;
        int y = cur.j;
        int m = gym.length;
        int n = gym[0].length;
        List<Pair> neighbors = new ArrayList<>();
        if (x + 1 < m && OB != gym[x + 1][y]) {
            neighbors.add(new Pair(x + 1, y));
        }
        if (y + 1 < n && OB != gym[x][y + 1]) {
            neighbors.add(new Pair(x, y + 1));
        }
        if (x - 1 >= 0 && OB != gym[x - 1][y]) {
            neighbors.add(new Pair(x - 1, y));
        }
        if (y - 1 >= 0 && OB != gym[x][y - 1]) {
            neighbors.add(new Pair(x, y - 1));
        }
        return neighbors;
    }

    static class Pair {
        int i;
        int j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

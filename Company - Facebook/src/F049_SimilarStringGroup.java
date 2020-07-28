// LeetCode 839
public class F049_SimilarStringGroup {
    public static void main(String[] args) {
        F049_SimilarStringGroup test = new F049_SimilarStringGroup();
        String[] A = new String[]{"tars", "rats", "arts", "star"};
        System.out.println(test.numSimilarGroups(A));
    }

    // Method 1: Union & Find
    // TC: O(n^2 * k), where n is the length of the array, k is the length of each word (not taking
    //     union() operation into account
    // SC: O(n) for UnionFind uf
    public int numSimilarGroups(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 1; i < A.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (isSimilar(A[i], A[j])) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getNumGroups();
    }

    private boolean isSimilar(String s, String t) {
        int diff = 0, i = 0;
        while (i < s.length() && diff <= 2) {
            if (s.charAt(i) != t.charAt(i++)) {
                diff++;
            }
        }
        return diff == 2 || diff == 0;
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            this.parent = new int[size];
            for (int i = 0; i < size; ++i) {
                this.parent[i] = i;
            }
            this.rank = new int[size];
        }

        public void union(int x, int y) {
            int root1 = find(x);
            int root2 = find(y);
            if (root1 == root2) {
                return;
            }
            if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else {
                parent[root1] = root2;
            }
            if (rank[root1] == rank[root2]) {
                rank[root2] += 1;
            }
        }

        public int find(int i) {
            while (parent[i] != i) {
                i = parent[i];
            }
            return i;
        }

        public int getNumGroups() {
            int numGroups = 0;
            for (int i = 0; i < parent.length; ++i) {
                if (i == parent[i]) {
                    numGroups++;
                }
            }
            return numGroups;
        }
    }

    // Method 2: DFS
    // TC: O(n^2 * k)
    // SC: O(n)
    public int numSimilarGroups_DFS(String[] A) {
        int r = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != null) {
                r++;
                dfs(i, A);
            }
        }
        return r;
    }

    private void dfs(int j, String[] A) {
        String s = A[j];
        A[j] = null;
        for (int i = j + 1; i < A.length; i++) {
            if (A[i] != null && isSimilar(A[i], s)) {
                dfs(i, A);
            }
        }
    }
}

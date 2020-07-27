import java.util.*;

// LeetCode 721
public class F042_AccountsMerge {
    // TC:
    // SC:
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new LinkedList<>();
        if (accounts.size() == 0) {
            return res;
        }
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);
        // Step 1: traverse all emails except names, if we have not seen an email before, put it
        //         with its index into map. Otherwise, union the email to its parent index.
        Map<String, Integer> mailToIndex = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < accounts.get(i).size(); ++j) {
                String mail = accounts.get(i).get(j);
                if (mailToIndex.containsKey(mail)) {
                    int preIndex = mailToIndex.get(mail);
                    uf.union(preIndex, i);
                } else {
                    mailToIndex.put(mail, i);
                }
            }
        }
        // Step 2: traverse every email list, find the parent of current list index and put all
        // emails into the set list that belongs to key of its parent index
        Map<Integer, Set<String>> disjointSet = new HashMap<>();
        for (int i = 0; i < accounts.size(); ++i) {
            int parentIndex = uf.find(i);
            disjointSet.putIfAbsent(parentIndex, new HashSet<>());
            Set<String> curSet = disjointSet.get(parentIndex);
            for (int j = 1; j < accounts.get(i).size(); ++j) {
                curSet.add(accounts.get(i).get(j));
            }
            disjointSet.putIfAbsent(parentIndex, curSet);
        }
        for (int index : disjointSet.keySet()) {
            List<String> curList = new LinkedList<>(disjointSet.get(index));
            Collections.sort(curList);
            curList.add(0, accounts.get(index).get(0));
            res.add(curList);
        }
        return res;
    }


    static class UnionFind {
        private int size;
        private int[] parent;

        public UnionFind(int size) {
            this.size = size;
            this.parent = new int[size];
            for (int i = 0; i < size; ++i) {
                this.parent[i] = i;
            }
        }

        public void union(int a, int b) {
            parent[find(a)] = parent[find(b)];
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}

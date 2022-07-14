package HackerRank;

import java.util.*;

public class GetTheGroups {
    public static void main(String[] args) {
        GetTheGroups test = new GetTheGroups();
        List<String> queryType = Arrays.asList("Friend", "Total", "Friend", "Total");
        List<Integer> student1 = Arrays.asList(1, 1, 2, 1);
        List<Integer> student2 = Arrays.asList(2, 4, 3, 4);
        System.out.println(test.getTheGroups(4, queryType, student1, student2));
    }

    public List<Integer> getTheGroups(int n, List<String> queryType, List<Integer> student1, List<Integer> student2) {
        int[] students = new int[n];
        for (int i = 1; i <= n; ++i) {
            students[i - 1] = i;
        }
        UnionAndFind uf = new UnionAndFind(students);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < queryType.size(); ++i) {
            int stu1 = student1.get(i);
            int stu2 = student2.get(i);
            String query = queryType.get(i);
            if (query.equals("Friend")) {
                uf.union(stu1, stu2);
            } else {
                res.add(uf.total(stu1, stu2));
            }
        }
        return res;
    }

    static class UnionAndFind {
        private Map<Integer, Integer> parent;
        private Map<Integer, Integer> rank;
        private Map<Integer, Integer> childrenSize;

        public UnionAndFind(int[] elements) {
            this.parent = new HashMap<>();
            this.rank = new HashMap<>();
            this.childrenSize = new HashMap<>();
            for (int i : elements) {
                parent.put(i, i);
                rank.put(i, 0);
                childrenSize.put(i, 0);
            }
        }

        public int find(int element) {
            if (parent.get(element) != element) {
                int elementParent = find(parent.get(element));
                // Do path compression here
                parent.put(element, elementParent);
            }
            return parent.get(element);
        }

        public void union(int a, int b) {
            int aParent = find(a);
            int bParent = find(b);
            // Already in the same set
            if (aParent == bParent) {
                return;
            }
            if (rank.get(aParent) > rank.get(bParent)) {
                parent.put(bParent, aParent);
                int originalChildrenSize = childrenSize.get(aParent);
                int extraChildren = childrenSize.get(bParent) + 1;
                childrenSize.put(aParent, originalChildrenSize + extraChildren);
            } else if (rank.get(aParent) < rank.get(bParent)) {
                parent.put(aParent, bParent);
                int originalChildrenSize = childrenSize.get(bParent);
                int extraChildren = childrenSize.get(aParent) + 1;
                childrenSize.put(bParent, originalChildrenSize + extraChildren);
            } else {
                parent.put(aParent, bParent);
                int originalChildrenSize = childrenSize.get(bParent);
                int extraChildren = childrenSize.get(aParent) + 1;
                childrenSize.put(bParent, originalChildrenSize + extraChildren);
                rank.put(bParent, rank.get(bParent) + 1);
            }
        }

        public int setSize(int a) {
            return childrenSize.get(find(a)) + 1;
        }

        public int total(int a, int b) {
            return setSize(a) + setSize(b);
        }
    }
}

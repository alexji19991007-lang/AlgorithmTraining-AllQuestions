import java.util.HashMap;
import java.util.Map;

public class UnionAndFind {
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

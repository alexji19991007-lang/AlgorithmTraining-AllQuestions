public class TestUF {
    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5};
        UnionAndFind uf = new UnionAndFind(array);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(3, 4);
        uf.union(1, 4);
        System.out.println(uf.total(3, 5));
    }
}

package Iterators;

public class TestVector2D {
    public static void main(String[] args) {
        int[][] v = {{1, 2}, {3}, {4}};
        Vector2DIterator iterator = new Vector2DIterator(v);
        assert iterator.next() == 1; // return 1
        assert iterator.next() == 2; // return 2
        assert iterator.next() == 3; // return 3
        assert iterator.hasNext(); // return true
        assert iterator.hasNext(); // return true
        assert iterator.next() == 4; // return 4
        assert !iterator.hasNext(); // return false
        System.out.println("All Tests Passed");
    }
}

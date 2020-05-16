public class Coordinate {
    int row;
    int col;

    public Coordinate(int a, int b) {
        this.row = a;
        this.col = b;
    }

    @Override
    public String toString() {
        return ("<" + row + ", " + col + '>');
    }
}

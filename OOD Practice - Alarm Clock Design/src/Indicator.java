public class Indicator {
    private int start;
    private int end;
    private int value;

    public Indicator(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public Indicator(int start, int end) {
        this.start = start;
        this.end = end;
        this.value = 0;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void advance() {
        this.value = value + 1 > end ? start : value + 1;
    }
}

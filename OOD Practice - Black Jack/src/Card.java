public class Card {
    private final int faceValue;
    private final Suit suit;

    public Card(int v, Suit s) {
        this.faceValue = v;
        this.suit = s;
    }

    public int value() {
        return faceValue;
    }

    public Suit suit() {
        return suit;
    }
}

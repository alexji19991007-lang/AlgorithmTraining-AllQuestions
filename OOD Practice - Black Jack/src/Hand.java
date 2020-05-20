import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    // TODO
    protected final List<Card> cards = new ArrayList<>();

    public int score() {
        int score = 0;
        for (Card card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCards(Card[] c) {
        Collections.addAll(cards, c);
    }

    public int size() {
        return cards.size();
    }

    public void print() {
        for (Card c : cards) {
            if (c.value() == 1) {
                System.out.print("A ");
            } else if (c.value() > 1 && c.value() <= 10) {
                System.out.print(c.value() + " ");
            } else if (c.value() == 11) {
                System.out.print("J ");
            } else if (c.value() == 12) {
                System.out.print("Q ");
            } else {
                System.out.print("K ");
            }
        }
        System.out.println();
    }
}

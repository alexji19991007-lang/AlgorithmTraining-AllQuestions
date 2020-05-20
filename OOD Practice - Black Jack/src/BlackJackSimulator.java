import java.util.ArrayList;
import java.util.List;

// 1. 模拟BlackJack
// 2. 简化：所有玩家采用dealer模式，即只要手上的牌分数不到16就自动继续抓牌
//      a. 相当于初始发牌后只玩一轮
//      b. 但是每个玩家在这一轮里需要不断的抓牌，判断
public class BlackJackSimulator {
    private Deck deck;
    private final BlackJackHand[] hands;
    private static final int HIT_UNTIL = 16;

    public BlackJackSimulator(int numPlayers) {
        hands = new BlackJackHand[numPlayers];
        for (int i = 0; i < numPlayers; ++i) {
            hands[i] = new BlackJackHand();
        }
    }

    private void initializeDeck() {
        deck = new Deck();
        deck.shuffle();
    }

    private boolean dealInitial() {
        for (BlackJackHand hand : hands) {
            Card[] cards = deck.dealHand(2);
            if (cards == null) {
                return false;
            }
            hand.addCards(cards);
        }
        return true;
    }

    private List<Integer> getBlackJacks() {
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < hands.length; ++i) {
            if (hands[i].isBlackJack()) {
                winners.add(i);
            }
        }
        return winners;
    }

    private boolean playHand(BlackJackHand hand) {
        while (hand.score() < HIT_UNTIL) {
            Card card = deck.dealCard();
            if (card == null) {
                return false;
            }
            hand.addCards(new Card[] {card});
        }
        return true;
    }

   private boolean playAllHands() {
        for (BlackJackHand hand : hands) {
            if (!playHand(hand)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getWinners() {
        List<Integer> winners = new ArrayList<>();
        int winningScore = 0;
        for (int i = 0; i < hands.length; ++i) {
            BlackJackHand hand = hands[i];
            if (!hand.busted()) {
                if (hand.score() > winningScore) {
                    winningScore = hand.score();
                    winners.clear();
                    winners.add(i);
                } else if (hand.score() == winningScore) {
                    winners.add(i);
                }
            }
        }
        return winners;
    }

    private void printHandsAndScore() {
        for (int i = 0; i < hands.length; ++i) {
            System.out.print("Hand " + i + " (" + hands[i].score() + "): ");
            hands[i].print();
            System.out.println();
        }
    }

    public void simulate() {
        initializeDeck();
        // Deal first round
        boolean success = dealInitial();
        if (!success) {
            System.out.println("Error. Out Of Cards.");
            return;
        }
        // Print Message
        System.out.println("-- Initial --");
        printHandsAndScore();
        // Check blackjack winners
        List<Integer> blackjacks = getBlackJacks();
        if (blackjacks.size() > 0) {
            System.out.println("-- Game Finished --");
            System.out.print("Blackjack At: ");
            for (int i : blackjacks) {
                System.out.print("Player " + i + " ");
            }
            System.out.println();
            return;
        }
        // Deal the remaining rounds
        success = playAllHands();
        if (!success) {
            System.out.println("Error. Out Of Cards.");
            return;
        }
        // Game finished, print result
        System.out.println("\n-- Game Finished --");
        printHandsAndScore();
        List<Integer> winners = getWinners();
        if (winners.size() > 0) {
            System.out.print("Winners: ");
            for (int i : winners) {
                System.out.print("Player " + i + " ");
            }
            System.out.println();
        } else {
            System.out.println("All Players Busted");
        }
    }
}

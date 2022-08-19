public class GameRunner {
    public static void main(String[] args) {
        DotsAndBoxes game = new DotsAndBoxes(4, 4);
        game.connect(0, 0, 0, 1); // 1
        game.connect(1, 2, 1, 3); // 2
        game.connect(0, 0, 1, 0); // 1
        game.connect(1, 2, 2, 2); // 2
        game.connect(1, 1, 1, 0); // 1
        game.connect(1, 3, 2, 3); // 2
        game.connect(1, 1, 1, 2); // 1
        game.connect(2, 2, 3, 2); // 2
        game.connect(0, 1, 0, 2); // 1
        game.connect(3, 2, 3, 3); // 2
        game.connect(0, 2, 1, 2); // 1
        game.connect(2, 3, 3, 3); // 2
        game.connect(0, 1, 1, 1); // 1
        game.connect(2, 2, 2, 3); // 1

        game.checkWinner();
    }
}

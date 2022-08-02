import junit.framework.TestCase;

public class BowlingGameTest extends TestCase {
    private BowlingGame game;

    protected void setUp() throws Exception {
        this.game = new BowlingGame();
    }

    public void testGame_AllZeroScore() throws Exception {
        rollTimes(20, 0);
        assertEquals(0, game.getScore());
    }

    public void testGame_AllOneScore() throws Exception {
        rollTimes(20, 1);
        assertEquals(20, game.getScore());
    }

    public void testGame_OneSpareFollowedByNonSpare() throws Exception {
        rollSpare();
        game.roll(8);
        rollTimes(17, 0);
        assertEquals(26, game.getScore());
    }

    public void testGame_OneStrikeFollowedByNonStrike() throws Exception {
        rollStrike();
        game.roll(4);
        game.roll(4);
        rollTimes(16, 0);
        assertEquals(26, game.getScore());
    }

    public void testGame_AllStrike() throws Exception {
        rollTimes(12, 10);
        assertEquals(300, game.getScore());
    }


    private void rollTimes(int times, int pins) {
        for (int i = 0; i < times; ++i) {
            game.roll(pins);
        }
    }

    private void rollSpare() {
        game.roll(2);
        game.roll(8);
    }

    private void rollStrike() {
        game.roll(10);
    }
}

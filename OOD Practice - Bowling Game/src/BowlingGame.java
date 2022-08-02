public class BowlingGame {
    private final int[] rolls;
    private int curRoll;

    public BowlingGame() {
        this.rolls = new int[21];
        this.curRoll = 0;
    }

    public void roll(int pins) {
        rolls[curRoll++] = pins;
    }

    public int getScore() {
        int score = 0;
        int frameIndex = 0;
        for (int curFrame = 0; curFrame < 10; ++curFrame) {
            if (isStrikeFrame(frameIndex)) {
                score += 10 + getStrikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpareFrame(frameIndex)) {
                score += 10 + getSpareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += getFrameScore(frameIndex);
                frameIndex += 2;
            }
        }
        return score;
    }

    private boolean isSpareFrame(int index) {
        return rolls[index] + rolls[index + 1] == 10;
    }

    private boolean isStrikeFrame(int index) {
        return rolls[index] == 10;
    }

    private int getStrikeBonus(int index) {
        return rolls[index + 1] + rolls[index + 2];
    }

    private int getSpareBonus(int index) {
        return rolls[index + 2];
    }

    private int getFrameScore(int index) {
        return rolls[index] + rolls[index + 1];
    }
}

import java.util.Random;

public class Random7UsingRandom5 {
    public int random7() {
        Random random5 = new Random(5);
        while (true) {
            int r = random5.nextInt() * 5 + random5.nextInt();
            if (r < 21) return r % 7;
        }
    }
}

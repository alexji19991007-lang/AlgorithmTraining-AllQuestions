import java.util.Random;

public class Random1000UsingRandom5 {
    public int random1000() {
        Random rand = new Random(5);
        while (true) {
            // Compute a0 * x^0 + a1 * x^1 + a2 * x^3 + ... + ak * x^k
            int num = 0;
            for (int i = 0; i < 5; ++i) {
                num = num * 5 + rand.nextInt();
            }
            // choose 3000 instead of 100 to reduce the # of expected random5() calls
            if (num < 3000) {
                return num % 1000;
            }
        }
    }
}

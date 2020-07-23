import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneralizedReservoirSampling {
    private final int k;
    private int count;
    private List<Integer> sample;
    private Random rand;

    public GeneralizedReservoirSampling(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be > 0");
        }
        this.k = k;
        this.count = 0;
        this.sample = new ArrayList<>();
        this.rand = new Random();
    }

    public void read(int value) {
        count++;
        if (count <= k) {
            sample.add(value);
        } else {
            int random = rand.nextInt(count);
            if (random < k) {
                sample.set(random, value);
            }
        }
    }

    public List<Integer> sample() {
        return sample;
    }
}

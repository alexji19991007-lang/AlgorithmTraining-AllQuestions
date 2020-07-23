import java.util.Random;

public class ReservoirSampling {
    private int count;
    private Integer sample;
    private Random rand;

    public ReservoirSampling() {
        this.count = 0;
        this.sample = null;
        this.rand = new Random();
    }

    public void read(int value) {
        sample = rand.nextInt(++count) == 0 ? value : sample;
    }

    public Integer sample() {
        return sample;
    }
}

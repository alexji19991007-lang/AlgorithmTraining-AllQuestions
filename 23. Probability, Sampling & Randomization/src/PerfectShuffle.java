import java.util.Random;

public class PerfectShuffle {
    public void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; ++i) {
            int j = rand.nextInt(array.length - i) + i;
            swap(array, i, j);
        }
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

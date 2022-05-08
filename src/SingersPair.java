import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SingersPair {
    public static void main(String[] args) {
        Random rand = new Random();
        List<String> singers = new ArrayList<>() {
            {
                add("s1");
                add("s2");
                add("s3");
                add("s4");
                add("s5");
                add("s6");
                add("s7");
                add("s8");
            }
        };
        for (int i = singers.size(); i > 0; --i) {
            int randIndex = rand.nextInt(i);
            swap(singers, randIndex, i - 1);
        }
        System.out.println(singers);
    }

    public static void swap(List<String> singers, int i, int j) {
        String singer1 = singers.get(i);
        singers.set(i, singers.get(j));
        singers.set(j, singer1);
    }
}

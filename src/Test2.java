import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        String x = "abc,,a,,,abc,";
        System.out.println(Arrays.toString(x.split("\\W+")));
    }
}

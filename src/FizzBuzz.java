import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FizzBuzz {
    public static void main(String[] args) {
        FizzBuzz test = new FizzBuzz();
        test.fizzBuzz(15);
    }

    public void fizzBuzz(int n) {
        Map<Integer, String> dict = new HashMap<>();
        dict.put(3, "Fizz");
        dict.put(5, "Buzz");
        for (int i = 1; i <= n; ++i) {
            StringBuilder toAdd = new StringBuilder();
            for (Map.Entry<Integer, String> entry : dict.entrySet()) {
                if (i % entry.getKey() == 0) {
                    toAdd.append(entry.getValue());
                }
            }
            if (toAdd.toString().equals("")) {
                toAdd.append(i);
            }
            System.out.println(toAdd);
        }
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        Map<Integer, String> dict = new HashMap<>();
        dict.put(3, "Fizz");
        dict.put(5, "Buzz");
        for (int i = 1; i <= n; ++i) {
            StringBuilder toAdd = new StringBuilder();
            for (Integer key : dict.keySet()) {
                if (i % key == 0) {
                    toAdd.append(dict.get(key));
                }
            }
            if (toAdd.toString().equals("")) {
                toAdd.append(i);
            }
            ans.add(toAdd.toString());
        }
        return ans;
    }
}

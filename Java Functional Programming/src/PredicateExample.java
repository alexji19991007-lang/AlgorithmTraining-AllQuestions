import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Map<String, Integer> iqMap = makeMap();
        System.out.println(iqMap);
        // "entry.getValue() <= 100" is a Predicate
        iqMap.entrySet().removeIf(entry -> entry.getValue() <= 100);
        System.out.println(iqMap);
        System.out.println();

        Map<String, Integer> iqMap1 = makeMap();
        System.out.println(iqMap1);
        Predicate<Map.Entry<String, Integer>> lowIq = entry -> entry.getValue() <= 100;
        Predicate<Map.Entry<String, Integer>> curly = entry -> entry.getKey().equals("Curly");
        // Compose two Predicate(s)
        iqMap1.entrySet().removeIf(lowIq.and(curly));
        System.out.println(iqMap1);
    }

    public static Map<String, Integer> makeMap() {
        return new ConcurrentHashMap<>() {
            {
                put("Larry", 100);
                put("Curly", 90);
                put("Moe", 110);
            }
        };
    }
}

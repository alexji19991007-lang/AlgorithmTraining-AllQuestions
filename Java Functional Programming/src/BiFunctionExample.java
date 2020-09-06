import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BiFunctionExample {
    public static void main(String[] args) {
        Map<String, Integer> iqMap = makeMap();
        System.out.println(iqMap);
        // "v - 50" is a BiFunction
        iqMap.replaceAll((k, v) -> v - 50);
        System.out.println(iqMap);
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

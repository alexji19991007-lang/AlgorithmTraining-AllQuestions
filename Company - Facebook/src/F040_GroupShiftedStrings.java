import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LeetCode 249
public class F040_GroupShiftedStrings {
    public static void main(String[] args) {
        String[] strings = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        F040_GroupShiftedStrings test = new F040_GroupShiftedStrings();
        System.out.println(test.groupStrings(strings).toString());
    }

    // TC: O(n * s) where s is the length of the longest string
    // SC: O(n + s)
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        if (strings.length == 0) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String cur : strings) {
            int offset = cur.charAt(0) - 'a';
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cur.length(); ++i) {
                int c = cur.charAt(i) - offset;
                c = c < 'a' ? c + 26 : c;
                sb.append((char)c);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(cur);
        }
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            res.add(list);
        }
        return res;
    }
}

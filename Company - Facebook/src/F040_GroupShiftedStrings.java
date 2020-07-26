import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LeetCode 249
public class F040_GroupShiftedStrings {
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
                sb.append(c);
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

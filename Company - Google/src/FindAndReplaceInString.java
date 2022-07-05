import java.util.HashMap;
import java.util.Map;

// LeetCode 1146
public class FindAndReplaceInString {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < indices.length; ++i) {
            indexMap.put(indices[i], i);
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            int curIndex = indexMap.getOrDefault(i, -1);
            if (curIndex == -1 || !checkSubstring(s, i, sources[curIndex])) {
                sb.append(s.charAt(i++));
                continue;
            }
            sb.append(targets[curIndex]);
            i += sources[curIndex].length();
        }
        return sb.toString();
    }

    public boolean checkSubstring(String s, int i, String sub) {
        for (int j = 0; j < sub.length(); ++j) {
            if (s.charAt(j + i) != sub.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}

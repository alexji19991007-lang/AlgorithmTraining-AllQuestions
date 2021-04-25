import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LargestProductOfLength {
    public int largestProduct(String[] dict) {
        Map<String, Integer> bitMasks = getBitMasks(dict);
        Arrays.sort(dict, (s0, s1) -> {
            if (s0.length() == s1.length()) {
                return 0;
            }
            return s0.length() < s1.length() ? 1 : -1;
        });
        int largest = 0;
        for (int i = 1; i < dict.length; ++i) {
            for (int j = 0; j < i; ++j) {
                int product = dict[i].length() * dict[j].length();
                if (product <= largest) {
                    break;
                }
                int iMask = bitMasks.get(dict[i]);
                int jMask = bitMasks.get(dict[j]);
                if ((iMask & jMask) == 0) {
                    largest = product;
                }
            }
        }
        return largest;
    }

    public Map<String, Integer> getBitMasks(String[] dict) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : dict) {
            int bitMask = 0;
            for (char x : s.toCharArray()) {
                int index = x - 'a';
                bitMask |= (1 << index);
            }
            map.put(s, bitMask);
        }
        return map;
    }
}

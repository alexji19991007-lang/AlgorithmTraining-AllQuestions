import java.util.HashMap;
import java.util.Map;

public class BaseConverter {
    private int base;
    private String value;
    private Map<Character, Integer> hexToDec;
    private Map<Integer, Character> decToHex;

    public BaseConverter(String value, int base) {
        this.value = value;
        this.base = base;
        hexToDec = new HashMap<>();
        decToHex = new HashMap<>();
        for (int i = 0; i < 6; ++i) {
            hexToDec.put((char) ('A' + i), 10 + i);
            decToHex.put(10 + i, (char) ('A' + i));
        }
    }

    public BaseConverter(String value) {
        this.value = value;
        this.base = 10;
    }

    public String toDecimal() {
        int sum = 0;
        int power = 0;
        for (int i = value.length() - 1; i >= 0; --i) {
            int val = value.charAt(i) - '0';
            if (this.base == 16 && hexToDec.containsKey(value.charAt(i))) {
                val = hexToDec.get(value.charAt(i));
            }
            sum += val * Math.pow(this.base, power++);
        }
        return String.valueOf(sum);
    }

    public String toTargetBase(int targetBase) {
        String val = this.base == 10 ? this.value : toDecimal();
        int temp = Integer.parseInt(val);
        StringBuilder sb = new StringBuilder();
        while (temp != 0) {
            int curVal = temp % targetBase;
            if (targetBase == 16 && decToHex.containsKey(curVal)) {
                sb.append(decToHex.get(curVal));
            } else {
                sb.append(curVal);
            }
            temp /= targetBase;
        }
        return sb.reverse().toString();
    }
}

import com.sun.jdi.PathSearchingVirtualMachine;

import java.util.*;

public class HexadecimalRepresentation {
    public static void main(String[] args) {
        System.out.println(hex(122));
    }

    public static String hex(int number) {
        if (number == 0) {
            return "0x0";
        }
        StringBuilder sb = new StringBuilder("0x");
        boolean isLeading = true;
        for (int i = 28; i >= 0; i -= 4) {
            // mask the last 4 bits to get a single hex digit.
            // Example: 29 in binary → 0001 1101
            // number >> 4 → 0000 0001 & 1111 = 1 → first hex digit
            // number >> 0 → 0001 1101 & 1111 = 13 → second hex digit (D)
            int curNum = (number >> i) & 15; // 15 is 1111 in binary
            // As long as the current digit is 0 and it’s still leading, we skip it.
            if (curNum == 0 && isLeading) {
                continue;
            }
            // Once we hit the first non-zero, isLeading = false and we start appending digits.
            isLeading = false;
            if (curNum <= 9) {
                sb.append((char)(curNum + '0'));
            } else {
                sb.append((char)(curNum - 10 + 'A'));
            }
        }
        return sb.toString();
    }
}

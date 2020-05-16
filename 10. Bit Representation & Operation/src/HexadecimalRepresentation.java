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
            int curNum = (number >> i) & 15;
            if (curNum == 0 && isLeading) {
                continue;
            }
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

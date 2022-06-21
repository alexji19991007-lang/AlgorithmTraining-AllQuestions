import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public List<String> Restore(String ip) {
        List<String> res = new ArrayList<>();
        // We cannot restore a string with length greater than 12
        if (ip == null || ip.length() == 0 || ip.length() > 12) {
            return res;
        }
        restoreHelper(ip.toCharArray(), 0, 0, new StringBuilder(), res);
        return res;
    }

    private void restoreHelper(char[] ip, int section, int index, StringBuilder sb, List<String> res) {
        if (section == 4) {
            if (sb.length() == ip.length + 4) {
                res.add(sb.substring(0, sb.length() - 1));
            }
            return;
        }
        if (index < ip.length) {
            sb.append(ip[index]).append('.');
            restoreHelper(ip, section + 1, index + 1, sb, res);
            sb.delete(sb.length() - 2, sb.length());
        }
        if (index + 1 < ip.length) {
            char a = ip[index];
            char b = ip[index + 1];
            if (a != '0') {
                sb.append(a).append(b).append('.');
                restoreHelper(ip, section + 1, index + 2, sb, res);
                sb.delete(sb.length() - 3, sb.length());
            }
        }
        if (index + 2 < ip.length) {
            char a = ip[index];
            char b = ip[index + 1];
            char c = ip[index + 2];
            if ((a == '1') || (a == '2' && b >= '0' && b <= '4') || (a == '2' && b == '5' && c >= '0' && c <= '5')) {
                sb.append(a).append(b).append(c).append('.');
                restoreHelper(ip, section + 1, index + 3, sb, res);
                sb.delete(sb.length() - 4, sb.length());
            }
        }
    }
}

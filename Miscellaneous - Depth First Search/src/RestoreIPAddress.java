import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String ip) {
        List<String> res = new ArrayList<>();
        // We cannot restore a string with length greater than 12
        if (ip.length() > 12) {
            return res;
        }
        restorHelper(ip, res, 0, "", 0);
        return res;
    }

    private void restorHelper(String s, List<String> res, int index, String temp, int section) {
        if (section == 4 && index == s.length()) {
            res.add(temp);
            return;
        }
        for (int i = 1; i <= 3; ++i) {
            if (index + i > s.length()) {
                return;
            }
            // Take out the current part
            String mPart = s.substring(index, index + i);
            // Filter out certain cases where the current part is invalid
            if (mPart.startsWith("0") && mPart.length() > 1 || Integer.parseInt(mPart) > 255) {
                return;
            }
            // recursive call here
            restorHelper(s, res, index + i, section == 0 ? mPart : temp + "." + mPart, section + 1);
        }
    }
}

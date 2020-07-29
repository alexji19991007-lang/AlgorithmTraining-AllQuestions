public class CompressString2 {
    public String compress(String input) {
        if (input.length() == 0) {
            return input;
        }
        return encode(input.toCharArray());
    }

    public String encode(char[] input) {
        int slow = 0, fast = 0;
        int newLength = 0;
        // 第一遍从左往右Scan，做两件事情
        while (fast < input.length) {
            int begin = fast;
            // 跳过连续出现的相同字母
            while (fast < input.length && input[begin] == input[fast]) {
                fast++;
            }
            // Always copy the character
            input[slow++] = input[begin];
            if (fast - begin == 1) {
                // 1. 如果遇到单个出现的letter，newLength加2，但是先不加‘1’
                newLength += 2; // “1”是用来copy字母本身，另一个“1”是copy出现次数
            } else {
                // 2. 如果遇到多个出现的letter，把出现次数加上（总长度一定不会变长,
                //    并且算出出现次数需要几个格子来表示（len）更新newLength
                int len = copyDigits(input, slow, fast - begin);
                slow += len;
                newLength += len + 1; // "+1"是用来copy字母本身
            }
        }
        // 第二遍从右往左Scan
        char[] res = new char[newLength];
        fast = slow - 1;
        slow = newLength - 1;
        while (fast >= 0) {
            // 在这个if-else判断中，我们只copy数字
            if (Character.isDigit(input[fast])) {
                // 如果当前看的是数字的话，将整个数字copy下来
                while (fast >= 0 && Character.isDigit(input[fast])) {
                    res[slow--] = input[fast--];
                }
            } else {
                // 如果当前不是数字，说明这个字母只连续出现了一次，多加一个‘1’
                res[slow--] = '1';
            }
            // copy字母
            res[slow--] = input[fast--];
        }
        return new String(res);
    }

    public int copyDigits(char[] input, int slow, int count) {
        int len = 0;
        for (int i = count; i > 0; i /= 10) {
            len++;
            slow++;
        }
        for (int i = count; i > 0; i /= 10) {
            // 从数字的最小位数开始copy
            int digit = i % 10;
            input[--slow] = (char)(digit + '0');
        }
        return len;
    }
}

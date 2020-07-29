public class StringCompression {
    public static void main(String[] args) {
        StringCompression test = new StringCompression();
        String s = "aabbbbbbbbbbbb";
        System.out.println(test.compress(s.toCharArray()));
    }

    public int compress(char[] chars) {
        int slow = 0, fast = 0;
        int newLength = 0;
        // 第一遍从左往右Scan，做两件事情
        while (fast < chars.length) {
            int begin = fast;
            // 跳过连续出现的相同字母
            while (fast < chars.length && chars[begin] == chars[fast]) {
                fast++;
            }
            // Always copy the character
            chars[slow++] = chars[begin];
            // 如果遇到多个出现的letter，把出现次数加上（总长度一定不会变长）,
            // 并且算出出现次数需要几个格子来表示（len）更新newLength
            int len = copyDigits(chars, slow, fast - begin);
            slow += len;
            newLength += len + 1; // "+1"是用来copy字母本身
        }
        return newLength;
    }

    public int copyDigits(char[] input, int slow, int count) {
        if (count == 1) {
            return 0;
        }
        int len = 0;
        for (int i = count; i > 0; i /= 10) {
            len++;
            slow++;
        }
        for (int i = count; i > 0; i /= 10) {
            // 从数字的最小位数开始copy
            int digit = i % 10;
            input[--slow] = (char) (digit + '0');
        }
        return len;
    }
}

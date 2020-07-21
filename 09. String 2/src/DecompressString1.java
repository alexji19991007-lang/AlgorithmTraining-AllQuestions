public class DecompressString1 {
    public static void main(String[] args) {
        String s = "ap2lec3n";
        DecompressString1 test = new DecompressString1();
        System.out.println(test.decompress(s));
    }

    public String decompress(String input) {
        if (input.length() == 0) {
            return input;
        }
        char[] array = input.toCharArray();
        return decodeLong(array, array.length);
    }

    public String decodeLong(char[] input, int length) {
        int newLength = length;
        for (int i = 0; i < length; ++i) {
            int digit = input[i] - '0';
            if (digit > 2 && digit <= 9) {
                newLength += digit - 2;
            }
        }
        char[] res = new char[newLength];
        int end = newLength - 1;
        for (int i = length - 1; i >= 0; --i) {
            if (!Character.isDigit(input[i])) {
                res[end--] = input[i];
                continue;
            }
            int digit = input[i] - '0';
            i--;
            for (int j = 0; j < digit; ++j) {
                res[end--] = input[i];
            }
        }
        return new String(res);
    }
}

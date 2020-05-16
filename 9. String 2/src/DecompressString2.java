import java.util.*;

public class DecompressString2 {
    public String decompress(String input) {
        if (input.length() == 0) {
            return input;
        }
        char[] array = input.toCharArray();
        return decodeLong(array, decodeShort(array));
    }

    public int decodeShort(char[] input) {
        int end = 0;
        for (int i = 0; i < input.length; i += 2) {
            int digit = input[i + 1] - '0';
            if (digit >= 0 && digit <= 2) {
                for (int j = 0; j < digit; ++j) {
                    input[end++] = input[i];
                }
            } else {
                // We don't handle longer decoded string here
                input[end++] = input[i];
                input[end++] = input[i + 1];
            }
        }
        return end;
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
            int digit = input[i] - '0'; // How many
            if (digit > 2 && digit <= 9) {
                i--;
                for (int j = 0; j < digit; ++j) {
                    res[end--] = input[i]; // Decode
                }
            } else {
                res[end--] = input[i];
            }
        }
        return new String(res);
    }

}

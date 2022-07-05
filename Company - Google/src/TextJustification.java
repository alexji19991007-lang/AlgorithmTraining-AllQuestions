import java.util.ArrayList;
import java.util.List;

// LeetCode 68
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int left = 0;
        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            res.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }
        return res;
    }

    public int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right++].length();
        while (right < words.length && 1 + sum + words[right].length() <= maxWidth) {
            sum += 1 + words[right++].length();
        }
        return right - 1;
    }

    public String justify(int left, int right, String[] words, int maxWidth) {
        if (left == right) {
            return padResult(words[left], maxWidth);
        }
        int sum = words[left].length();
        for (int i = left + 1; i <= right; ++i) {
            sum += words[i].length();
        }
        int numIntervals = right - left;
        int totalWhiteSpace = maxWidth - sum;
        int numSpacesBetweenWords = right == words.length - 1 ? 1 : totalWhiteSpace / numIntervals;
        int remainingSpaces = right == words.length - 1 ? 0 : totalWhiteSpace % numIntervals;
        StringBuilder sb = new StringBuilder();
        for (int i = left; i < right; ++i) {
            sb.append(words[i]);
            sb.append(addWhiteSpace(numSpacesBetweenWords));
            if (remainingSpaces > 0) {
                sb.append(" ");
                remainingSpaces--;
            }
        }
        sb.append(words[right]);
        return right == words.length - 1 ? padResult(sb.toString(), maxWidth) : sb.toString();
    }

    public String padResult(String s, int maxWidth) {
        return s + addWhiteSpace(maxWidth - s.length());
    }

    public String addWhiteSpace(int length) {
        return " ".repeat(length);
    }
}

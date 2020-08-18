package Karat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WrapLines {
    public static void main(String[] args) {
        WrapLines test = new WrapLines();
        String[] words = {"123 45 67 8901234 5678", "12345 8 9 0 1 23"};
        System.out.println(test.wrapLines2(words, 10).toString());
    }

    public List<String> wrapLines1(String[] words, int maxLength) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int p = 0;
        while (p < words.length) {
            if (sb.length() == 0) {
                // assume all words length < maxLength
                sb.append(words[p++]);
            } else if (sb.length() + 1 + words[p].length() <= maxLength) {
                sb.append('-');
                sb.append(words[p++]);
            } else {
                ans.add(sb.toString());
                sb.setLength(0);
            }
        }
        if (sb.length() != 0) ans.add(sb.toString());
        return ans;
    }

    public List<String> wrapLines2(String[] lines, int maxLength) {
        List<UnbalancedString> unbalanced = new ArrayList<>();
        List<String> words = new ArrayList<>();
        for (String line : lines) {
            String[] wordCollection = line.split(" ");
            Collections.addAll(words, wordCollection);
        }
        StringBuilder sb = new StringBuilder();
        int p = 0, numSlashes = 0;
        while (p < words.size()) {
            if (sb.length() == 0) {
                // assume all words length < maxLength
                sb.append(words.get(p++));
            } else if (sb.length() + 1 + words.get(p).length() <= maxLength) {
                sb.append('-');
                numSlashes++;
                sb.append(words.get(p++));
            } else {
                UnbalancedString str = new UnbalancedString(sb.toString(), numSlashes);
                unbalanced.add(str);
                sb.setLength(0);
                numSlashes = 0;
            }
        }
        if (sb.length() != 0) {
            UnbalancedString str = new UnbalancedString(sb.toString(), numSlashes);
            unbalanced.add(str);
        }
        List<String> balanced = new ArrayList<>();
        for (UnbalancedString line : unbalanced) {
            int extraNeeded = maxLength - line.str.length();
            if (line.numSlashes == 0) {
                balanced.add(line.str);
                continue;
            }
            int newSlashAddedToEach = extraNeeded / line.numSlashes;
            int oneMoreSlash = extraNeeded % line.numSlashes;
            StringBuilder balancedStr = new StringBuilder();
            int i = 0;
            while (i < line.str.length()) {
                char cur = line.str.charAt(i);
                if (cur != '-') {
                    balancedStr.append(cur);
                } else {
                    balancedStr.append("-".repeat(newSlashAddedToEach + 1));
                    if (oneMoreSlash > 0) {
                        balancedStr.append("-");
                        oneMoreSlash--;
                    }
                }
                i++;
            }
            balanced.add(balancedStr.toString());
        }
        return balanced;
    }

    static class UnbalancedString {
        String str;
        int numSlashes;

        public UnbalancedString(String str, int numSlashes) {
            this.str = str;
            this.numSlashes = numSlashes;
        }
    }
}

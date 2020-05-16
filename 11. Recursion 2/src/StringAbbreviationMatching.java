public class StringAbbreviationMatching {
    public boolean matching(String input, String pattern) {
        return matchHelper(input, 0, pattern, 0);
    }

    public boolean matchHelper(String input, int inputStart, String pattern, int patternStart) {
        if (inputStart == input.length() && patternStart == pattern.length()) {
            return true;
        }
        if (inputStart == input.length() || patternStart == pattern.length()) {
            return false;
        }
        if (Character.isDigit(pattern.charAt(patternStart))) {
            int num = 0;
            int i = patternStart;
            while (i < pattern.length() && Character.isDigit(pattern.charAt(i))) {
                num = num * 10 + (pattern.charAt(i) - '0');
                i++;
            }
            return inputStart + num <= input.length() &&
                    matchHelper(input, inputStart + num, pattern, i);
        } else {
            return input.charAt(inputStart) == pattern.charAt(patternStart) &&
                    matchHelper(input, inputStart + 1, pattern, patternStart + 1);
        }
    }
}

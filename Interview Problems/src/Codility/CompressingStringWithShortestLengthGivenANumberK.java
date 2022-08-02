package Codility;

import java.util.ArrayList;
import java.util.List;

public class CompressingStringWithShortestLengthGivenANumberK {
    public static void main(String[] args) {
        CompressingStringWithShortestLengthGivenANumberK test = new CompressingStringWithShortestLengthGivenANumberK();
        String S = "AAAAAAAAAAABXXAAAAAAAAAA";
        test.compressionLength(S, 3);
    }

    public int compressionLength(String S, int K) {
        List<Integer> occurrence = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        List<Character> characters = new ArrayList<>();
        int i = 0;
        while (i < S.length()) {
            int begin = i;
            while (i < S.length() && S.charAt(i) == S.charAt(begin)) {
                i++;
            }
            characters.add(S.charAt(begin));
            occurrence.add(i - begin);
            temp.add(i - begin);
        }
        int minLength = Integer.MAX_VALUE;
        int curIndex = 0;
        for (int j = 0; j < S.length(); ++j) {
            int curCount = occurrence.get(curIndex);
            if (j >= K) {
                minLength = Math.min(minLength, calculateLength(temp));
//                if (j > curCount - 1) {
//                    curIndex++;
//                }
                int leftIndex = j - K;

            }
        }
        return -1;
    }

    public int calculateLength(List<Integer> occurrence) {
        int length = 0;
        for (int count : occurrence) {
            if (count > 1) {
                while (count > 0) {
                    length++;
                    count /= 10;
                }
            }
            length++;
        }
        return length;
    }
}

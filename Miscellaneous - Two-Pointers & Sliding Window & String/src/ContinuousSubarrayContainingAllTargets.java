import java.util.HashSet;
import java.util.Set;

public class ContinuousSubarrayContainingAllTargets {
    public static void main(String[] args) {
        char[] array = {'a', 'b', 'c', 'd'};
        String s = "tcbacbd";
        System.out.println(findSubArray(array, s));
        System.out.println(findSubarrayStart(array, s));
    }

    public static int findSubArray(char[] array, String s) {
        int n = s.length();
        if (n < array.length) {
            return -1;
        }
        Set<Character> setArray = new HashSet<>();
        Set<Character> curString = new HashSet<>();
        for (char x : array) {
            setArray.add(x);
        }
        for (int i = 0; i <= n - array.length; ++i) {
            if (setArray.contains(s.charAt(i))) {
                curString.add(s.charAt(i));
                for (int j = 1; j < array.length; ++j) {
                    if (setArray.contains(s.charAt(i + j)) && !curString.contains(s.charAt(i + j))) {
                        curString.add(s.charAt(i + j));
                    } else {
                        curString.clear();
                        break;
                    }
                    if (curString.size() == array.length) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public static int findSubarrayStart(char[] array, String s) {
        int n = s.length();
        if (n < array.length) {
            return -1;
        }
        int numMatched = 0;
        int curStart = 0; // 当numMatched == array.length时，return curStart. curStart同时用作sliding window的左边界
        // 用setArray方便确认当前字符是否在array中
        Set<Character> setArray = new HashSet<>();
        // 用curWindow表示当前sliding window中已经成功match的字符
        Set<Character> curWindow = new HashSet<>();
        for (char x : array) {
            setArray.add(x);
        }
        for (int i = 0; i < s.length(); ++i) {
            char x = s.charAt(i);
            // 如果当前字符在array里面，并且当前的sliding window中还没有这个字符
            if (setArray.contains(x) && !curWindow.contains(x)) {
                curWindow.add(x);
                numMatched++;
                if (numMatched == array.length) {
                    return curStart;
                }
            }
            // 如果当前字符在array里面，但是当前的sliding window中已经存在这个字符了
            else if (setArray.contains(x) && curWindow.contains(x)) {
                // 一直移动左边界直到此duplicate被删除
                while (s.charAt(curStart) != x) {
                    curWindow.remove(x);
                    curStart++;
                    numMatched--;
                }
                // 我们会停在这个duplicate上，所以还要increment curStart一次
                curStart++;
            }
            // 若当前字符不在array里面
            else {
                // 清空当前sliding window，将左边界直接移动到当前index的下一个，numMatched归零
                numMatched = 0;
                curStart = i + 1;
                curWindow.clear();
            }
        }
        return -1;
    }
}

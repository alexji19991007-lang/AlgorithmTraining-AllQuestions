import java.util.*;

public class StringReplace {
    public String replace(String input, String source, String target) {
        char[] array = input.toCharArray();
        if (source.length() >= target.length()) {
            return replaceShorter(array, source, target);
        }
        return replaceLonger(array, source, target);
    }

    public String replaceShorter(char[] array, String s, String t) {
        int slow = 0, fast = 0;
        while (fast < array.length) {
            if (fast <= array.length - s.length() && matchSubstring(array, fast, s)) {
                copySubstring(array, slow, t);
                slow += t.length();
                fast += s.length();
            } else {
                array[slow++] = array[fast++];
            }
        }
        return new String(array, 0, slow);
    }

    public String replaceLonger(char[] array, String s, String t) {
        // 第一遍从左往右看，找到所有match的最后一个字母的index
        List<Integer> matchIndices = getMatches(array, s);
        if (matchIndices.size() == 0) {
            return new String(array);
        }
        // 计算需要增加的长度，创立新的array
        int newLength = matchIndices.size() * (t.length() - s.length()) + array.length;
        char[] newArray = new char[newLength];
        // 三个指针分别指向三个不同的容器
        int slow = newLength - 1;
        int fast = array.length - 1;
        int index = matchIndices.size() - 1;
        while (fast >= 0) {
            if (index >= 0 && fast == matchIndices.get(index)) {
                copySubstring(newArray, slow - t.length() + 1, t);
                slow -= t.length();
                fast -= s.length();
                index--;
            } else {
                newArray[slow--] = array[fast--];
            }
        }
        return new String(newArray);
    }

    public List<Integer> getMatches(char[] array, String s) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (i <= array.length - s.length()) {
            if (matchSubstring(array, i , s)) {
                res.add(i + s.length() - 1);
                i += s.length();
            } else {
                i++;
            }
        }
        return res;
    }

    public boolean matchSubstring(char[] array, int start, String target) {
        for (int i = 0; i < target.length(); ++i) {
            if (array[start + i] != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public void copySubstring(char[] array, int start, String target) {
        for (int i = 0; i < target.length(); ++i) {
            array[start + i] = target.charAt(i);
        }
    }

}

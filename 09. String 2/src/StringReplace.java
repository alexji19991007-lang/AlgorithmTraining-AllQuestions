import java.util.*;

// 这道题最核心的难点就是 source 比 target 短（replace longer） 的情况。
// 因为替换后字符串会变长，所以不能在原数组里从左往右直接改，否则会覆盖还没处理的字符。

// 所以我们的核心思路是：
// 第一遍：找到所有需要替换的位置
// 第二遍：从右往左构建新数组

// 第一步：找到所有匹配的位置 --> 每个匹配 substring 的最后一个字符 index
// 例如：
//
// input = supermansuperman
// source = man
//
// index：
// s u p e r m a n s u p   e   r  m   a   n
// 0 1 2 3 4 5 6 7 8 9 10  11 12  13  14  15
//
// "man" 出现在：
// 5 6 7
// 13 14 15
// 所以记录：7, 15
// 最终：matchIndices = [7, 15]
// 为什么存 最后一个index？--> 因为后面 从右往左处理 时：fast == matchIndex，就知道碰到一个完整匹配。

// 第二步：计算新的长度，创建新array
// int newLength = matchIndices.size() * (t.length() - s.length()) + array.length;
// 新长度 = match的次数 * 每次替换增加的长度 + 原array长度

// 第三步：三个指针从后往前相向而行，填写新的array
// int slow = newLength - 1;            --> Write to new array
// int fast = array.length - 1;         --> Read from old array
// int index = matchIndices.size() - 1; --> which occurrence are we looking at

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

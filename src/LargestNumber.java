import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(largestNumber(nums));
    }


    public static String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] numString = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            numString[i] = String.valueOf(nums[i]);
        }
        Comparator<String> comp = (str1, str2) -> {
            String s1 = str1 + str2;
            String s2 = str2 + str1;
            // Let's say s1 = "123", s2 = "125"
            // s2.compareTo(s1) = -2 since 3 - 5 = 2 (actually minus their ascii values);
            // If s1 = "379", s2 = "999"
            // s2.compareTo(s1) = -6 since 3 - 9 = -6 (always compare the first difference)
            return s2.compareTo(s1);
        };
        Arrays.sort(numString, comp);
        if (numString[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : numString) {
            sb.append(s);
        }
        return sb.toString();
    }
}

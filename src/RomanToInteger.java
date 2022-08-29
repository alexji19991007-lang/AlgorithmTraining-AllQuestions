public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("IV"));
    }

    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] nums = new int[s.length()];
        for (int i = 0; i < nums.length; ++i) {
            char cur = s.charAt(i);
            switch (cur) {
                case 'M' -> nums[i] = 1000;
                case 'D' -> nums[i] = 500;
                case 'C' -> nums[i] = 100;
                case 'L' -> nums[i] = 50;
                case 'X' -> nums[i] = 10;
                case 'V' -> nums[i] = 5;
                case 'I' -> nums[i] = 1;
            }
        }
        int sum = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] < nums[i + 1]) {
                sum -= nums[i];
            } else {
                sum += nums[i];
            }
        }
        return sum + nums[nums.length - 1];
    }

    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLetters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num = num - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString();
    }
}

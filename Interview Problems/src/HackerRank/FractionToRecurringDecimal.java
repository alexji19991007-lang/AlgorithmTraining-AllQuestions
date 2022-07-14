package HackerRank;

import java.util.HashMap;

public class FractionToRecurringDecimal {
    public static void main(String[] args) {
        FractionToRecurringDecimal test = new FractionToRecurringDecimal();
        System.out.println(test.fractionToDecimal(1, 3));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-", determine the sign
        res.append((numerator > 0) ^ (denominator > 0) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        // Integer part:
        res.append(num / den);
        num %= den;
        // if there is no remainder, just return our current solution (no decimal part)
        if (num == 0) {
            return res.toString();
        }
        // fraction part:
        res.append(".");
        // We use a hash map to detect recurrence
        HashMap<Long, Integer> map = new HashMap<>();
        // the value of each pair indicates at what index a particular number has occurred
        map.put(num, res.length());
        while (num != 0) {
            // get next decimal number
            num *= 10;
            res.append(num / den);
            num %= den;
            // check if there is a recurrence
            if (map.containsKey(num)) {
                int index = map.get(num);
                // if recurrence occurs, find the recurrence starting position, insert a '( ' before
                res.insert(index, "(");
                res.append(")");
                break;
            } else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}

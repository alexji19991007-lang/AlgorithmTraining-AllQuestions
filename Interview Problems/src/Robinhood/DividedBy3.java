package Robinhood;

public class DividedBy3 {
    public static void main(String[] args) {
        DividedBy3 test = new DividedBy3();
        System.out.println(test.dividedBy3("456"));
    }

    public int dividedBy3(String number) {
        int res = 0;
        for (int i = 0; i < number.length(); ++i) {
            for (int j = 0; j <= i; ++j) {
                int num = Integer.parseInt(number.substring(j, i + 1));
                res += num % 3 == 0 ? 1 : 0;
            }
        }
        return res;
    }
}

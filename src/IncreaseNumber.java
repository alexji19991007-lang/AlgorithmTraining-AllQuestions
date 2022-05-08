public class IncreaseNumber {
    public static void main(String[] args) {
        IncreaseNumber test = new IncreaseNumber();
        System.out.println(test.solution(109, 0));
    }

    public int solution(int n, int k) {
        int cur = 100;
        while (cur > 0 && k > 0) {
            while (n + cur < 1000 && k > 0) {
                n += cur;
                k--;
            }
            cur /= 10;
        }
        return n;
    }
}
